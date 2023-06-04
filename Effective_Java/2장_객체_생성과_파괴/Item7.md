## 다 쓴 객체 참조를 해제하라.

 - 자바처럼 가비지 컬렉터를 지원한다고 해서 메모리 관리에 더 이상 신경쓰지 않아도 생각하면 안된다.
 - 객체들의 `다 쓴 참조`에서 메모리 누수가 발생할 수 있음
 - 아래와 같은 코드가 있다고 가정할 때 `elements[size]`는 더 이상 사용되지 않을 값임(=> 다 쓴 참조), 따라서 아래와 같이 참조를 해제해야한다.
   - ```
     public Object pop() {
        return elements[--size];
     }  
     ``` 
   - ```
     public Object pop() {
        Object result = elements[--size];
        elements[size] = null;
        return result;
     }
     ```
 - 하지만, 모든 객체를 다 쓰자마자 null 처리하는 것은 불필요함
 - 다 쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 `유효 범위(scope)` 밖으로 밀어내는 것
 - 객체 참조를 null 처리하는 일은 예외적인 경우여야 한다.
   - `Stack`과 같이 자기 메로리를 직접 관리하는 클래스(elements 배열로 저장소 풀을 만들어 원소들을 관리)
   - `Cache` 또한 메모리 누수를 일으키는 주범
   - `listener` 혹은 `callback` 역시 메모리 누수를 일으키는 주범
     - 클라이언트가 `callback`을 등록만 하고 명확히 해지하지 않으면, `callback`은 계속 쌓이게 됨
     - `callback`을 `약한 참조(weak reference)(WeakHashMap)`로 저장하면 가비지 컬렉터가 즉시 수거해간다.