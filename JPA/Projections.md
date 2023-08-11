## Projections
 - Entity 전체 조회를 하는 것이 아닌 특정 필드만 조회
 - 하나의 Entity에 대해서만(Join 없이) 사용하는 것은 괜찮음
 - Join이 들어가면 outer join 밖에 지원하지 않는 단점 + root 레벨의 entity가 아닌 경우 쿼리 최적화가 되지 않는(특정 필드만 select 하는 것이 아니라 모든 필드 select) 단점이 있음
