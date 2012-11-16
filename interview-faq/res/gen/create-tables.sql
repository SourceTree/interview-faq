create table CATEGORY_QUESTION (question_id bigint not null, category_id bigint not null) ENGINE=InnoDB;
create table PARENT_CATEGORY (parent_category_id bigint, category_id bigint not null, primary key (category_id)) ENGINE=InnoDB;
create table category (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, categoryDescription varchar(500) not null, name varchar(80) not null, primary key (id), unique (name)) ENGINE=InnoDB;
create table question (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, answer varchar(5000) not null, question varchar(200) not null, primary key (id)) ENGINE=InnoDB;
create table user (id bigint not null auto_increment, created_date datetime, deleted bit, modified_date datetime, version integer, email varchar(255), name varchar(80), password varchar(80), role varchar(30), primary key (id)) ENGINE=InnoDB;
alter table CATEGORY_QUESTION add index FKD2C46E0788E429AD (category_id), add constraint FKD2C46E0788E429AD foreign key (category_id) references category (id);
alter table CATEGORY_QUESTION add index FKD2C46E071E34E0AD (question_id), add constraint FKD2C46E071E34E0AD foreign key (question_id) references question (id);
alter table PARENT_CATEGORY add index FKA39080F388E429AD (category_id), add constraint FKA39080F388E429AD foreign key (category_id) references category (id);
alter table PARENT_CATEGORY add index FKA39080F3FB31E198 (parent_category_id), add constraint FKA39080F3FB31E198 foreign key (parent_category_id) references category (id);
