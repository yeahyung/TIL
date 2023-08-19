## Queue
 - FIFO(First In First Out)
 - Array-Based, List-Based 로 구현할 수 있음
   - Array-Based 인 경우, enqueue, dequeue 과정에서 메모리 낭비가 발생할 수 있기 때문에 `Circular queue` 형식으로 구현
 - 양쪽에서 enqueue, dequeue 가 가능한 `deque(double-ended queue)`, 우선순위가 높은 순서로 dequeue 할 수 있는 `priority queue`

### Priority Queue
 - Heap 자료구조(이진 완전트리)를 구현
   - Max Heap: 각 node에 저장된 값은 child node들에 저장된 값보다 크거나 같다 => root node에 저장된 값이 가장 크다
   - Min Heap: 각 node에 저장된 값은 child node들에 저장된 값보다 작거나 같다 => root node에 저장된 값이 가장 작다
   - 이진 완전트리에서의 Push, Pop
     - Push: leaf node에 추가 & parent node 와 비교하여 swap
     - Pop: root node pop & leaf node의 값을 root node에 추가 & child node 와 비교하여 swap
 - push, poop: O(log n) (tree의 높이가 log n이기 때문에)

## Stack
 - LIFO(Last In First Out)