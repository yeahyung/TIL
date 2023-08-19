## OSI 7 Layers vs TCP/IP 4 Layers
 - OSI 7 계층은 네트워크 통신을 표준화한 모델로, 통신 시스템을 7단계로 나누어 설명
 - 실무적으로 이용이 복잡하기에 실제 인터넷에서는 이를 단순화한 TCP/IP 4계층을 사용

### OSI 7 Layers
 - Application Layer
 - Presentation Layer
 - Session Layer
 - Transport Layer
 - Network Layer
 - Data Link Layer
 - Physical Layer

### TCP/IP 4 Layers
 - Application Layer(OSI 기준 5, 6, 7)(HTTP, FTP, DNS, Telnet, SMTP, SSH)
 - Transport Layer(OSI 기준 4)(TCP, UDP)
 - Internet Layer(OSI 기준 3)(IP, ICMP)
 - Network Interface Layer(OSI 기준 1, 2)(Ethernet)

### 캡슐화(Encapsulation) & 역캡슐화(Decapsulation)
 - 캡슐화란 통신 프로토콜의 특성을 포함한 정보를 Header에 포함시켜서 하위 계층에 전송하는 것
 - 통신 상대측에서 이러한 Header를 역순으로 제거하면서 원래의 Data를 얻는 과정을 역캡슐화