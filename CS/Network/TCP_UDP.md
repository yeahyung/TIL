## TCP vs UDP
 - TCP(Transfer Control Protocol)
   - 연결성, 신뢰성 전송 프로토콜
   - 3 way handshaking 을 통해 두 호스트의 전송 계층 사이에 `논리적 연결`을 설립
     - connection setup(3 way handshaking) -> data transfer(데이터 전송) -> connection termination(4 way handshaking)
   - 오류제어, 흐름제어, 혼잡제어 실행
       - 오류제어: 훼손된 segment 감지 및 재전송, 손실된 segment 재전송, 순서가 맞지 않게 도착한 segment 정렬, 중복 segment 감지 및 폐기
         - TCP header의 checksum, 확인응답, 타임-아웃 등을 통해 수행
       - 흐름제어: 데이터를 보내는 속도와 데이터를 받는 속도의 균형을 맞추는 것
   - header가 더 크고 속도가 비교적 느림
 - UDP(User Datagram Protocol)
   - 비연결형, 비신뢰성 전송 프로토콜 / 세션 수립 과정이 없음
   - 오류제어, 흐름제어, 혼잡제어 제공 X
   - 적은 양의 오버헤드, 수신여부를 확인하지않아 속도가 빠름
   - 동영상 스트리밍

### 3 way handshake
 - TCP/IP 프로토콜로 통신하기 전, 정확한 정보 전송을 위해 상대방 컴퓨터와 세션을 수립하는 과정
    - 1. 클라이언트 -> 서버 SYN 패킷
    - 2. 서버 -> 클라이언트 SYN ACK 패킷
    - 3. 클라이언트 -> 서버 ACK 패킷 & 연결 

#### DDOS(Distributed Denial of Service)
 - 서버가 SYN ACK을 보내고 클라이언트로부터 ACK을 대기하는 것을 이용하여
 - 무수히 많은 SYN을 보내고 ACK을 보내지 않는 공격

### 4 way handshake
 - TCP 연결을 종료하는 Connection Termination
   - 1. 클라이언트 -> 서버 FIN 패킷
   - 2. 서버 -> 클라이언트 ACK 패킷
   - 3. 서버 Process가 정상 종료 시 -> 클라이언트 FIN 패킷
   - 4. 클라이언트 -> 서버 ACK 패킷