## Hadoop

## HDFS

## Hive

 - RDB의 데이터 베이스, 테이블과 같은 형태로 HDFS 에 저장된 데이터의 구조 정의하는 방법 제공
 - SQL과 유사한 HiveQL 쿼리 지원
 - 쿼리가 MapReduce Job 으로 실행됨
 - 배치 작업 위주로 사용됨

## Hive Metastore

 - Metadata 정보에 대하여 관리하는 Application
 - 별도의 RDBMS(MySQL 등)에 실제 Database, Table 에 대한 정보(location, description, schema[columnName, columnType] 정보 저장) 

## Spark

 - 인메모리 분산 처리 엔진
 - 메모리 작업하기 때문에 기존 DISK 접근 방식보다 훨씬 빠름
 - Lazy Evaluation -> 실제 action이 일어날 때만 Spark Job 할당하여 작업됨, action이 아닌 transformed 등의 작업은 Spark Job 으로 실행되지 않음
 - key word: 분산, RDD, Data Frame

## Livy

 - Spark 를 Rest API 로 사용가능

## Presto

 - 분산 쿼리 엔진
 - HDFS, Hive, S3, RDBMS 등의 다양한 소스 지원
 - 쿼리가 MapReduce Job 으로 실행되는 Hive, Pig 와 달리 Presto 에는 별도의 쿼리 실행 엔진 구현
   - 단계별 결과를 디스크에 쓰지 않고, 메모리에서 메모리로 데이터를 전달하는 구조이기 때문에 HDFS 에 저장된 데이터를 Hive 보다 빠르고 인터렉티브하게 분석 가능
   - 인터렉티브 쿼리 용도
   - Hive Connector 사용 가능, Hive Connector 사용해도 HiveQL 이나 Hive 의 쿼리 실행 엔진(MapReduce) 를 사용하지 않음
 - Coordinator(Client 요청, SQL 구문 파싱, 쿼리 플래닝, 쿼리 실행할 Worker 노드 조정), Worker(테스크 수행, 데이터 처리 & 테스트 결과 Client 전달)