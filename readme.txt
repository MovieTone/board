1. Скачайте Tomcat - http://apache.ip-connect.vn.ua/tomcat/tomcat-8/v8.0.28/bin/apache-tomcat-8.0.28.zip

2. Поместите файл BoardNew-1.0.war в каталог apache-tomcat-8.0.28\webapps

3. Запустите сервер Tomcat - apache-tomcat-8.0.28\bin\startup.bat

4. В каталоге apache-tomcat-8.0.28\webapps автоматически создастся каталог board.

5. Скопируйте конфигурационные файлы Ads.hbm.xml, Users.hbm.xml и hibernate.cfg.xml в apache-tomcat-8.0.28\webapps\BoardNew-1.0\WEB-INF\classes

6. Откройте MySQL и запустите сервер mysqld.exe. Откройте консоль cmd, где запустите mysql и введите поочередно следующие 4 команды:
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

7. В конфигурационном файле apache-tomcat-8.0.28\webapps\BoardNew-1.0\WEB-INF\classes\hibernate.cfg.xml найдите следующие строки:
<property name = "connection.url">jdbc:mysql://127.0.0.1:3306/bulletin_board?autoReconnect=true&amp;useUnicode=true&amp;createDatabaseIfNotExist=true&amp;characterEncoding=utf-8</property>
<property name = "connection.user">admin</property>
<property name = "connection.password">password</property>
Измените порт 3306 на порт своего MySQL сервера, admin (между тегами) на свое имя пользователя 
и password (между тегами) на свой пароль

8. Откройте в браузере готовый сайт http://localhost:8080/BoardNew-1.0/