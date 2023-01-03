## OpenSearch

 - https://opensearch.org/downloads.html

```
wget https://artifacts.opensearch.org/releases/bundle/opensearch/1.3.2/opensearch-1.3.2-linux-x64.rpm
wget https://artifacts.opensearch.org/releases/bundle/opensearch-dashboards/1.3.2/opensearch-dashboards-1.3.2-linux-x64.rpm

rpm -ivh opensearch-1.3.2-linux-x64.rpm
rpm -ivh opensearch-dashboards-1.3.2-linux-x64.rpm

# Root CA(Do it on Admin Node)
openssl genrsa -out root-ca-key.pem 2048
openssl req -new -x509 -sha256 -key root-ca-key.pem -subj "/C=CA/ST=ONTARIO/L=TORONTO/O=ORG/OU=UNIT/CN=ROOT" -out root-ca.pem -days 3650

# Admin cert(Do it on Admin Node)
openssl genrsa -out admin-key-temp.pem 2048
openssl pkcs8 -inform PEM -outform PEM -in admin-key-temp.pem -topk8 -nocrypt -v1 PBE-SHA1-3DES -out admin-key.pem
openssl req -new -key admin-key.pem -subj "/C=CA/ST=ONTARIO/L=TORONTO/O=ORG/OU=UNIT/CN=ses-admin" -out admin.csr
openssl x509 -req -in admin.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -sha256 -out admin.pem -days 730

# Node 별 Certificate(Do it on Every Node)
openssl genrsa -out node-key-temp.pem 2048
openssl pkcs8 -inform PEM -outform PEM -in node-key-temp.pem -topk8 -nocrypt -v1 PBE-SHA1-3DES -out node-key.pem
openssl req -new -key node-key.pem -subj "/C=CA/ST=ONTARIO/L=TORONTO/O=ORG/OU=UNIT/CN=$HOST_NAME" -out node.csr
openssl x509 -req -in node.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -sha256 -out node.pem -days 3650

# /etc/hosts 파일 수정
# 각 노드별 ip hostname 추가
172.16.0.8 search001
172.16.0.9 search002
172.16.0.10 search003
172.16.0.11 search004

# Set OpenSearch Configuration (/etc/opensearch/opensearch.yml)
plugins.security.ssl.transport.pemcert_filepath: node.pem
plugins.security.ssl.transport.pemkey_filepath: node-key.pem
plugins.security.ssl.transport.pemtrustedcas_filepath: root-ca.pem
plugins.security.ssl.transport.enforce_hostname_verification: false
plugins.security.ssl.http.enabled: true
plugins.security.ssl.http.pemcert_filepath: node.pem
plugins.security.ssl.http.pemkey_filepath: node-key.pem
plugins.security.ssl.http.pemtrustedcas_filepath: root-ca.pem
plugins.security.allow_unsafe_democertificates: true
plugins.security.allow_default_init_securityindex: true
plugins.security.authcz.admin_dn:
  - CN=ses-admin,OU=UNIT,O=ORG,L=TORONTO,ST=ONTARIO,C=CA
plugins.security.nodes_dn:
   - 'CN=search001,OU=UNIT,O=ORG,L=TORONTO,ST=ONTARIO,C=CA'
   - 'CN=search002,OU=UNIT,O=ORG,L=TORONTO,ST=ONTARIO,C=CA'
   - 'CN=search003,OU=UNIT,O=ORG,L=TORONTO,ST=ONTARIO,C=CA'
   - 'CN=search004,OU=UNIT,O=ORG,L=TORONTO,ST=ONTARIO,C=CA'

# Initialize Security
/usr/share/opensearch/plugins/opensearch-security/tools/securityadmin.sh -cd /usr/share/opensearch/plugins/opensearch-security/securityconfig/ -icl -nhnv -cert /etc/opensearch/admin.pem -cacert /etc/opensearch/my-root-ca.pem -key /etc/opensearch/admin-key.pem -h search001

### NOT starting on installation, please execute the following statements to configure opensearch service to start automatically using systemd
systemctl daemon-reload
systemctl enable opensearch.service
systemctl enable opensearch-dashboards.service

systemctl start opensearch.service
systemctl start opensearch-dashboards.service
```

```
curl -XGET https://localhost:9200 -u 'admin:admin' --insecure
```

```
# Set OpenSearch Configuration
/etc/opensearch/opensearch.yml

# Set OpenSearch Security Configuration
/usr/share/opensearch/plugins/opensearch-security/securityconfig
 - Edit internal_users.yml to add user
```