## RDB vs NoSQL

### RDB
 - 사전에 엄격하게 정의된 DB schema를 요구하는 table 기반 데이터 구조
 - schema로 인해 데이터 중복이 없기 때문에 데이터 update가 많을 때 유리
 - 데이터 구조가 변경될 여지가 없이 명확한 경우 유리

### NoSQL
 - table 형식이 아닌 비정형 데이터를 저장할 수 있도록 지원
 - SQL 지원하지 않고 transaction을 지원하지 않음
 - 데이터 중복으로 인해 데이터 update 시 모든 컬렉션에서 수정이 필요하기 때문에 update가 적고 조회가 많을 때 유리
 - 정확한 데이터 구조가 정해지지 않은 경우
 - 데이터 양이 매우 많은 경우(scale out 가능)