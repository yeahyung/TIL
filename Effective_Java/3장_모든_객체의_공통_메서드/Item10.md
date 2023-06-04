## equals는 일반 규약을 지켜 재정의하라.

 - 아래 상황 중 하나에 해당한다면 `equals` 메서드는 재정의하지 않는 것이 좋다.
   - `각 인스턴스가 본질적으로 고유하다`
   - `인스턴스의 '논리적 동치성(logical equality')을 검사할 일이 없다`
   - `상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다`
   - `클래스가 private이거나 package-private이고, equals 메서드를 호출할 일이 없다`
 - 아래 상황 일 때 `equals` 메서드를 재정의해라.
   - `객체 식별성(object identity)가 아니라 논리적 동치성(logical equality)를 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때`
     - 주로 값 클래스(Integer, String 등)이 해당한다.
     - 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스라면 equals를 재정의하지 않아도 됨
     - 같은 인스턴스가 2개 이상 만들어지지 않으니 논리적 동치성과 객체 식별성이 사실강 같은 의미이기 때문

### equals 메서드를 재정의할 때 지켜야할 규약

 - 반사성(reflexivity): null이 아닌 모든 참조 값 x에 대해, x.equals(x)는 true이다.
 - 대칭성(symmetry): null이 아닌 모든 참조 값 x, y에 대해 x.equals(y)가 true면 y.equals(x)도 true이다.
 - 추이성(transitivity): null이 아닌 모든 참조 값 x, y, z에 대해 x.equals(y)가 true이고, y.equals(z)도 true이면, x.equals(z)도 true이다.
 - 일관성(consistency): null이 아닌 모든 참조 값 x, y에 대해 x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.
 - null-아님: null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false이다.

### 주의 사항

 - equals를 재정의할 땐 hashCode도 반드시 재정의하자.
 - Object 외의 타입을 매개변수로 받는 equals 메서드를 선언하지 마라.
   - Object 외의 타입을 매개변수로 받는 equals는 `overriding`이 아니라 `overloading`이다.
 - `AutoValue` 프레임워크를 통해 equals 테스트를 하자.