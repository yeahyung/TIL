## MySQL의 격리 수준
 - 트랜잭션의 격리 수준(Isolation Level)이란, 여러 트랜잭션이 동시에 처리될 때 특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회하는 데이터를 볼 수 있게 허용할지 말지를 결정하는 것
 - `READ UNCOMMITTED`, `READ COMMITTED`, `REPEATABLE READ`, `SERIALIZABLE`
   - `READ UNCOMMITTED`, `SERIALIZABLE` 은 거의 사용되지 않음, 뒤로 갈수록 트랜잭션 간의 데이터 격리(고립) 정도가 높아지며 동시 처리 성능도 떨어짐 

### READ UNCOMMITTED
 - 각 트랜잭션의 변경 내용이 `COMMIT`, `ROLLBACK` 여부에 상관없이 다른 트랜잭션에서 보임
   - INSERT 후 COMMIT 하지 않았는데 다른 트랜잭션에서 조회가 가능함 => Dirty Read
     - 문제가 생겨서 INSERT를 ROLLBACK했으면? 이러한 이유로 잘 사용하지 않음

### READ COMMITTED
 - Oracle DBMS의 Default Isolation Level
 - 트랜잭션에서 데이터를 변경했더라도 `COMMIT`이 완료된 데이터만 다른 트랜잭션에서 조회할 수 있음
   - 아직 커밋되지 않은 변경된 데이터를 조회 시 언두 로그에서 조회
 - `NON-REPEATABLE READ` 부정합 문제가 발생할 수 있음
   - 사용자 A, B 가 있다고 가정
     - B 가 Transaction 시작 후 name이 "Toto"인 사용자 조회 => 결과 없음
     - A 가 Transaction 시작 후 name이 "Toto"인 사용자 Insert & Commit
     - B 가 name이 "Toto"인 사용자 다시 조회 => 결과 있음
     - => 하나의 트랜잭션 내에서 같은 SELECT 쿼리를 실행했을 때는 항상 같은 결과를 가져와야 한다는 `REPEATABLE READ` 정합성에 어긋남
   - 하나의 트랜잭션에서 계좌 조회, 다른 트랜잭션에서 입금/출금 진행할 때, 계좌 조회 트랜잭션에서 계좌를 조회할 때 마다 다른 결과를 가져올 수 있음
   - DB Isolation Level이 `READ COMMITTED` 이여도 JPA에서는 1차 캐시 활용으로 인하여 `REPEATABLE READ` 가 발생하지 않는다.

### REPEATABLE READ
 - MySQL의 InnoDB 스토리지 엔진에서 기본으로 사용되는 격리 수준
 - `REPEATABLE READ`는 이 `MVCC`를 위해 언두 영역에 백업된 이전 데이터를 이용해 동일 트랜잭션 내에서는 동일한 결과를 보여줄 수 있게 보장한다.
   - 사실 `READ COMMITTED`도 언두 영역에 백업된 데이터를 보여주는데 차이점은 몇 번째 이전 버전까지의 데이터를 보여줄 것이냐에 있음
   - InnoDB의 모든 트랜잭션은 고유한 트랜잭션 번호를 가지며, 언두 영역에 백업된 모든 레코드에는 변경을 발생시킨 트랜잭션의 번호가 포함되어 있음
     - `REPEATABLE READ` 격리 수준에서는 `MVCC`를 보장하기 위해 실행 중인 트랜잭션 가운데 가장 오래된 트랜잭션 번호보다 트랜잭션 번호가 앞선 언두 영역의 데이터는 삭제할 수 없음
     - 사용자 A, B 가 있다고 가정
       - 과거 트랜잭션 번호(6)에 의해 name "Toto" 데이터가 저장됨
       - B 가 Transaction 시작(트랜잭션 번호: 10) 후 name이 "Toto"인 사용자 조회 => 1건 조회
       - A 가 Transaction 시작(트랜잭션 번호: 12) 후 name이 "Toto"인 사용자 "Lala"로 변경
       - B 가 name이 "Toto"인 사용자 다시 조회 => 언두 영역에서 트랜잭션 번호가 10보다 작은 데이터만 보이게 되므로 그대로 1건 조회
   - 하지만 `PHANTOM READ`와 같은 부정합이 발생할 수 있음
     - B 가 조회(SELECT ... FOR UPDATE)
     - A 가 새로운 Data Insert
     - B 가 조회 시, A 가 Insert 한 데이터 조회(SELECT ... FOR UPDATE) => 처음 조회한 결과와 두번째 조회한 결과가 다름
     - `SELECT ... FOR UPDATE` 쿼리는 레코드에 쓰기 잠금을 걸어야 하는데 언두 레코드에 잠금을 걸 수 없기 때문에 조회되는 레코드는 언두 영역의 변경 전 데이터를 가져오는 것이 아니라 현재 레코드 값을 가져오게 됨
     - 쓰기 잠금을 걸 때 발생하지 일반적인 SELECT 에서는 발생하지 않음
       - 넥스트 키 락, 갭 락을 사용해 해결할 수 있음(아예 INSERT를 할 수 없음)

### SERIALIZABLE
 - 가장 엄격한 격리 수준, 성능도 떨어짐
 - 일반적으로 InnoDB 테이블에서 순수한 `SELECT` 작업은 아무런 레코드 잠금도 설정하지 않지만 `SERIALIZABLE` 격리 수준이 설정되면 읽기 작업도 공유 잠금(읽기 잠금)을 획득해야 한다.