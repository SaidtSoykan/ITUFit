create table admin (facility_id integer not null, id bigint not null, primary key (id));
create table admin_key (id bigint not null, key_hash bytea, key_salt bytea, primary key (id));
create table comment (rating integer, date timestamp(6), facility_id bigint, id bigserial not null, student_id bigint, comment varchar(255), primary key (id));
create table facility (capacity integer, facility_type smallint check (facility_type between 0 and 6), id bigserial not null, description varchar(255), location varchar(255), primary key (id));
create table meal (calorie float(53) not null, id bigserial not null, meal_date timestamp(6), primary key (id));
create table reservation (end_time timestamp(6), facility_id bigint, id bigserial not null, start_time timestamp(6), student_id bigint, primary key (id));
create table student (basal_metabolism float(53) not null, exercise_score integer, goal_weight float(53) not null, height float(53) not null, is_restricted boolean not null, weight float(53) not null, id bigint not null, primary key (id));
create table userr (id bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), user_name varchar(255), password_hash bytea, password_salt bytea, primary key (id));
alter table if exists admin add constraint FKiadwjjlofshsunhct0spffrab foreign key (id) references userr;
alter table if exists comment add constraint FK823mmrxws72i4hs98yfyvlclj foreign key (facility_id) references facility;
alter table if exists comment add constraint FKamc40esh2mg78arkc68uflpjs foreign key (student_id) references student;
alter table if exists reservation add constraint FKk2ho7pcs5a3m9phlq0gjeqx12 foreign key (facility_id) references facility;
alter table if exists reservation add constraint FKiuft3416ayrn5538t6bd108fw foreign key (student_id) references student;
alter table if exists student add constraint FK1qh3y0kgt3fgsieonrxmjnis6 foreign key (id) references userr;
