### Java Garbage Collection

#### 가비지 컬렉션 과정

 - `stop-the-world` => `GC`를 실행하기 위해 `JVM`이 어플리케이션 실행을 멈추는 것
   - `stop-the-world`가 발생하면 `GC`를 실행하는 쓰레드를 제외한 나머지 쓰레드는 작업을 멈춘다.
   - `GC` 튜닝 => `stop-the-world` 시간을 줄이는 것!
 - 메모리를 명시적으로 해제하는 방법
   - null 지정
   - System.gc() 호출 => 시스템 성능에 매우 큰 영향을 끼치므로 하면 안됨

#### weak generation hypothesis

 - 가비지 컬렉터는 아래 두 가지 가정(전제 조건) 하에 만들어짐
   - 대부분의 객체는 금방 접근 불가능한 상태(unreachable)가 된다.
   - 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재한다.
 - 위 가정의 장점을 최대한 살리기 위해 JVM에서는 `Young` 영역,`Old` 영역으로 공간을 나누었다.
   - `Young` 영역: 새롭게 생성한 객체의 대부분이 위치
     - 대부분의 객체가 금방 접근 불가능한 상태가 되기 때문에 많은 객체가 `Young` 영역에 생성되었다가 사라짐
     - `Young` 영역에서 객체가 사라질 때 `Minor GC`가 발생한다고 말함
   - `Old` 영역: 접근 불가능 상태로 되지 않아 `Young` 영역에서 살아남은 객체가 `Old` 영역으로 복사된다.
     - `Young` 영역보다 크게 할당하며, 크기가 큰 만큼 `Young` 영역보다 `GC`는 적게 발생함
     - `Old` 영역에서 객체가 사라질 때 `Major GC(Full GC)` 가 발생한다고 말함

#### Young 영역의 구성

 - `Young` 영역은 3개의 영역으로 나뉜다.
   - `Eden` 영역
   - `Survivor` 영역 2개

#### Young 영역에 대한 GC

   - 새로 생성한 대부분의 객체는 `Eden` 여역에 위치한다.
   - `Eden` 영역에서 `GC`가 한 번 발생한 후 살아남은 객체는 `Survivor` 영역 중 하나로 이동된다.
   - `Eden` 영역에서 `GC`가 발생하면 이미 살아남은 객체가 존재하는 `Survivor` 영역으로 객체가 계속 쌓인다.
   - 하나의 `Survivor` 영역이 가득 차게 되면 그 중에서 살아남은 객체를 다른 `Survivor` 영역으로 이동한다. 그리고 가득 찬 `Survivor` 영역은 아무 데이터도 없는 상태로 된다.
   - 이 과정을 반복하다가 계속해서 살아남아있는 객체는 `Old` 영역으로 이동하게 된다.
     - `Survivor` 영역 중 하나는 반드시 비어있는 상태로 남아 있어야 한다!(두 영역에 모두 데이터가 존재하면 비정상적인 상황이라고 보면 됨)

#### Old 영역에 대한 GC

 - `Old` 영역은 기본적으로 데이터가 가득차면 `GC`를 실행한다.
 - `GC` 방싱에 따라 처리 절차가 다르며 대표적인 5가지 방식이 있다.
   - `Serial GC`, `Parallel GC`, `Parallel Old GC(Parallel Compacting GC)`, `Concurrent Mark & Sweep GC(CMS)`, `G1(Garbage First) GC`

##### Serial GC(-XX:+UseSerialGC)

 - `mark-sweep-compact` 알고리즘 사용
   - `Old` 영역에 살아 있는 객체를 식별(mark)
   - `heap` 앞 부분부터 확인하여 살아 있는 것만 남김(sweep)
   - 각 객체들이 연속되게 쌓이도록 `heap`의 가장 앞 부분부터 채워 객체가 존재하는 부분과 객체가 없는 부분으로 나눔(compaction)
 - 적은 메모리와 CPU 코어 개수가 적을 때 적합한 방식

##### Parallel GC(-XX:+UseParallelGC)

 - `Serial GC`와 기본적인 알고리즘은 같으나, `Serial GC`는 `GC`를 처리하는 쓰레드가 하나인 것에 비해 `Parallel GC`는 여러개의 쓰레드를 사용하여 더 빠르게 처리함
 - 메모리가 충분하고 코어의 개수가 많을 때 적합함
 - `Throughput GC`라고 하기도 함

https://d2.naver.com/helloworld/1329
##### Parallel Old GC(Parallel Compacting GC)



##### Concurrent Mark & Sweep GC(CMS)



##### G1(Garbage First) GC



### JVM Internal
