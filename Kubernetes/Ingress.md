## Ingress
 - 클러스터 외부에서 클러스터 파드에 접근할 때 사용하는 방법
 - Service는 보통 L4 영역 / Ingress는 L7 영역의 통신을 담당해서 처리
 - 클러스터 외부에서 접근해야 할 URL을 사용하도록 할 수 있고, 트래픽 로드밸런싱, SSL 인증서, 도메인 기반 가상 호스팅 제공
   - Ingress 자체는 이런 규칙들을 정의해둔 자원이고, 실제로 동작시키는 것은 Ingress COntroller
   
### Ingress Controller
 - ingress-nginx controller