## DNS(Domain Name System)
### DNS Lookup을 통해 IP를 획득하는 방법
 - hosts 파일 확인
 - DNS Cache 확인
 - DNS 질의
 - GSLB(Global Server Load Balancer)
   - google, naver 급의 서비스는 GSLB 를 사용함
   - CDN 질의 -> CDN 에서 Client IP 확인 & IP 기반으로 위치 확인하여 가장 가까운 곳에 위치한 서버의 IP 리턴
   - GSLB는 Healch Check를 통해 서버의 상태를 확인하여 가까운 곳의 서버가 Down되었을 경우 다음 서버의 IP 를 리턴함

### www.google.com 에 접근했을 때 화면이 나오는 과정
 - 유저가 브라우저에 www.google.com 을 입력하면 HTTP request message 생성
 - IP 주소를 알아야하기 때문에 DNS lookup을 통해 해당 domain의 server IP 주소를 획득
 - 반환된 IP 주소로 HTTP request message 전송
   - 생성된 HTTP request message를 TCP/IP 층에 전달
   - HTTP request message에 헤더를 추가해서 TCP/IP 패킷 생성
 - 해당 패킷은 전기신호로 랜선을 통해 네트워크로 전송, 목적지 IP에 도착
 - 구글 server에 도착한 패킷은 unpacking(decapsulation)을 통해 message를 복원하고 server의 process로 보내짐
 - server의 process는 HTTP request message에 대한 response data를 가지고 HTTP response message 생성
 - HTTP response message를 client IP에 전송
 - HTTP response message에 담긴 데이터를 토대로 웹브라우저에서 HTML 렌더링을 하여 검색창 노출