
CentOS7 에 ELK Stack을 구축해보기(CentOS같은 경우는 네이버 클라우드의 서버 상품을 이용했습니다)


ElasticSearch: Apache 오픈소스로 분산형 검색 시스템에 DB의 역할까지 가능

Logstash: 여러 가지 로그 파일, 또는 JSON 포멧의 파일을 ElasticSearch로 전송

Kibana: ElasticSearch에 저장된 데이터를 사용자에게 시각화하여 제공

Filebeat: 로그를 저장하는 파일을 지속적으로 보며, 로그가 생성될 경우, Logstash로 전송


즉, Filebeat를 통해 로그를 수집하고, 이를 Logstash를 통해 JSON 형태로 변환한 후, ElasticSearch를 통해 로그를 검색 및 분석, Kibana를 통해 이를 시각화한다.



ElasticSearch의 경우, java 기반이기 때문에 java가 설치되어 있어야 한다. 아래 명령어를 통해 java 설치


﻿$sudo yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel

ElasticSearch 설치하기

/etc/yum.repos.d/ 에 elasticsearch.repo 추가하기


﻿vi /etc/yum.repos.d/elasticsearch.repo﻿


[elasticsearch-7.x]
name=Elasticsearch repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md

만약 Elasticsearch 6버전을 다운받고 싶은 경우, 7을 6으로 변경하면 된다.


sudo yum -y install elasticsearch

Logstash 설치하기

/etc/yum.repos.d/logstash.repo 추가하기


vi /etc/yum.repos.d/logstash.repo


[logstash-7.x]
name=Elastic repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md


sudo yum -y install logstash


Kibana 설치하기

/etc/yum.repos.d/kibana.repo 추가하기


vi /etc/yum.repos.d/kibana.repo


[kibana-7.x]
name=Kibana repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md


sudo yum -y install kibana

Kibana를 설치하였으면 연결할 Elasticsearch를 설정해 주어야 한다.


sudo vi /etc/kibana//kibana.yml

elasticsearch.url: "http://127.0.0.1:9200" 으로 변경


Filebeat 설치

/etc/yum.repos.d/elastic.repo 추가


vi /etc/yum.repos.d/elastic.repo


[elastic-7.x] 
name=Elastic repository for 7.x packages 
baseurl=https://artifacts.elastic.co/packages/7.x/yum 
gpgcheck=1 
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch 
enabled=1 
autorefresh=1 
type=rpm-md


sudo yum -y install filebeat

filebeat와 logstash 연결은 추후 추가하도록 하겠습니다.



Kibana에 접속하기 위해서는 공인 IP를 할당받아야 합니다. 공인 IP를 할당받으면 외부에서 접근할 수 있도록 Elasticsearch를 수정해야 합니다.


hostname -i 

위의 명령어를 실행하여 얻은 사설 IP 주소를 복사합니다.


sudo vi /etc/elasticsearch/elasticsearch.yml


-------------------network---------------------에 있는
network.host: 
해당 부분에 위에서 얻은 사설 IP 주소를 적어줍니다.

-------------------Discovy----------------------에 있는
discovery.seed_hosts: [" "]
cluster.initial_master_nodes: [" "]
부분 역시 위에서 얻은 사설 IP 주소를 적어줍니다.


sudo systemctl start elasticsearch

Elasticsearch를 실행하고, 주소창에 공인IP주소:9200을 적고 실행하면


image.png
대표사진 삭제


다음과 같은 화면을 볼 수 있다면 성공적으로 Elasticsearch가 실행되었습니다.


이제 Kibana의 설정을 변경하여 보겠습니다.


sudo vi /etc/kibana/kibana.yml


server.host: "0.0.0.0"
elasticsearch.url: "위에서 얻은 사설 IP:9200"


sudo systemctl start kibana

실행 후, 주소창에 공인IP:5601을 적고 실행 시


﻿
