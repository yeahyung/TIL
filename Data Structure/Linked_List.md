## Linked List
 - Node라는 구조체로 이루어지고, Node는 데이터 값과 다음 Node의 address 값을 저장(마지막 Node의 next address는 null)
 - `메모리 상에는 비연속적`으로 저장되지만 Node의 next address를 통해 `논리적인 연속성`을 가진 자료구조
 - 데이터 추가되는 시점에 메모리 할당하기 때문에 효율적인 메모리 관리
   - 단, 데이터 값 + Next address를 추가적으로 저장해야하기 때문에 데이터 하나당 차지하는 메모리가 더 커지게 됨

### Linked List Time Complexity
 - 데이터 삽입/삭제: O(1)
   - Next Address 값만 변경하면 됨
   - 하지만, 삽입/삭제를 하려는 index까지 도달하는데 O(n)이 걸리기 때문에 실질적으로 O(n) 이라고 볼 수도 있음
 - 데이터 접근/검색: O(n)
   - random access를 지원하지 않음, 순차 접근(sequential access) 지원(무조건 head 부터 시작)