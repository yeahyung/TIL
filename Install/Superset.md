# Apache Superset

Apache Superset이란?

AirBnB에서 오픈소스로 공개, 데이터 탐색 및 시각화를 위한 웹 기반의 데이터 시각화 툴, 대시보드 생성 및 공유 가능

csv 파일 upload가능(upload시 DB에 update), 거의 모든 종류의 DB지원(MongoDB 제외)

## Apache Superset 설치 방법(CentOS 7)

python3 설치 및 upgrade
<pre><code>sudo yum install -y https://centos7.iuscommunity.org/ius-release.rpm 
sudo yum install -y python36u python36u-libs python36u-devel python36u-pip
sudo yum upgrade python-setuptools
sudo yum install gcc gcc-c++ libffi-devel python-devel python-pip python-wheel openssl-devel cyrus-sasl-devel openldap-devel
</code></pre>

<pre><code># 가상환경 사용할 수 있는 virtualenv 설치
pip3 install virtualenv
# venv 가상환경 생성
python3 -m venv venv
# venv 가상환경 실행
. venv/bin/activate
# pip tool upgrade
pip install --upgrade setuptools pip
# Install superset
pip install apache-superset
# Initialize the database
superset db upgrade
# Create an admin user (you will be prompted to set a username, first and last name before setting a password)
export FLASK_APP=superset
flask fab create-admin

ex)
Username [admin]: root
User first name [admin]: name
User last name [user]: name
Email [admin@fab.org]: email
Password:12345

# Load some data to play with
superset load_examples
# Create default roles and permissions
superset init
# To start a development web server on port 8088, use -p to bind to another port
superset run -p 8088 --with-threads --reload --debugger
superset run -p 8080 -h 0.0.0.0 --with-threads --reload --debugger
</code></pre>

## 외부에서 Superset 접근(nginx 활용)
<pre><code>sudo vi /etc/yum.repos.d/nginx.repo
[nginx]
name=nginx repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=0
enabled=1
	
sudo yum install -y nginx
systemctl start nginx
systemctl enable nginx

sudo vi /etc/nginx/conf.d/default.conf
proxy_pass http://127.0.0.1:5000;
</code></pre>

## MySQL과 연동

<pre><code>sudo yum install python-devel mysql-devel
# 만약 위의 명령어를 실행하였을 때 mariadb관련 conflict가 발생한다면
yum remove mariadb-libs - y
sudo yum install python-devel mysql-devel
pip install mysqlclient
</code></pre>
