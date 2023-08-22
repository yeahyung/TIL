## JPA
 - Java Persistence API
 - JPA의 모든 데이터 변경은 `트랜잭션` 안에서 실행된다!

### JPA 구동 방식
 - 1. 설정 정보 조회(META-INF/persistence.xml)
 - 2. EntityManagerFactory 생성(하나만 생성됨)
 - 3. EntityManagerFactory 통해 EntityManager 생성 - 쓰레드간 공유 X(사용 후 폐기)

### Entity
 - JPA가 관리할 객체
 - `@Id`: 데이터베이스 PK와 매핑

### JPQL
 - SQL을 추상화한 객체 지향 쿼리 언어
 - 엔티티 객체를 대상으로 쿼리