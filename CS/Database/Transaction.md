## Transaction
 - 데이터베이스 내에서 수행되는 작업의 최소 단위로, 데이터베이스의 무결성을 유지하고 DB 상태를 변화시키는 기능을 수행
 - ACID(원자성 Atomicity, 일관성 Consistency, 고립성 Isolation, 지속성 Durability)
   - 원자성: transaction에 포함된 작업은 전부 수행되거나 전부 수행되지 말아야 한다(all or nothing)
   - 일관성: transaction이 성공적으로 완료하면 언제나 일관성 있는 데이터베이스 상태로 유지되는 것
     - 송금 전후 모드 잔액의 data type은 integer 이여야 한다.
   - 고립성: 여러 개의 transaction 이 수행될 때, 각 transaction은 다른 transaction의 작업에 끼어들지 못하도록 보장(concurrency control)
   - 지속성: 성공적으로 수행된 transaction은 데이터베이스에 영원히 반영되어야한(장애에 영향을 받지 않아야 함)

### Concurrency Control
 - 여러 개의 transaction이 한 개의 데이터를 동시에 갱신(update)할 때, 어느 한 transaction의 갱신이 무효화될 수 있음 => 갱신 손실
 - 동시성 제어를 통해 갱신손실을 미리 막을 수 있음(using Lock)

### Commit & Rollback
 - Commit: transaction 작업이 완료되었다고 확정하는 명령어
 - Rollback: transaction 작업 중 문제가 발생했을 때, 변경 사항을 취소하고 이전 Commit 상태로 되돌리는 것

### DeadLock
 - 여러 transaction들이 각각 자신의 데이터에 대하여 lock을 획득한 상태에서 상대방 데이터에 대하여 접근하고자 대기를 할 때 교차대기를 하게 되면서 영원히 기다리는 상태
 - 해결 방법
   - 예방기법: 각 transaction이 실행되기 전에 필요한 데이터를 모두 lock
     - lock 해야할 데이터가 많다면 transaction의 병행성을 보장하지 못할 수 있음
   - 회피기법: 자원을 할당할 때 time stamp를 사용하여 deadlock이 발생하지 않도록 회피
     - 먼저 시작한(time stamp) transaction이 데이터 대기(wait-die 방식) 또는 선점(wound-wait) 방식 
   - 탐지 회복 기법: Transaction이 실행되기 전에는 아무런 검사를 하지 않고, deadlock이 발생하면 이를 감지하고 회복시키는 방법
     - 트랜잭션 중 하나를 선택하여 롤백 하는 등의 조치