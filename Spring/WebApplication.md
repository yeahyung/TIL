### Web Server
 - HTTP 기반으로 동작
 - **정적 리소스**(HTML, CSS, JS, 이밎, 영상 등) 제공
 - Nginx, Apache

### Web Application Server
 - HTTP 기반으로 동작
 - Web Server 기능 포함(정적 리소스 제공 가능)
 - 프로그램 코드를 실행하여 **어플리케이션 로직** 수행
   - 동적 HTML, HTTP API(JSON), 서블릿, JSP, 스프링 MVC
 - Tomcat, Jetty 등

### 웹 시스템 구성 - Web Server + WAS + DB
 - WAS + DB 구조는 WAS가 담당하는 업무가 너무 많아짐(정적 리소스도 WAS가 처리하는 등)
 - 효율적인 리소스 관리
   - 정저 리소스가 많이 사용되면 Web Server 증설, 애플리케이션 리소스가 많이 사용되면 WAS 증설

### Servlet
 ```
서버 TCP/IP 대기, 소켓 연결 -> HTTP 요청 메시지 파싱해서 읽기 -> GET, POST 방식 구분, 호출 URL 확인 -> Content Type 확인 -> Content Type 에 따른 바디 내용 파싱
->  비즈니스 로직 실행 -> HTTP 응답 메시지 생성 -> TCP/IP 응답 전달, 소켓 종료
```
 - Servlet을 통해 `비즈니스 로직 실행` 부분을 제외한 부분을 제공

#### Servlet Container
 - 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함
 - 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명 주기 관리
 - 서블릿 객체는 **싱글톤으로 관리**
   - 고객의 요청이 들어올 때 마다 계속 객체를 생성하는 것은 비효율
   - 최초 로딩 시점에 서블릿 객체르 ㄹ미리 만들어두고 재활용
   - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
   - **공유 변수 사용 주의**
   - 서블릿 컨테이너 종료 시 함께 종료
 - **동시 요청을 위한 멀티 쓰레드 처리 지원**

### 동시 요청 - 멀티 쓰레드
 - 쓰레드 풀을 통해 쓰레드를 관리함(톰캣은 기본 값 200개)
 - request 요청이 올 때 마다 쓰레드 할당
 - WAS에서 멀티 쓰레드를 지원하니 개발자는 멀티 쓰레드 관련 코드를 신경쓰지 않아도 됨
   - 다만 멀티 쓰레드 환경이므로 싱글톤 객체(서블릿, 스프링 빈)은 주의해서 사용하도록 하자. 