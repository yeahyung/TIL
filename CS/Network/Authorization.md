## Authorization

### Cookie
 - 서버에서 header에 cookie 값을 담아 리턴한 것을 브라우저의 쿠키 디렉토리에 저장됨

### Session
 - 쿠키를 이용하여 구현됨
 - 클라이언트를 구분하기 위해 각 클라이언트별 session ID를 부여하고, 클라이언트는 쿠키에 session ID를 저장해둠
 - 세션은 쿠키와 달리 서버측에 저장하여 관리됨

### 로그인 상태 관리
 - HTTP는 비연결성(Connectionless), 비상태성(Stateless) 특성을 지니기 때문에 서버는 클라이언트가 로그인을 했더라도 이후 요청 때 해당 클라이언트가 로그인을 했는지 알 수 없음
 - 따라서, 쿠키와 세션을 활용하여 로그인 상태를 유지
   - 클라이언트가 로그인 시 서버는 회원정보를 대조하여 인증(authentication)
   - 회원 정보를 세션 저장소에 생성하고 session ID 발급
   - 이후, http response header 쿠키에 발급한 session ID를 담아서 리턴
   - 클라이언트에서는 session ID를 쿠키 저장소에 저장하고, 이후 http request를 보낼때마다 쿠키에 session ID를 담아서 보냄
   - 서버에서는 쿠키에 담겨져서 온 session ID에 해당하는 회원 정보를 세션 저장소에서 가져옴(authorization)