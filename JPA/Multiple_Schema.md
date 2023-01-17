## Multiple Schema
 - 하나의 Schema 가 아닌 여러개의 Schema 를 사용해야 할 경우가 있음
   - 사용자별 Schema 를 생성하여 사용(ex user001, user002, user003 ...)
 - `EmptyInterceptor` 를 상속한 Class 생성 후, `onPrepareStatement` method 를 override 한다.
   - `onPrepareStatement` 에서 SQL Query 를 원하는 Schema 로 replace 한 후, `super.onPrepareStatement` 를 실행
 - Entity 객체 내에 Table 이름뿐 아니라 Schema 이름도 함께 명시해야한다(replace 할 대상)
```
public class HibernateInterceptor extends EmptyInterceptor {
    @Override
    public String onPrepareStatement(String sql) {
        sql = sql.replaceAll("##schema##", schema);
        return super.onPrepareStatement(sql);
    }
}
```
```
@Entity
@Table(name = "MY_TAABLE", name = "##schema##")
public class tableEntity
...
```