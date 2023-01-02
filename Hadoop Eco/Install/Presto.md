## Presto

 - https://prestodb.io/docs/current/installation/deployment.html

```
wget https://repo1.maven.org/maven2/com/facebook/presto/presto-server/0.278.1/presto-server-0.278.1.tar.gz
tar -zxvf presto-server-0.278.1.tar.gz
cd presto-server-0.278.1/

mkdir etc
touch etc/config.properties
touch etc/log.properties
touch etc/node.properties

mkdir etc/catalog
touch etc/catalog/hive.properties
```

```config.properties
coordinator=true
node-scheduler.include-coordinator=true
http-server.http.port=8282
query.max-memory=5GB
query.max-memory-per-node=1GB
discovery-server.enabled=true
discovery.uri=http://localhost:8282
```

```log.properties
com.facebook.presto=INFO
```

```node.properties
node.environment=production
node.id=ffffffff-ffff-ffff-ffff-ffffffffffff
node.data-dir=/var/presto/data
```

```etc/catalog/hive.properties
connector.name=hive-hadoop2
hive.non-managed-table-writes-enabled=true
hive.non-managed-table-creates-enabled=true
hive.metastore.uri=thrift://localhost:9083
hive.config.resources=/etc/hadoop/conf/core-site.xml,/etc/hadoop/conf/hdfs-site.xml
hive.s3.use-instance-credentials=false
hive.s3.aws-access-key=
hive.s3.aws-secret-key=
hive.s3.endpoint=
```

## Presto Cli

 - https://prestodb.io/docs/current/installation/cli.html
```
wget https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.278.1/presto-cli-0.278.1-executable.jar
chmod +x presto-cli-0.278.1-executable.jar
mv presto-cli-0.278.1-executable.jar presto
./presto --server localhost:8282 --catalog hive --schema default
```