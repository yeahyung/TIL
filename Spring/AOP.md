## AOP (Aspect Oriented Programming)
 - 공통 로직에 대하여 모듈화하여 사용

### AOP 구성 요소
 - Aspect: 여러 객체에 걸쳐 있는 공통 로직에 대하여 모듈화한 것
 - JoinPoint: 메서드 실행 또는 예외 처리와 같이 프로그램을 실행하는 동안의 지점
 - Advice: 특정 JoinPoint 에서 수행하는 작업
   - 실제 구현체 
 - Target: Aspect 를 적용할 곳(클래스, 메서드 등)
 - PointCut: JoinPoint 에 대하여 상세한 설정

### Advice Type
 - @Before: Advice that executes before a JoinPoint, but does not have the ability to prevent execution flow proceeding to the JoinPoint(unless it throws an exception)
   - interceptor 처럼 동작하며, @Before 의 반환값은 void 여야 한다. 
 - @AfterReturning: Advice to be executed after a JoinPoint completes normally
 - @AfterThrowing: Advice to be executed if a method exists by throwing an exception
 - @After: Advice to be executed regardless of the means by which a JoinPoint exists
   - 반환값은 void 여야 한다. 
 - @Around: Advice that surrounds a JoinPoint such as a method invocation.
   - 실행 전, 후 모두에서 동작 , 반환값은 Object 여야 한다.