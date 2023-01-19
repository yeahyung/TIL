## Composite Key
 - JPA 에서 PK 를 지정하기 위해 @Id Annotation 사용이 필요하다.
 - 복합 키(다수의 PK)를 지원하기 위햇 `@IdClass`와 `@EmbeddedId` 두 가지 방법을 제공
 - @GeneratedValue 사용 불가

### @IdClass
 - 관계형 데이터베이스에 가까운 방식
 - Entity Class 에 PK인 Column 에 모두 @Id Annotation 설정 
 - PK인 Column 을 필드로 갖는 별도의 클래스 추가적으로 생성해야함
```
@IdClass(TestId.class)
@Entity
public class Test {
    @Id
    @Column(name = "TEST_ID")
    private String id;
    
    @Id
    @Column(name = "TEST_DATE")
    private LocalDateTime testDate;
    
    // ...
}

public class TestId implements Serializable {
    private String id;
    private LocalDateTime testDate;
    
    // constructors, getters and setters, equals & hashCode 구현
}
```

### @EmbeddedId
 - 객체지향에 가까운 방식
```
@Entity
public class Test {
    @EmbeddedId
    private TestId testId;
    
    // ...
}

@Embeddable
public class TestId implements Serializable {
    private String id;
    private LocalDateTime testDate;
    
    // constructors, getters and setters, equals & hashCode 구현
}
```

### JPQL 에서의 차이점
 - @IdClass -> `SELECT test.id FROM`
 - @EmbeddedId -> `SELECT test.testId.id FROM`

`If we're going to access parts of the composite key individually, we can make use of @IdClass, but in places where we frequently use the complete identifier as an object, @EmbeddedId is preferred.`