## Private 생성자나 열거 타입으로 싱글턴임을 보증하라

 - 싱글턴을 만드는 방법
   1. 인스턴스에 접근할 수 있는 유일한 수단으로 `public static` 멤버를 마련한다.
      - `public static final Elvis INSTANCE = new Elvis();`
        - 해당 클래스가 싱글터임이 API에 명백히 드러나고, 코드가 간결해짐
   2. 정적 팩터리 메서드를 public `public static` 메서드로 제공한다.
      - `private static final Elvis INSTANCE = new Elvis();`
      - `public static Elvis getInstance() { return INSTANCE; }`
        - API를 바꾸지 않고도, 해당 클래스를 싱글턴이 아니게 변경할 수 있음
        - 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있음
        - 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있음
          - ex) Elvis::getInstance
        - 위의 장점들이 굳이 필요하지 않다면 public 필드 방식을 사용하라.
   - 공통: 생성자를 private 으로 감춰두고, reflection API에서 private 생성자를 접근할 수 있기 때문에 방어 로직을 생성자에 둔다.
   3. 원소가 하나인 Enum(열거) 타입을 선언
      - `public enum Elvis { INSTANCE; }`
        - public 필드 방식과 비슷하지만 더 간결하다
          - 만드려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면, 이 방법은 사용할 수 없음