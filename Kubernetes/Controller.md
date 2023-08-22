## Controller
 - 파드들을 관리하는 역할

### Replication Controller
 - 지정한 숫자만큼 파드가 항상 클러스터 안에서 실행되도록 관리

### Replicaset
 - Replication Controller 발전형
 - 집합 기반의 selector 지원
   - Replication Controller 에서는 셀렉터가 등호 기반(=, !=)만 지원
   - 집합 기반에서는 in, notin, exists 같은 연산자 지원
 
### Deployment
 - stateless 앱을 배포할 때 사용하는 가장 기본적인 컨트롤러
 - 파드 개수 설정, 앱 배포 시 롤링 업데이트, 이전 버전 롤백 등을 지원

### Daemonset
 - 클러스터 전체 노드에 특정 파드를 실행할 때 사용(모니터링 앱 등)

### Statefulset
 - 상태가 있는 파드들을 관리하는 것
   - volumn을 사용해서 파드를 재시작했을 때도 해당 데이터 유지 필요(DB 같은 것들, ES, Kafka ...)

### Job
 - 실행된 후 종료해야 하는 성격의 작업

### Cronjob
 - 잡을 시간 기준으로 관리하도록 생성