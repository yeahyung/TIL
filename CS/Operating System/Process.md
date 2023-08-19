## Process
 - 실행 파일(program)이 `memory`에 적재되어 `CPU`를 할당받아 실행되는 것

### Memory
 - Memory는 CPU가 직접 접근할 수 있는 컴퓨터 내부의 기억 장치
 - program이 CPU에서 실행되려면, 해당 내용이 memory에 적재된 상태여야 함

### Process의 Memory 영역
 - stack: 함수에서 쓰이는 지역 변수, 매개 변수 등
 - heap: runtime 중 동적 할당되는 메모리
 - data: 프로그램 전역 변수와 static 변수가 저장되는 메모리 영역
 - code: 실행한 코드(컴파일된 코드)가 저장되는 영역

## Multi Process
 - 2개 이상의 Process가 동시에(Concurrency, Parallelism) 실행되는 것
 - Concurrency(동시성)
   - CPU core가 1개일 때, 여러 process를 짧은 시간동안 번갈아 가면서 연산을 하게 되는 시분할 시스템(time sharing system)
 - Parallelism(병렬성)
   - CPU core가 여러개일 때, 각각의 core가 각각의 process를 연산함으로써 process가 동시에 실행되는 것

#### Context
 - 시분할 시스템에서는 한 process가 매우 짧은 시간동안 CPU를 점유하여 일정 부분의 명령을 수행하고, 다른 process에게 넘김
 - 그 후, 차례가 되면 다시 CPU를 점유하여 명령을 수행함
 - 이전에 어디까지 명령을 수행했고, register에는 어떤 값이 저장되어 있었는지에 대한 정보가 필요함
 - process가 `어떤 상태로 수행되고 있는지에 대한 정보`가 `context` 이며 PCB(Process Control Block)에 저장됨

##### PCB(Process Control Block)
 - 운영 체제가 프로세스를 표현한 자료 구조
 - Process State(new, running, waiting ...), Process Number, Program Counter(다음에 실행할 명령어의 주소), Registers, Memory Limits ... 등의 정보가 저장됨

##### Context Switch
 - 한 프로세스에서 다른 프로세스로 `CPU 제어권`을 넘겨주는 것
 - 이전의 프로세스 상태를 PCB에 저장하여 보관하고, 새로운 프로세스의 PCB를 읽어서 보관된 상태를 복구하는 작업이 이루어짐

## Thread
 - 한 Process 내에서 실행되는 동작(기능 function)의 단위, `독립적인 기능`을 수행함
   - 독립적으로 함수를 호출하기 때문에 stack memory 영역을 공유하지 않음
 - 각 Thread는 속해있는 Process의 Stack 메모리를 제외한 나머지 memory 영역을 공유할 수 있음
 - Multi Thread => 하나의 process가 동시에 여러개의 일을 수행할 수 있도록 해주는 것

### Stack memory & PC register
 - thread가 함수를 호출하기 위해서는 인자 전달, Return Address 전달, 함수 내 지역변수 저장 등을 위한 독립적인 stack memory 공간 필요
 - 한 process 내에서도 thread 끼리 context switch가 일어나기 때문에, pc register에 code address가 저장되어 있어야 이어서 실행할 수 있음

### Multi Process vs Multi Thread
 - 두 방법 모두 동시에 여러 작업을 수행한다는 측면에서 유사함
 - 메모리 구분이 필요할 때는 multi process 가 유리함
   - multi thread 의 경우 thread 간 자원을 공유하기 때문에 동기화 문제가 발생할 수 있음
 - context switching 이 자주 일어나고, 데이터 공유가 빈번한 경우, 자원을 효율적으로 사용해야 되는 경우에는 multi thread
   - multi process 에서의 context switching은 메모리도 완전히 바꾸고 pc register를 통해 현재 실행하고 있는 코드를 바꿔야하는데 mutlti thread 에서의 context switching 은 메모리는 그대로 두고 pc register 를 통해 수행하고 있는 코드만 바꾸면 됨
   - process 간의 통신(IPC) 보다 thread 간의 통신 비용이 적음
   - process 를 생성하고 자원을 할당하는 등의 system call 생략 가능하기 때문에 자원을 효율적으로 관리할 수 있음

#### Multi Process
 - process 간에 데이터를 주고 받는 방법은?
   - IPC(Inter Process Communication)
     - 공유 메모리 방식(shared memory)
       - process 가 kernel 에 공유 메모리 할당을 요청, 공유 메모리는 일반적인 메모리 접근으로 취급됨
       - 따라서 공유 메모리에 kernel 도움없이 접근 가능, 빠르다는 장점이 있지만 동기화 문제 발생 
     - 메시지 전달 방식(message passing)
       - system call을 사용하여 구현
       - kernel을 통해 send, receive 연산 제공
       - 메모리 공유보다 속도가 느리지만, 충돌을 회피할 필요가 없기 때문에 적은 양의 데이터를 교환하는데 유용함
       - `pipe`, `socket`, `message queue` 등이 있음

#### 동기화 문제 해결 방법
 - `mutex`
   - 한 개의 스레드만이 공유 자원에 접근할 수 있도록 하여 `경쟁 상황(race condition)`을 방지
   - 공유 자원을 점유하는 thread가 lock을 걸어 다른 thread는 unlock 상태가 될 때 까지 해당 자원에 접근할 수 없음
 - `semaphore`
   - S 개의 thread 만이 공유 자원에 접근할 수 있도록 제어하는 동기화 기법
 - `critical section`: `entry section` + `critical section` + `exit section`

#### Deadlock
 - 둘 이상의 thread가 각기 다른 thread가 점유하고 있는 자원을 서로 기 다릴 때, 무한 대기에 빠지는 상태
 - `상호 배제(mutual exclusion)`, `점유 대기(hold-and-wait)`, `비선점(no preemption)`, `순환 대기(circular wait)` 4가지 조건이 동시에 성립할 때 발생함
   - 상호 배제
     - 동시에 한 thread만 점유할 수 있는 상황
     - 다른 thread가 자원을 사용하려면 자원이 방출될 때까지 기다려야함
   - 점유 대기
     - thread가 자원을 보유한 상태에서 다른 thread가 보유한 자원을 추가로 기다리는 상황
   - 비선점
     - 다른 thread가 사용 중인 자원을 강제로 선점할 수 없는 상황
     - 자원을 점유하고 있는 thread에 의해서만 자원이 방출됨
   - 순환 대기
     - 대기중인 thread들이 순환 형태로 자원을 대기하고 있는 상황
 - 무시, 예방, 회피, 탐지-회복 4가 방법을 통해 해결할 수 있음
   - 무시
     - 아무런 조치 취하지 않음 -> 잘 발생하지 않고 해결 비용이 크기 때문에 그냥 무시
   - 예방
     - 교착 상태의 4가지 발생 조건 중 하나가 성립하지 않게 하는 것
       - 현실적으로 순환 대기 조건이 성립하지 않도록 하는 것이 가능하나 자원 효율성이 떨어지고 비용이 큼
   - 회피
     - thread가 앞으로 자원을 어떻게 요청할지에 대한 정보를 통해 순환 대기 상태가 발생하지 않도록 자원을 할당하는 방법
     - 자원 할당 그래프 알고리즘, 은행원 아 ㄹ고리즘 등을 사용하여 자원을 할당하여 deadlock 회피
   - 탐지-회복
     - 시스템 검사를 통해 deadlock 발생을 탐지하고 이를 회복함
     - 효율성이 떨어지고 비용이 큼