## Index
 - Table 검색 성능을 높여주는 대표적인 방법
 - RDBMS 에서는 일반적으로 B+ Tree 구조로 된 index 사용

### 클러스터형 인덱스와 보조 인덱스(Clustering Index & Secondary Index)
 - Clustering Index: 특정 컬럼을 기본키(primary key)로 지정하면 자동으로 클러스터형 인덱스가 생성됨
   - 해당 컬럼 기준으로 정렬이 됨(id 값, 정렬이 필요하기 때문에 auto_increment 옵션을 대부분 사용함)
 - Secondary Index: `create index`를 통해 생성하거나 고유키(unique key)로 지정하면 보조 인덱스가 생성됨

### Index 장점
 - 검색 속도 향상

### Index 단점
 - 추가 저장 공간 필요
   - Index 자료구조를 위한 저장 공간
 - 느린 데이터 변경 작업
   - INSERT, UPDATE, DELETE 작업이 자주 발생하면 성능이 나빠질 수 있음
   - B+ Tree 구조의 Index는 데이터가 추가, 삭제될 때 마다 tree의 구조가 변경(Index 재구성) 될 수 있음

#### Index를 어느 Column에 사용하는 것이 좋은가?
 - where 절에서 자주 조회되고
 - 수정 빈도가 낮으며
 - 카디널리티는 높고
   - 데이터가 중복되지 않은 정도
 - 선택도가 낮은 컬럼
   - 데이터에서 특정 값을 잘 골라낼 수 있는 정도

#### 왜 B+ Tree 구조를 사용하는가?
 - Hash Table의 검색 시간복잡도는 O(1)인데 B+ Tree 는 O(log N) 인데 왜 Hash Table을 사용하지 않는가?
   - Hash Table은 값이 정렬되어있지 않기 때문에 Range Query에 매우 취약함
     - Leaf Node 끼리 서로 연결되어 있어 Range Query 지원 가능