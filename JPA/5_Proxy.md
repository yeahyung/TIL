## Proxy
 - em.find(): 데이터베이스를 통해 실제 엔티티 객체 조회
 - em.getReference(): 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
 - 실제 클래스를 상속 받아서 만들어지기 때문에 실제 클래스와 겉 모양이 같고 사용자 입장에서는 진짜 객체인지 프록시 객체인지 구분할 필요 없음

### Proxy 특징
 - Proxy 객체는 실제 객체의 참조(target)를 보관
 - Proxy 객체 호출 시, Proxy 객체는 실제 객체의 method 호출
 - 초기화
   - client -> member.getName() -> 영속성 컨텍스트에 proxy 초기화 요청 -> DB 조회 -> 실제 Entity 생성 -> target을 실제 Entity로 설정
 - 처음 사용할 때 한번만 초기화 됨
 - 원본 엔티티를 상속받기 때문에 타입 체크 시 `==`는 실패, `instance of` 사용해야함
 - 영속성 컨텍스트에 찾는 entity가 이미 있으면 em.getReference() 호출 시 Proxy가 아닌 실제 entity 반환
 - 준영속 상태일 때, 프록시 초기화하면 에러 발생(LazyInitializationException)

### 즉시 로딩과 지연 로딩
 - Member를 조회할 때 Team도 함께 조회해야 할까? (Team을 조회할 때 Member를 조회해야 할까?)
 - `@ManyToOne(fetch = FetchType.EAGER)` 즉시 로딩을 사용해서 함께(join) 조회하는 법
 - `@ManyToOne(fetch = FetchType.LAZY)` 지연 로딩을 사용해서 프록시로 조회하는 법
   - 가급적 지연 로딩을 사용
   - 즉시 로딩을 적용하면 예상치 못한 SQL 발생, JPQL 에서 N + 1 문제 일으킴(Team 조회 시, Team에 해당하는 모든 Member가 조회됨)
   - `@ManyToOne`, `@OneToOne`은 기본이 즉시 로딩이기 때문에 지연 로딩으로 설정 필요
   - 하지만 대부분 비즈니스 로직에서 Member와 Team을 함께 사용한다면 LAZY 로딩일 때 SELECT 쿼리가 따로 따로 2번 나가게 된다. 이때는 즉시 로딩을 쓸까?
     - 그래도 쓰지마라, `fetch join`, `entity graph`, `batch size`를 통해 해결하자.
   - 지연 로딩도 N + 1 이 발생함
     - Team 조회 시, 단일 쿼리만 실행되지만 `for(Member member : team.getMember()) member.getName()` 실행 시 N + 1
     - 역시 `fetch join`, `entity graph`, `batch size`를 통해 해결하자.

### Fetch Join

### Entity Graph

### Batch Size

### 영속성 전이(CASCADE)
 - 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만드는 것
   - ex) 부모 엔티티 저장 시 자식 엔티티도 함께 저장

### 고아 객체
 - 부모 엔티티와 연관 관계가 끊어진 자식 엔티티
 - 참조하는 곳이 하나일 때 사용해야함
   - `@OnetoOne`, `@OneToMany` 에서만 사용 가능
 - 부모를 제거하면 자식은 고아가 됨, 따라서 고아 객체 제거 기능 활성화 = `CascadeType.REMOVE`와 동일한 동작

