## Persistence Context(영속성 컨텍스트)
 - `Entity`를 영구 저장하는 환경, 논리적인 개념
 - `Entity Manager`를 통해 영속성 컨텍스트에 접근

### 엔티티 생명주기
 - 비영속(new/transient)
   - 영속성 컨텍스트와 관계가 없는 **새로운** 상태
 - 영속(managed)
   - 영속성 컨텍스트에 **관리**되는 상태
 - 준영속(detached)
   - 영속성 컨텍스트에 저장되었다가 **분리**된 상태
   - `detach()`, `clear()`, `close()`
 - 삭제(removed)
   - **삭제**된 상태

### 영속성 컨텍스트의 이점
 - 1차 캐시
 - 동일성(identity) 보장
   - 1차 캐시로 반복 `REPEATABLE READ` 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공
 - 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
 - 변경 감지(Dirty Checking)
   - Entity와 스냅샷을 비교하여 변경이 이루어졌을 때 `쓰기 지연 SQL 저장소`에 등록
 - 지연 로딩(Lazy Loading)

#### Flush
 - 영속성 컨텍스의 변경 내용을 데이터베이스에 반영
   - 영속성 컨텍스트를 비우지는 않음
   - 데이터베이스에 쿼리를 날리는 것이지 실제 commit 한 것은 아님(flush 직접 호출, JPQL 쿼리 실행일 때)
 - flush 하는 법
   - em.flush()를 통해 직접 호출
   - 트랜잭션 커밋(플러시 자동 호출)
   - JPQL 쿼리 실행(플러시 자동 호출)
     - JPQL 쿼리를 실행하면 1차 캐시에서 조회하는 것이 아니라 DB에서 직접 조회하기 때문에
