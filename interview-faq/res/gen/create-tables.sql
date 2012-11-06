create table CATEGORY_QUESTION (question_id bigint not null, category_id bigint not null) ENGINE=InnoDB;
create table category (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, categoryDescription varchar(150), name varchar(80), primary key (id)) ENGINE=InnoDB;
create table question (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, question varchar(200), primary key (id)) ENGINE=InnoDB;
create table user (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, email varchar(255), name varchar(80), password varchar(80), role varchar(30), primary key (id)) ENGINE=InnoDB;
alter table CATEGORY_QUESTION add index FKD2C46E0788E429AD (category_id), add constraint FKD2C46E0788E429AD foreign key (category_id) references category (id);
alter table CATEGORY_QUESTION add index FKD2C46E071E34E0AD (question_id), add constraint FKD2C46E071E34E0AD foreign key (question_id) references question (id);
