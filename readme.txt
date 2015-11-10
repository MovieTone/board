1. �������� Tomcat - http://apache.ip-connect.vn.ua/tomcat/tomcat-8/v8.0.28/bin/apache-tomcat-8.0.28.zip

2. ��������� ���� BoardNew-1.0.war � ������� apache-tomcat-8.0.28\webapps

3. ��������� ������ Tomcat - apache-tomcat-8.0.28\bin\startup.bat

4. � �������� apache-tomcat-8.0.28\webapps ������������� ��������� ������� board.

5. ���������� ���������������� ����� Ads.hbm.xml, Users.hbm.xml � hibernate.cfg.xml � apache-tomcat-8.0.28\webapps\BoardNew-1.0\WEB-INF\classes

6. �������� MySQL � ��������� ������ mysqld.exe. �������� ������� cmd, ��� ��������� mysql � ������� ���������� ��������� 4 �������:
1) create database bulletin_board;
2) use bulletin_board;
3) create table users
(
user varchar(20) not null primary key,
password varchar(40) not null
) engine=InnoDB;
4) create table ads
(
id int not null primary key auto_increment,
adName varchar(40) not null,
headline varchar(30) not null,
rubric varchar(10) not null,
adText varchar(400) not null,
adTime bigint not null
) engine=InnoDB;

7. � ���������������� ����� apache-tomcat-8.0.28\webapps\BoardNew-1.0\WEB-INF\classes\hibernate.cfg.xml ������� ��������� ������:
<property name = "connection.url">jdbc:mysql://127.0.0.1:3306/bulletin_board?autoReconnect=true&amp;useUnicode=true&amp;createDatabaseIfNotExist=true&amp;characterEncoding=utf-8</property>
<property name = "connection.user">admin</property>
<property name = "connection.password">password</property>
�������� ���� 3306 �� ���� ������ MySQL �������, admin (����� ������) �� ���� ��� ������������ 
� password (����� ������) �� ���� ������

8. �������� � �������� ������� ���� http://localhost:8080/BoardNew-1.0/