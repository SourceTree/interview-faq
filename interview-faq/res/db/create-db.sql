drop database if exists faqmasters;
create database if not exists faqmasters character set utf8 collate utf8_bin;
use faqmasters;
grant all privileges on faqmasters.* to 'faqmasters'@'%' identified by 'FAQMastersP@ssw0rd' with grant option;
flush privileges;
