## HTTP(HyperText Transfer Protocol)
 - TCP/IP 기반으로 작동하며 request/response 구조로 웹상에서 데이터를 주고받을 수 있는 프로토콜
 - request message: start line(method, path, HTTP version), headers, body
 - response message: status line(HTTP version, status code, status message), headers, body
 - Connectionless: 응답을 받으면 연결을 끊어버림 -> 많은 사람이 웹을 이용하더라도 실제 동시 접속을 최소화하여 많은 유저의 요청을 처리할 수 있음
   - 연결을 끊었기 때문에 클라이언트의 이전 상태(로그인 유무) 등을 알 수 없다는 Stateless 특성
     - -> cookie, session, jwt 등을 통해 해결
 - 데이터 유출을 방지하기 위해 암호화 추가된 프로토콜 = HTTPS

### GET vs POST
 - GET: 데이터 조회 요청, URL 뒤에 Query String(Query String까지 포함해서 브라우저 히스토리에 남기 때문에 캐시 가능)
 - POST: 데이터 처리(주로 생성), 전달 데이터를 body 에 포함하여 통신(캐시 불가능)

### PUT vs PATCH
 - PUT: 모든 리소스 수정(리소스를 대체, 리소스가 없으면 생성)
 - PATCH: 일부 리소스 수정

### HTTP Status Code
 - 클라이언트가 보낸 요청에 대한 서버의 응답 코드
   - 1xx(정보): 요청을 받았으며 작업을 계속한다.
   - 2xx(성공): 클라이언트가 요청한 동작을 성공적으로 처리
   - 3xx(리다이렉션): 요청을 완료하기 위해 추가 작업 조치가 필요
   - 4xx(클라이언트 오류): 클라이언트 요청에 문제가 있다.
     - 401(Unauthorized, 인증 실패), 403(Forbidden, 인증은 됐으나 권한이 없음)
   - 5xx(서버 오류): 서버가 유효한 요청의 수행을 실패했다.