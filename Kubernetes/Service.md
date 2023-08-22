## Service
 - 동적으로 변하는(파드 재배치 등) 파드들에 고정적으로 접근할 때 사용하는 방법

### Service Type
 - ClusterIP
   - 기본 서비스 타입, 클러스터 안에서만 사용할 수 있음
 - NodePort
   - 서비스 하나에 모든 노드의 지정된 포트를 할당
   - 클러스터 외부에서도 접근 가능하며, 파드가 node1에서 실행되고 있다해도 node2:port로 접근 가능
 - LoadBalancer
 
### kube-proxy
 - 쿠버네티스에서 서비스를 만들었을 때 클러스터 IP나 노드 포트로 접근할 수 있게 만들어 실제 조작을 하는 컴포넌트
 - 클러스터의 노드마다 실행되면서 내부 IP로 연결하려는 요청을 적절한 파드로 전달함

#### kube-proxy 네트워크 관리 방법
 - userspace
   - 클라이언트 -> 클러스터 IP -> iptables -> kube-proxy -> pods
 - iptables(현재 기본 관리 모드)
   - 클라이언트 -> 클러스터 IP -> iptables -> pods
   - kube-proxy가 iptables를 관리하는 역할만 함
 - IPVS(IP Virtual Server) 모드
   - 리눅스 커널에 있는 L4 로드밸런싱 기술
   - 클라이언트 -> 클러스터 IP(가상 서버) -> pods