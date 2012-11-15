alter table CATEGORY_QUESTION drop foreign key FKD2C46E0788E429AD;
alter table CATEGORY_QUESTION drop foreign key FKD2C46E071E34E0AD;
alter table PARENT_CATEGORY drop foreign key FKA39080F388E429AD;
alter table PARENT_CATEGORY drop foreign key FKA39080F3FB31E198;
drop table if exists CATEGORY_QUESTION;
drop table if exists PARENT_CATEGORY;
drop table if exists category;
drop table if exists question;
drop table if exists user;
