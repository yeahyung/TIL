## Elasticsearch

 - https://www.elastic.co/kr/downloads/past-releases#elasticsearch 에서 원하는 version 선택 & RPM 파일 설치(링크 주소 복사)
```
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-8.5.3-x86_64.rpm
rpm -ivh elasticsearch-8.5.3-x86_64.rpm

### Get the password
# The generated password for the elastic built-in superuser is : XJumOFSvud+qtlNMqnrU

### NOT starting on installation, please execute the following statements to configure elasticsearch service to start automatically using systemd
systemctl daemon-reload
systemctl enable elasticsearch.service

### You can start elasticsearch service by executing
systemctl start elasticsearch.service
```

```
curl -k -u 'elastic:XJumOFSvud+qtlNMqnrU' https://localhost:9200
```

```
# Set Elasticsearch Configuration
/etc/elasticsearch/elasticsearch.yml
```