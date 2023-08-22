## Kubernetes Network

### Pods
 - Pod: 한개 이상의 컨테이너를 구성하고 같은 host와 network 스택을 공유하고 volume과 같은 리소스들을 공유
   - network 스택을 공유한다는 의미: pod 안의 모든 컨테이너들은 localhost를 통해 서로에게 도달이 가능하다는 것
   - Pod 내의 container들은 같은 `veth(virtual ethernet)`을 공유함
     - -> 이로 인해 container 들은 localhost를 통해 서로에게 접근 가능
     - 각 Pod마다 `pause`라는 명령으로 실행된 container가 존재하며, 다른 컨테이너에게 network interface를 제공하는 역할을 담당
   - Pod 내의 container들이 서로 통신할 수 있다고 시스템을 만들지 못함 -> 외부와의 통신이 필요, 따라서 `service` 등장

### Services
 - Pod IP는 언제든지 변경될 수 있는 존재, 따라서 Client에서는 Pod IP를 Endpoint로 설정하기 어려움
 - -> reverse-proxy(load balancer) 활용
   - 클라이언트 -> proxy -> proxy는 살아있는 서버에게 트래픽 전달
 - `Service`란 쿠버네티스 리소스 타입 중 하나로 각 Pod로 traffic을 포워딩해주는 프록시 역할
   - `selector`를 이용하여 traffic 전달을 받을 Pod 들을 결정
   - `Service Name`을 통해 통신 가능(내부 클러스터 DNS가 있기 때문에 가능)
     - `client pod`에서 `Service Name`으로 API 호출 시, 내부 클러스터 DNS에 의해 `Service Name` -> `Cluster IP`로 변환되어 호출됨
       - 이때, `Cluster IP`는 `Pod`의 라우팅 테이블에 없는 대역(`service network` 대역)이기 때문에 상위 게이트웨이로 전달되고, default gateway까지 가야하지만 `kube-proxy`에 의해 `Cluster IP`가 `server pod` 중 하나의 IP 로 변환되게됨
 - `Service`에서 사용이 필요한 `service network` 대역이 따로 지정되어있음(Pod 네트워크, Node 네트워크와는 다름)

#### kube-proxy
 - `netfilter`: Rule-based 패킷 처리 엔진
   - kernel space에 위치하며 오고가는 모든 패킷의 생명 주기 관찰
   - 규칙에 매칭되는 패킷 발견 시 미리 정의된 action 수행(destination 주소 변경도 가능)
     - kernel space에 존재하는 proxy!

##### user space mode
 - `kube-proxy`가 localhost interface에서 `service`의 요청을 받가내기 위해 특정 포트를 연다.
 - `netfilter`로 하여금 `Service IP(Cluster IP)`로 들어오는 패킷을 `kube-proxy` 자신에게 라우팅되도록 설정
 - `kube-proxy`로 들어온 요청을 실제 `server pod:port`로 요청을 전달
 - `user space`에서 proxy하는 것은 모든 패킷을 `user space`에서 `kernel space`로 변환을 해야하기 때문에 비용이 많이 듬
   - -> `iptables mode` 등장

##### iptables mode
 - `kube-proxy`가 실제 proxy 역할을 수행하지 않고 모두 `netfilter`에게 맡긴 형태
 - `netfilter`로 하여금 `Service IP(Cluster IP)`로 들어오는 패킷을 `kube-proxy`가 아닌 `server pod`으로 전달

##### ipvs mode(experimental)
 - iptables mode 에서 pod의 개수가 많아지게 되면 느려지는 문제 발생
   - iptables 의 동작 원리인 `Chain`에 의해 성능 저하
     - 룰을 찾을 때 O(n) 비용 소요된다고 함, 반면 ipvs는 hasing을 사용하기 때문에 O(1)

##### kube-proxy reliability
 - `kube-proxy`는 systemd unit 으로 동작하거나 daemonset으로 설치되기 때문에 죽어도 다시 살아날 수 있음
 - health server pod 감지는 어떻게?
   - `kube-proxy`는 마스터 api 서버의 정보를 수신하기 때문에 클러스터의 변화를 감지함, 이를 통해 지속적으로 iptables을 업데이트하여 netfilter 규칙을 최신화

##### 단점
 - 클러스터 안의 Pod에서 요청한 request만 위의 방식으로 동작함
 - `netfilter`를 사용하는 방식때문에 외부에서 들어온 요청에 대해서는 원 요청자의 origin IP가 수정되어 들어옴

### NodePort
 - 노드 네트워크 IP를 통하여 접근 + ClusterIP 로도 접근 가능
 - `kube-proxy`가 각 노드의 `eh0` 네트워크 인터페이스에 `30000-32767` 포트 사이의 임의의 포트를 할당함
   - 노드:Port 로 요청이 오게되면 매핑된 `ClusterIP`로 전달
 - `source ip` 유실 이슈(`SNAT(source network address transaltion)`)
   - client -> node2:nodePort 호출
   - node2 replaces the source IP address (SNAT) in the packet with its own IP address
   - node2 replaces the destination IP on the packet with the pod IP(kube-proxy)
   - packet is routed to node 1(netfilter), and then to the endpoint
 - `SNAT`란?
   - 네트워크 패킷이나 데이터 송수신 시 IP 주소와 포트 번호를 변환하여 네트워크의 주소 체계를 변경하는 프로세스
   - 사설 네트워크 -> 공인 네트워크 통신 시 사설 네트워크의 IP 주소를 공인 네트워크 IP 주소로 변환하여 외부로 나감
   - 반대로 공인 네트워크 IP 주소를 사설 네트워크의 IP 주소로 변환하여 내부로 들어올 때 사용됨
     - kubernetes에서 왜 바꾸는가?
       - 내부 노드에서 클라이언트의 응답을 올바르게 전달하기 위해

### Ingress
 - 클러스터 외부에서 각 Pod로 트래픽을 전달, 트래픽을 어떻게 처리할지 정의(/w nginx)
 - Layer 7 에서의 요청