net start mysql
mysql -u myroot -p
yangtao1992
create database tonguetwister;
show databases;
use userdata;
create table tb_ip(
userIp varchar(20),
time bigint(20)
);
desc user;
select*from tb_ip;
insert into tb_ip (userIp) value ('182.61.2.1');
select*from tb_ip;
select*from tb_ip where '182.61.2.1' in (userIp);
update tb_ip set time=null where userIp='192.168.1.5';
select*from tb_ip;