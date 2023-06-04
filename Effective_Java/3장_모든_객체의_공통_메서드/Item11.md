## equals를 재정의하려거든 hashCode도 재정의하라.

 - equals 비교에 사용되는 정보가 변경되지 않았다면 hashCode 메서드는 일관된 값을 반환해야 한다.
 - equals가 두 객체를 같다고 판단했다면, hashCode도 같은 값을 반환해야 한다.
   - equals가 두 객체를 다르다고 판단했더라도, hashCode도 다른 값을 반환할 필요는 없다.
   - 단, hashCode가 다른 값을 반환해야 해시 테이블의 성능이 좋아진다.
 - 