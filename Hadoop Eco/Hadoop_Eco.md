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
```
spark.read => used to read a table stored in the Hive metastore
spark.table => used to read data from a variety of sources, such as files, databases, and cloud storage. 
```

## Livy

 - Spark 를 Rest API 로 사용가능
 - Spark Job Server 는 Spark Job Submit 에 특화되어 있는 반면, Livy 는 좀 더 interactive 하게 작동한다.
 ```
 Livy is a good choice if you want to enable interactive use of Spark through a REST API, while Spark Job Server is a good choice if you want to deploy and manage Spark applications on a cluster.
 ```

## Presto

 - 분산 쿼리 엔진
 - HDFS, Hive, S3, RDBMS 등의 다양한 소스 지원
 - 쿼리가 MapReduce Job 으로 실행되는 Hive, Pig 와 달리 Presto 에는 별도의 쿼리 실행 엔진 구현
   - 단계별 결과를 디스크에 쓰지 않고, 메모리에서 메모리로 데이터를 전달하는 구조이기 때문에 HDFS 에 저장된 데이터를 Hive 보다 빠르고 인터렉티브하게 분석 가능
   - 인터렉티브 쿼리 용도
   - Hive Connector 사용 가능, Hive Connector 사용해도 HiveQL 이나 Hive 의 쿼리 실행 엔진(MapReduce) 를 사용하지 않음
 - Coordinator(Client 요청, SQL 구문 파싱, 쿼리 플래닝, 쿼리 실행할 Worker 노드 조정), Worker(테스크 수행, 데이터 처리 & 테스트 결과 Client 전달)
 - Presto 에서는 Location이 S3 File인 경우(Not Directory) 에 대하여 지원하지 않음
   - Table 생성 시 `External Location must be a directory` 에러 발생
   - 이미 만들어진 Table인 경우, Select Query 시 0 row 조회
 - AWS Athena 에서 사용하는 것으로 보임

## Trino

 - 분산 쿼리 엔진
 - Presto 초기 멤버들이 페이스북에서 나와 개발한 것

## Sqoop

 - SQL to Hadoop
 - Hadoop 과 관계형 데이터베이스 간에 데이터를 전송할 수 있도록 설계됨
 - `sqoop import --connect jdbc:mysql://localhost:3306/hive --username yeaa -P --table TBLS --hive-import --create-hive-table --hive-table default.sqoop_test -m 1`
   - mysql 의 hive database 의 TBLS table 을 hive 의 default database 의 sqoop_test 로 import 한다.

## Impala

 - Apache Impala is the open source, native analytic database for Apache Hadoop.