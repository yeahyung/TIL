## clone 재정의는 주의해서 진행하라.

 - clone 메서드는 사실상 생성자와 같은 효과를 낸다.
   - 따라서, 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
 - 복제 기능은 clone을 활용하기 보다는 `생성자` 또는 `팩터리`를 사용하는 게 낫다.
   - 단, 배열은 clone 메서드 방식이 가장 깔끔한 예외 케이스