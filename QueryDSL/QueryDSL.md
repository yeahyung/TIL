## QueryDSL
 - 쿼리를 `자바 코드`로 작성 / 문법 오류를 `컴파일 시점`에 확인할 수 있음
 - `동적 쿼리` 문제 해결

### Sub Query
 - `Expressions`를 통해 Sub Query 실행 가능
   - select, where 절에만 사용할 수 있고 from 에는 사용할 수 없음
     - 필요시 Native 쿼리 고려

### Projections
 - DTO 클래스 생성 후 `Setter`, `Field(reflection을 통한 field 값 주입)`, `Constructor`를 통한 방식이 있음
   - 일반적으로 `Constructor` 방식이 좋을 것 같으나 컴파일 오류를 잡을 수 없고 런타임 에러 발생함
 - `@QueryProjection`을 통한 QClass 생성
   - DTO 클래스 생성 후, 해당 메서드의 생성자 부분에 `@QueryProjection` 어노테이션 설정 & compileDsl 실행 시 QClass 생성됨
     - 컴파일 오류를 잡을 수 있다는 장점이 있음
     - 하지만  DTO 클래스가 QueryDSL 에 의존성을 갖게된다는 문제가 있음 / 순수한 DTO 가 아니다.
     - 어플리케이션에서 전반적으로 QueryDSL을 쓰면 `@QueryProjection`을 사용, 아니면 생성자로 처리하자.

### 벌크 연산
 - 모든 row 를 업데이트 하는 등의 작업은 dirty checking 으로 하기엔 성능 저하가 발생하기 때문에 벌크 연산을 통해 처리
 - 영속성 컨텍스트를 변경하지 않고 DB 에 바로 쿼리가 날아가기 때문에 영속성 컨텍스트(1차 캐시)와 DB 의 실제 데이터가 다르다
   - 따라서, 벌크 연산 후 데이터 조회를 하는 경우엔 영속성 컨텍스트를 지워줘야 한다(flush, clear / @Modifying)

### Paging 처리
 - `fetchResults()`를 통해 조회 쿼리 + count 쿼리를 동시에 수행할 수 있다.
   - 하지만, `fetchResults()`는 조회 쿼리와 완전히 동일한 쿼리에 대하여 count를 수행하기 때문에 불필요하게 복잡한 쿼리가 수행될 수 있다.
     - order by 가 포함된다거나, join 을 하지 않아도 총 결과수는 동일한다던가 등..
     - 이럴 경우엔 별도로 count 쿼리를 수행해야 한다(`fetchResults()`를 사용하지 않고)

#### Paging 최적화
 - `PageableExecutionUtils.getPage()` 메서드는 Paging 최적화 기능을 제공함
   - 첫 페이지에 모든 데이터가 조회되는 케이스라면(content size 가 page size 보다 작은 경우) 굳이 count 쿼리를 호출할 필요가 없음
   - 마지막 페이지를 조회하는 경우 offset + content size = total size