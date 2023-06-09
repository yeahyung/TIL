## public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라.

 - 가변 필드를 직접 노출하지 말고, 접근자 메서드(getter, setter)를 사용하라.
 - package-private 클래스나 private 충첩 클래스에서는 종종 필드를 노출하는 편이 나을 때도 있다.