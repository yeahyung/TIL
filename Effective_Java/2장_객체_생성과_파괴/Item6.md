## 불필요한 객체 생성을 피하라.

 - `String s = new String("temp")` -> `String s = "temp"`
 - 생성자 대신 정적 팩터리 메서드를 통해 불필요한 객체 생성을 피할 수 있다.
   - `Boolean a = new Boolean("true")` -> `Boolean a = Boolean.valueOf("true")`
 - `String.matches` 는 정규 표현식을 확인할 수 있는 가장 편한 방법이지만, 반복해서 사용하기엔 성능이 좋지 않음
   - 내부적으로 Pattern 인스턴스를 만들어서 사용하며 해당 인스턴스는 사용된 후 버려짐(가비지 컬렉터 대상)
   - 따라서, 성능을 개선하기 위해선 Pattern 인스턴스를 캐싱하여 재사용하도록 하자.
   - ```
     private static final Pattern a = Pattern.compile("pattern");
     
     static boolean isPatternMatch(String s) {
        return a.matcher(s).matches(); 
     }
     ```
   - 위의 방식에서 `isPatternMatch` 메서드를 호출하지 않는다면, `a` 필드는 불필요하게 초기화된 꼴이다.
     - => 메서드가 호출될 때 필드를 초기화하는 `지연 초기화(lazy initialization)` 방식도 있으나 코드를 복잡하게 하고, 성능을 크게 개선하지 않기 때문에 권하지 않는다.
 - 불필요한 객체를 만들어내는 또 다른 예는 `auto boxing`이 있다.
   - ```
      Long sum = 0L;
      for (long i = 0; i < Integer.MAX_VALUE; i++) 
          sum += i;
      ```
   - 위의 코드는 불필요한 Long 인스턴스가 매우 많이 생성됨, `long sum = 0L`로 선언하면 해결되는 문제
   - => 박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의하자.
 - "객체 생성은 비싸니 피해야 한다"로 오해하면 안된다, 상황에 맞게 개발하자.