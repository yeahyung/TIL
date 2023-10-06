### MSA
- 마이크로 서비스 아키텍쳐의 장점도 많지만 단점도 많음
    - 시스템이 커질수록 너무 많은 서비스가 생기고, 서비스간의 연결이 복잡해지게 된다.
    - 특히, a -> b -> c -> d 이런식으로 연관 모듈들이 호출될 경우 d에서 장애가 발생하면 어느 서비스에서 장애가 발생했는지(시초가 무엇인지) 추적이 쉽지 않다.
    - 또한 `장애 전파 현상`도 겪을 수 있음
        - client -> a -> b 호출일 때, b가 장애 혹은 응답이 느린 상황이라면 a -> b 호출 과정에서 a의 모든 thread들도 응답을 받기 위한 대기 상태가 될 것이고 결국 a도 장애 상태가 될 수 있다.
        - 이러한 문제를 해결하기 위해 `써킷 브레이커`와 같은 디자인 패턴으로 해결할 수 있지만 이런 패턴들을 적용하긴 매우 어려움
            - 스프링에선 `Spring Cloud Netflix`으로 손쉽게 프레임웍을 사용할 수 있도록 해줬는데 자바 한정이라는 단점이 있다.

### ServiceMesh
- 위의 문제들을 해결하기 위해 도입되었음
- `소프트웨어` 계층이 아닌 `인프라` 계층에서 이를 해결한다.
- `Service` -> `Service` 호출에서 `Service`<->`Proxy` -> `Proxy`<->`Service`와 같은 형태로 서비스마다 프록시를 두어서 서비스들을 호출한다.
    - 서비스에서 나가거나 들어오는 트래픽을 네트워크단(프록시)에서 통제할 수 있다.
    - 하지만 msa 구조에선 서비스가 엄청 많음, 즉 프록시도 엄청나게 많은데 프록시에 대한 설정도 너무 어려워지는데?!
        - 각 프록시에 대한 설정 정보를 중앙 집중화된 컨트롤러가 통제하는 구조를 갖자!

<img width="378" alt="스크린샷 2023-09-01 오후 6 33 29" src="https://media.oss.com/user/16337/files/af00b38b-0f45-4867-a9ec-ea686ee314ee">

### Envoy Proxy
- L4 기능뿐 아니라 L7 기능도 지원하며 HTTP 2.0, TCP, gRPC까지 다양한 프로토콜을 지원하는 프록시
    - HTTP L7 라우팅 지원을 하기 때문에 URL 기반 라우팅, 버퍼링, 서버간 부하 분산량 조절도 가능하다.
    - Auto Retry, Circuit Breaker, 부하량 제한 등 로드밸런싱 기능 제공
    - Dynamic Configuration 지원
        - 중앙 레포지토리 설정 정보를 동적으로 읽어와 서버 재시작없이 라우팅 설정 변경 기능 제공

#### Envoy 배포 아키텍쳐
- Front Envoy Proxy
    - 전체 시스템의 앞에 위치하는 프록시, 클라이언트에서 오는 호출을 받아서 각각의 서비스로 라우팅
- Service to service ingress listener
    - 특정 서비스 앞에 위치하여 서비스로 들어오는 트래픽에 대한 처리
    - 트래픽에 대한 버퍼링, Circuit breaker 역할
- Service to service egress listener
    - 특정 서비스 뒤에 위치하여 서비스에서 나가는 트래픽 통제
    - 호출 대상이 되는 서비스에 대한 로드 밸런싱, 호출 횟수 통제 역할
- External service to egress listener
    - 내부 서비스에서 외부 서비스로 나가는 트래픽 관리하는 역할

<img width="645" alt="스크린샷 2023-09-01 오후 6 42 04" src="https://media.oss.com/user/16337/files/f5ef88e9-02b1-4cfc-a71e-dc69e81b9eba">

- Envoy 설정은 크게 `Listener`, `Filter`, `Cluster` 로 구성된다.
- Listener를 통해 메시지를 받고, Filter를 이용하여 받은 메시지를 처리한 후 라우팅 규칙에 따라 적절한 Cluster로 라우팅을 해서 적절한 서비스로 메시지를 보낸다.

#### Envoy 실습
- `docker pull envoyproxy/envoy:v1.27-latest`
- `docker run --rm -d -p 10000:10000 envoyproxy/envoy:v1.27-latest`

```
admin:
  address:
    socket_address:
      protocol: TCP
      address: 0.0.0.0
      port_value: 9901
static_resources:
  listeners:
  - name: listener_0
    address:
      socket_address:
        protocol: TCP
        address: 0.0.0.0
        port_value: 10000
    filter_chains:
    - filters:
      - name: envoy.filters.network.http_connection_manager
        typed_config:
          "@type": type.googleapis.com/envoy.extensions.filters.network.http_connection_manager.v3.HttpConnectionManager
          scheme_header_transformation:
            scheme_to_overwrite: https
          stat_prefix: ingress_http
          route_config:
            name: local_route
            virtual_hosts:
            - name: local_service
              domains: ["*"]
              routes:
              - match:
                  prefix: "/"
                route:
                  host_rewrite_literal: www.envoyproxy.io
                  cluster: service_envoyproxy_io
          http_filters:
          - name: envoy.filters.http.router
            typed_config:
              "@type": type.googleapis.com/envoy.extensions.filters.http.router.v3.Router
  clusters:
  - name: service_envoyproxy_io
    connect_timeout: 30s
    type: LOGICAL_DNS
    # Comment out the following line to test on v6 networks
    dns_lookup_family: V4_ONLY
    lb_policy: ROUND_ROBIN
    load_assignment:
      cluster_name: service_envoyproxy_io
      endpoints:
      - lb_endpoints:
        - endpoint:
            address:
              socket_address:
                address: www.envoyproxy.io
                port_value: 443
    transport_socket:
      name: envoy.transport_sockets.tls
      typed_config:
        "@type": type.googleapis.com/envoy.extensions.transport_sockets.tls.v3.UpstreamTlsContext
        sni: www.envoyproxy.io
```
- `/etc/envoy/envoy.yaml` 파일
- `static_resources`
    - 10000 port로 접근하는 것을 `www.envoyproxy.io` 으로 라우팅하도록 처리되어있음
