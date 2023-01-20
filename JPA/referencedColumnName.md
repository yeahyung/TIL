## referencedColumnName
 - Child Table 에서 Foreign Key 지정 시, 연관 테이블의 Primary Key 가 아닌 Key 를 지정해야 할 케이스가 있음, 이러한 경우 referencedColumnName 설정을 통해 해결 가능
 - `@JoinColumn(name = "PARENT", referencedColumnName = "NAME")`
   - 단, `referencedColumnName` 을 사용할 경우, 해당 Key 는 **PK가 아니기 때문에** Lazy Fetch 에서도 예상치 못한 select 쿼리가 추가적으로 실행됨
```
1. Child Table 을 조회한다고 가정해보자(Lazy Fetch)
2. 1번의 수행 결과로, select from child 의 단일 select 쿼리만 수행되었을 거라 예상되지만
select from parent 의 select 도 실행되는 것을 볼 수 있다.
3. child.getParentId() 를 하지 않았는데 왜 select from parent 쿼리도 수행이 되었을까?
4. Hibernate 에서는 Lazy Loading 을 지원하기 위해, 실제 엔티티 객체 대신에 데이터베이스 조회를 지연할 수 있는 가짜 객체(Proxy) 를 지원한다.
 - em.find -> 데이터베이스에 바로 조회
 - em.getReference -> 해당 객체를 사용하기 전까지 데이터베이스 조회를 미룬다, Proxy 객체 반환
5. 위의 Proxy 객체를 영속성 컨텍스트에서 초기화 하기 위해선 PK 값이 필요함, 따라서 select from parent 쿼리를 수행하여 PK 값을 가져온 것임
 - PK 값이 있어야 해당 Proxy 객체를 재활용할 수도 있음
```
 - 위와 같은 상황을 겪을 수 있기 때문에 PK 가 아닌 Key 가 Foreign Key 로 설정되어있다면, 굳이 JPA 에선 연관관계를 유지하지 말고 사용하도록 하자.