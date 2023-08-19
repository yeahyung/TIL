## Primary Key
 - Candidate Key 중 선택한 Main key
 - 각 row를 구분하는 유일한 열(column)
 - Null 값을 가질 수 없고 중복된 값을 가질 수 없음

## Foreign Key
 - 다른 table의 Primary key column 과 연결되는(참조되는) table column

### Super Key
 - 각 row를 유일하게 식별할 수 있는 하나 또는 그 이상의 속성들의 집합
 - 유일성: 하나의 key 값으로 특정 row만을 유일하게 찾아낼 수 있어야함
 
### Candidate Key
 - Super Key 중에서 더 이상 쪼개질 수 없는 Super Key를 Candidate Key 라고 한다.
 - 각 row를 유일하게 식별할 수 있는 최소한의 속성들
 - 최소성: 모든 row를 유일하게 식별하는데 꼭 필요한 속성만으로 구성되어야함

### Alternative Key
 - 후보키가 2개 이상일 경우, 기본키로 지정이 되지 못하고 남은 후보키들

### Composite Key
 - table에서 각 row를 식별할 수 있는 `두 개 이상의 column으로 구성된 candidate key`