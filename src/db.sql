drop database if exists education;

create database education default character set utf8;
use education;
drop table if exists `euser`;
create table euser(
	uid integer auto_increment primary key,
	udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	name varchar(50),
	password varchar(20),
	email varchar(50),
	utype integer
)engine = myisam  default charset = utf8;


drop table if exists `lesson`;
create table lesson(
	lid integer auto_increment primary key,
	name varchar(50),
	ldesc varchar(200),
	udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	uid integer,
	ltype integer
)engine = myisam  default charset = utf8;

drop table if exists `lesson_student`;
create table lesson_student(
	id integer auto_increment primary key,
	lid integer,
	sid integer,
	stype integer
)engine = myisam  default charset = utf8;

drop table if exists `lesson_file`;
create table lesson_file(
	fid integer auto_increment primary key,
	name varchar(100),
	path varchar(1000),
	ftype varchar(10),
	fdesc varchar(2000),
	lid integer
)engine = myisam  default charset = utf8;

drop table if exists `exam`;
create table exam(
	eid integer auto_increment primary key,
	name varchar(50),
	edesc varchar(200),
	edue datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	etype integer,
	lid integer,
	uid integer
)engine = myisam  default charset = utf8;

drop table if exists `exam_video`;
create table exam_video(
	fid integer auto_increment primary key,
	name varchar(100),
	path varchar(1000),
	ftype varchar(10),
	fdesc varchar(2000),
	eid integer
)engine = myisam  default charset = utf8;


drop table if exists `exam_word`;
create table exam_word(
	fid integer auto_increment primary key,
	name varchar(100),
	pronunciation varchar(100),
	path varchar(1000),
	ftype varchar(10),
	fdesc varchar(2000),
	eid integer
)engine = myisam  default charset = utf8;

drop table if exists `exam_result`;
create table exam_result(
	rid integer auto_increment primary key,
	uid integer,
	eid integer,
	answer varchar(2000)
)engine = myisam  default charset = utf8;






