## Entity Mapping
 - 객체와 테이블 매핑: @Entity, @Table
 - 필드와 컬럼 매핑: @Column
 - 기본 키 매핑: @Id
 - 연관관계 매핑: @ManyToOne, @JoinColumn

### 객체와 테이블 매핑
 - @Entity가 붙은 클래스는 JPA가 관리
 - 기본 생성자 필수(최소 protected)
 - final, enum, interface, inner 클래스 사용 X

### @Enumerated
 - enum 타입을 컬럼에 매핑할 때 사용
 - EnumType.ORDINAL 사용 X
   - enum 순서를 데이터베이스에 저장하는 것이기 때문에 enum 값이 추가되면 장애 발생 가능성
   - EnumType.STRING 사용

### 기본 키 매핑
 - @GeneratedValue
   - Identity: 데이터베이스에 위임
     - 1차 캐시에 저장하기 위해선 ID 값을 알아야하는데 Identity의 경우 DB에 insert 하기 전까지 알 수 없음
     - 따라서, commit 할 때 insert 하는 것이 아니라 persist 할 경우 바로 DB에 insert 하여 ID 값을 얻어온 후 1차 캐시에 저장함
   - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용
     - DB에 SEQUENCE(ID 값)을 따로 저장하고, persist 할 때 마다 해당 값을 호출하여 얻어온다.
     - persist 할 때마다 SEQUENCE 값 조회? 성능 이슈는?
       - `ALLOCATION_SIZE` 활용, SEQUENCE 한 번 조회 시, 50개를 예약 사용한다는 의미
   - TABLE: 키 생성용 테이블 사용(SEQUENCE 전략을 지원하지 않는 DB도 있음, SEQUENCE 흉내내는 것)
     - SEQ를 저장할 Table을 별도 생성해서 각각의 Column으로 SEQ를 관리함
     - 모든 DB에 적용 가능하지만 성능 이슈로 잘 사용하지 않음
   - AUTO: 방언에 따라 자동 지정, 기본값