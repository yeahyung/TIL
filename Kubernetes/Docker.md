## Docker
 - Linux 기반의 Container Runtime 오픈소스
 - VM(Virtual Machine) 보다 훨씬 가벼운 형태
 - cgroup을 통해 프로세스별 자원 격리 / chroot로 특정 디렉터리 권한 제한 등을 통해 격리 환경 구성
   - 위의 기능들을 모아서 컨테이너를 손쉽게 사용할 수 있도록 한 것 = Docker

### Virtual Machine
 - Host OS 위에 Hypervisor(VMWare, KVM, Xen, ...)가 설치된 뒤, 그 위에 VM(Guest OS + APP) 들이 만들어진다.
 - Docker는 Host OS 위에 Hypervisor가 아닌 Docker Engine이 실행되고 그 위에 Container(APP) 들이 실행됨