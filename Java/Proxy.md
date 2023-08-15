## Dynamic Proxy
 - 런타임에 특정 인터페이스들을 구현하는 클래스 또는 인스턴스를 만드는 기술
 - Spring Data JPA, AOP, Mockito, Hibernate Lazy Initialization 등에서 사용됨
 - Interface에 대해서만 사용할 수 있음
 - Class에 대해서 사용하고 싶다면?
    - `CGlib`, `ByteBuddy`
    - 서브 클래스를 만들어 활용하는 방식이라 상속을 사용하지 못하는 경우엔 사용할 수 없다는 단점이 있음
