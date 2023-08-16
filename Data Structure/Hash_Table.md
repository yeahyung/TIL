## Hash Table
 - 효율적인 탐색을 위한 자료구조, key-value 쌍을 저장
 - hash function을 통해 key 값의 index 값을 구하여 value를 저장함
   - hash function을 통해 구한 key 값의 index 가 겹친다 => collision (collision이 적은 게 좋은 hash function)
     - separate chaining 또는 open addressing 을 통해 collision 해결
   
### Collision 해결 방법
 - `Open Addressing`
   - hash table 의 비어있는 slot(bucket)을 찾음, 빈 slot을 찾는 방법에 다라 `Linear Probing`, `Quadratic Probing`, `Double Hashing` 방법 등이 있음
     - `Linear Probing(+1, +2 칸 건너뛰어 slot 확인)`, `Quadratic Probing(+1^2, +2^2 칸 건너뛰어 slot 확인)`
       - 탐사 이동폭이 같기 때문에 특정 영역에 데이터가 집중적으로 몰리는 클러스터링 현상이 발생하는 단점이 있음
     - `Double Hashing`
       - 2개의 Hash Function 사용 / 하나는 최초의 해시값 획득에 사용, 두 번째는 충돌 발생할 때 탐사 이동폭(Linear Probing 의 경우 +1, +2)을 얻기 위해 사용
       - 탐사 이동폭이 다르기 때문에 클러스터링 문제가 발생하지 않음 
 - `Separate Chaining`
   - Linked List 이용, collision 발생 시 linked list에 노드(slot)을 추가하여 데이터 처장
   - worst case: O(n) (모든 key가 동일한 해시값을 갖게 되는 경우 = linked list 와 똑같음)