## try-finally 보다는 try-with-resources 를 사용하라.

 - 자바 라이브러리 `InputStream`, `OutputStream`, `java.sql.Connection` 등은 `close` 메서드를 호출해 직접 닫아줘야 한다.
   - 클라이언트가 놓치기 쉽기 때문에 상당수가 안전망으로 `finalizer`를 활용하고 있지만 `finalizer`는 믿을만하지 못함
 - 전통적으로 자원이 제대로 닫힘을 보장하는 수단으로 `try-finally`가 쓰인다.
   - ```
     BufferedReader br = new BufferedReader(new FileReader(path));
     try {
        return br.readLine();
     } catch(IOException e) {
     } finally {
        br.close();
     }
     ```
     - 위 코드에서 `OutputStream` 같은 자원을 하나 더 사용하게 된다면 `try-finally` 방식은 매우 지저분해진다.
     - 따라서 `try-with-resources`를 활용하자.
 - `try-with-resources` 구조를 사용하려면 해당 자원이 `AutoCloseable` 인터페이스를 구현해야 한다.
   - ```
     try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
     } catch(IOException e) {
     }
     ```