## RPC
 - 원격 프로시저 호출(Remote Procedure Call)
 - 원격(서버)에서 실행중인 프로세스의 Method 를 클라이언트 입장에서 마치 자신의 프로세스에 있는 Method 를 호출하는 것 처럼 사용 가능

### Stub
 - 서버와 클라이언트는 다른 주소 공간을 사용하고 있기 때문에 Method 호출에 사용되는 매개변수를 변환하는 작업이 필요함 
 - 이에 대한 처리를 담당하는 것이 Stub

### Marshalling
 - 데이터를 바이트로 쪼개어 TCP/IP 같은 통신 채널을 통해 전송될 수 있는 형태로 변환해주는 과정 

## Unmarshalling
 - 바이트로 쪼개어진 데이터를 원래 형태로 복원하는 과정

### Framework

 - gRPC(구글에서 만든 RPC 프레임워크, Protocol Buffer 기반 Serializer + HTTP/2)
 - Thrift(페이스북에서 만든 RPC 프레임워크)
 - Tutorial: https://github.com/yeahyung/rpc-tutorial

### 장점
 - JSON 에 비해 데이터의 크기가 월등히 작아 네트워크 비용 감소 & 크기가 작기 때문에 직렬화/역질렬화 속도가 빠르다
   - 데이터의 필드 이름에 대해 저장하지 않고 태그로 지정하여 사용한다.
   - { "userName": "yea" } -> 기존의 JSON 은 userName 에 대하여 8 bytes(UTF-8 기준) 가 소모되지만, protocol buffer 를 사용했을 경우 tag + data type 정보에 대해서 단 1 byte 로만 저장할 수 있음
   - https://chacha95.github.io/2020-06-16-gRPC2/ (Proto와 JSON 참조)
 - 클라이언트 입장에서 Request, Response Param 이 명확하다.

### 단점
 - 구현의 어려움, 불편함(Server Method 추가/수정 시 Client 입장에서의 generated code & impl 수정 필요)