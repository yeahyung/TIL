## 클래스와 멤버의 접근 권한을 최소화하라.

 - 잘 설계된 컴포넌트는 내부 데이터와 내부 구현 정보를 외부 컴포넌트에 완벽히 숨기고 API를 통해서만 다른 컴포넌트와 소통한다 => 정보 은닉, 캡슐화
   - 정보 은닉의 장점
     1. 시스템 개발 속도를 높인다.
        1. 여러 컴포넌트를 병렬로 개발할 수 있음
     2. 시스템 관리 비용을 낮춘다.
        1. 각 컴포넌트를 빨리 파악하여 디버깅할 수 있고, 다른 컴포넌트로 교체하는 비용도 적어진다.
     3. 성능 최적화에 도움을 준다.
        1. 다른 컴포넌트에 영향을 주지 않고 해당 컴포넌트만 최적화할 수 있음
     4. 소프트웨어 재사용성을 높인다.
     5. 큰 시스템을 제작하는 난이도를 낮춰준다.
        1. 아직 완성되지 않은 상태에서도 개별 컴포넌트의 동작을 검증할 수 있음
   - 접근 제한자(private, protected, public)을 제대로 활용하는 것이 정보 은닉의 핵심!
   - 기본 원칙들
     - 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.
       - private: 멤버를 선언한 클래스에서만 접근할 수 있다.
       - default(package-private): 멤버가 소속된 페키지 안의 모든 클래스에서 접근할 수 있다.
       - protected: default의 접근 범위를 포함하며, 이 멤버를 선언한 클래스의 하위 클래스에서도 접근할 수 있다.
       - public: 모든 곳에서 접근할 수 있다.
     - public 클래스의 인스턴스 필드는 되도록 public이 아니어야 한다.
       - 단, 상수라면 `public static final` 필드로 공개해도 괜찮다.
       - 길이가 0이 아닌 배열은 변경 가능하니 `public static final 배열` 필드는 두면 안됨
         - 또한 이 필드를 반환하는 접근자 메서드를 제공해서도 안된다.
         - `public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));`
           - public 배열을 private 배열로 변경하고 public 불변 리스트를 추가한다.
         - `public static final THing[] values() { return PRIVATE_VALUES.clone(); }`
           - public 배열을 private 배열로 변경하고, 그 복사본을 반환하는 public 메서드를 추가한다(방어적 복사)