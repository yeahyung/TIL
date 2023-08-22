## Kubernetes
 - 컨테이너 오케스트레이션 도구
 - 클러스터를 관리하는 마스터와 실제 컨테이너를 실행시키는 워커 노드로 구성
 - 마스터에는 `etcd`, `kube-apiserver`, `kube-scheduler`, `kube-controller-manager`, `kubelet`, `kube-proxy`, `docker` 등의 컴포넌트가 실행됨
 - 워커는 마스터의 `kube-apiserver`를 통해 통신함(파드 생성,  관리, 삭제 담당), `etcd`는 `kube-apiserver`를 통해서만 접근 가능
 - `kubelet`을 통해 docker 관리

### etcd
 - `key-value` 저장소
 - 쿠버네티스에서 필요한  모든 데이터를 저장하는 데이터베이스 역할

### kube-apiserver
 - 쿠버네티스 클러스터의 API를 사용할 수 있도록 하는 컴포넌트

### kube-scheduler
 - 현재 클러스터 안에서 자원 할당이 가능한 노드 중 알맞은 노드를 선택해 새롭게 만든 파드를 실행
   - 하드웨어 요구 사항, 어피니티 등 조건 확인

### kube-controller-manager
 - 파드들을 관리하는 컨틀롤러

### kubelet
 - 파트 컨테이너들의 실행을 직접 관리, 컨테이너가 정상 시행중인지 헬스 체크

### kube-proxy
 - 쿠버네티스는 클러스터 안에 별도의 가상 네트워크 설정 및 관리하는데 `kube-proxy`를 통해 이런 가상 네트워크의 동작을 관리함