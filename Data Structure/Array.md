## Array
 - Array는 연관된 data를 `메모리상에 연속적이며 순차적`으로 `미리 할당된 크기`만큼 저장하는 자료 구조
 - 고정된 저장 공간
 - 순차적인 데이터 저장

### Array Operation의 Time Complexity
 - 조회: O(1) - random access
 - 마지막 인덱스에 추가(append), 삭제: O(1)
 - 삽입, 삭제, 탐색: O(n)

### 장점
 - 조회, 추가 빠르다.

### 단점
 - 고정된 저장 공간을 사용하기 때문에 `메모리 낭비` 혹은 추가적인 `overhead`가 발생할 수 있음
 - `메모리 낭비`: size 100 짜리 Array 생성 후, 10만큼만 사용
 - `overhead`: size 100 짜리 Array 생성 후, 100개 초과의 element 삽입 시 size 200(ex) 짜리 Array 생성 후 기존 Array에서 새로운 Array로 모두 copy함 => Dynamic Array 

## Dynamic Array
 - Array의 경우 size가 고정되어있기 때문에 선언시에 설정한 size보다 많은 개수의 data가 추가되면 저장할 수 없음
 - Dynamic Array는 저장 공간이 가득 차게되면 resize를 하여 유동적으로 size를 조절하여 데이터를 저장하는 자료구조
 - resize 방식: 원래 있던 배열보다 더 큰 사이즈의 배열 생성 후, 데이터를 옮김
 - 데이터 추가(append)할 때의 Time Complexity: 
   - 추가할 때: O(1)
   - resize할 때: O(n) (기존 데이터를 일일이 옮겨야하기 때문에)

### Array vs Linked List
 - 메모리에 저장되는 방식과 이에 따른 operation에 의 연산 속도(time complexity)
 - 메모리상 연속적 vs 메모리상 비연속적 + 논리적 연속성 구성
 - Array는 Compile 단계에서 memory allocation이 일어남 -> Static Memory Allication이라 하며 Stack Memory 영역에 할당
 - Linked List는 runtime 단계에서 새로운 node가 추가될 때 마다 memory allocation이 일어남 => Dynamic Memory Allocation => Heap 메모리 영역에 할당
 - 얼마만큼의 데이터를 저장할지 미리 알고 있고, 조회를 많이 한다면 Array
 - 몇개의 데이터를 저장할지 불확실하고 삽입 삭제가 잦다면 Linked List 

### Dynamic Array vs Linked List
 - 데이터 접근이 빠름, 데이터 추가가 빠름
 - 맨 끝이 아닌 곳에 insert or remove 할 때 느림, resize 시 낮은 performance + 낭비되는 메모리 공간