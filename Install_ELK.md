
Install ELK Stack on CentOS 7


ElasticSearch: Apache OpenSource, Restful API, support distributed search

Logstash: send log files, JSON format file to Elasticsearch

Kibana: visualize the Elasticsearch data

Filebeat: send the changed log file to Logstash


So, Collect log data from Filebeat, Filebeat send it to Logstash, Logstash converts it to JSON format and send to ElasticSearch. Kibana visualize it.


### You have to install Java to use ElasticSearch.


## 1. Install Java

<pre><code>$sudo yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel</code></pre>

## 2. Install ElasticSearch

<pre><code>vi /etc/yum.repos.d/elasticsearch.repo</code></pre>

<pre><code>[elasticsearch-7.x]
name=Elasticsearch repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md</code></pre>


If you want to download ElasticSearch version 6,

just change 7 to 6

<pre><code>sudo yum -y install elasticsearch</code></pre>


## 3.Install Logstash 

<pre><code>vi /etc/yum.repos.d/logstash.repo</code></pre>

<pre><code>[logstash-7.x]
name=Elastic repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md</code></pre>

<pre><code>sudo yum -y install logstash</code></pre>


## 4. Install Kibana

<pre><code>vi /etc/yum.repos.d/kibana.repo</code></pre>

<pre><code>[kibana-7.x]
name=Kibana repository for 7.x packages
baseurl=https://artifacts.elastic.co/packages/7.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md</code></pre>


<pre><code>sudo yum -y install kibana</code></pre>


## 5. Install Filebeat 


<pre><code>vi /etc/yum.repos.d/elastic.repo</code></pre>


<pre><code>[elastic-7.x] 
name=Elastic repository for 7.x packages 
baseurl=https://artifacts.elastic.co/packages/7.x/yum 
gpgcheck=1 
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch 
enabled=1 
autorefresh=1 
type=rpm-md</code></pre>


<pre><code>sudo yum -y install filebeat

Upload filebeat + logstash later....</code></pre>


If you don't have any UI to see the Kibana(like server supports only console), you have to have a Public IP to access Kibana.

Also, you have to change Elasticsearch config file to allow access from other networks.

<pre><code>hostname -i</code></pre>

copy the private IP


<pre><code>sudo vi /etc/elasticsearch/elasticsearch.yml</code></pre>



<pre><code>-------------------network---------------------
network.host: 0.0.0.0

-------------------Discovy----------------------
discovery.seed_hosts: [" paste the private IP "]
cluster.initial_master_nodes: [" paste the private IP "]</code></pre>



<pre><code>sudo systemctl start elasticsearch</code></pre>


If you can get some messages from "curl localhost:9200", Elasticsearch is operating successfully.

Let's change the Kibana config file.


<pre><code>sudo vi /etc/kibana/kibana.yml</code></pre>


<pre><code>server.host: "0.0.0.0"
elasticsearch.url: "http://localhost:9200"</code></pre>


<pre><code>sudo systemctl start kibana</code></pre>

Now, go to your web page and search "Your public IP:5601"

you can face this image.

<img src="https://postfiles.pstatic.net/MjAyMDAyMDhfMTgx/MDAxNTgxMTUxNzYxMTc2.diMLe8A5nLWgXm7sqc2VoSJOAfu7bq_zB-3DxiKtFvUg.e662bNhioSub0j0BxkvtGbaR8B2rrOaT_NajkQhbgeUg.PNG.yhb77888/image.png?type=w773">

