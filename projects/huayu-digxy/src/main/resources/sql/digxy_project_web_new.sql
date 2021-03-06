DROP DATABASE IF EXISTS digxy_new; 

CREATE DATABASE digxy_new DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE digxy_new;

/**
* Project information
*/
Drop table if exists tb_project;
create table tb_project(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'key id',
	name varchar(50) not null comment 'project name' , 
	flag_image varchar(100) comment 'flag image for project' ,
	description varchar(500) comment 'description' ,
	home_page varchar(5000) comment 'home page info' , 
	creater bigint comment 'who create the project' ,
	creater_date datetime comment 'creater time' ,
	checker bigint comment 'who check the resource' ,
	check_date datetime comment 'check date' ,
	status tinyint comment 'passed , waiting , failure',
	check_msg varchar(500) comment 'check message' ,
	begin_datetime datetime comment 'what time the project begin in plan' ,
	end_datetime datetime comment 'what time the project end in plan',
	attender_limit int comment 'attender limit' ,
	plan_doc varchar(300) comment 'plan document' ,
	foster_doc varchar(300) comment 'foster plan document' , 
	talents_doc varchar(300) comment 'talents document',
	process_status tinyint comment 'project handle status', 
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8; 


Drop table if exists tb_sub_project;
Create table tb_sub_project(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'key id',
	name VARCHAR(50) not null COMMENT 'sub project name' ,
	flag_image varchar(100) comment '' ,  
	description varchar(500)  comment 'description' ,
	goal varchar(1000) comment 'we need to achieve' , 
	project_id BIGINT NOT NULL COMMENT 'parent project_id' , 
	creator_id BIGINT not null Comment 'who create it' , 
	create_time datetime comment 'when creating' ,
	modifier_id BIGINT COMMENT 'who update it' ,
	modify_time datetime comment 'when modifying' ,
	PRIMARY KEY(id)
)Engine=InnoDB DEFAULT CHARSET=utf8;


drop table if exists tb_project_resource;
create table tb_project_resource(
	id bigint not null auto_increment comment 'key id',
	name varchar(50) not null comment 'resource name' , 
	description varchar(500) comment 'resource description' , 
	doc_name varchar(200) not null comment 'file name , including the doc , swf ,image' ,
	doc_floder varchar(200) comment 'where the file store .',
	star tinyint default 0 comment 'level',
	upload_date datetime comment 'upload time' ,
	uploader bigint comment 'who upload' ,
	project_id bigint comment 'who belong to ',
	sub_project_id bigint comment 'who belong to sub_project',
	label varchar(50)  comment 'some tag  ' , 
	click_times int comment 'click it times' ,
	download_times int comment 'download times' ,
	status tinyint default 0 comment 'maybe need to audit'	,
	creator_id BIGINT not null Comment 'who create it' , 
	create_time datetime comment 'when creating' ,
	modifier_id BIGINT COMMENT 'who update it' ,
	modify_time datetime comment 'when modifying' ,	
	primary key(id)
)engine=innodb default charset=utf8; 


DROP TABLE IF EXISTS tb_project_member;
CREATE TABLE tb_project_member(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'KEY ID',
	project_id BIGINT NULL COMMENT 'prorject id',
	sub_project_id BIGINT COMMENT 'sub project id' ,
	user_id BIGINT NULL COMMENT 'user id',
	name varchar(20) comment 'member name' ,
	img_src varchar(50) comment 'member image' ,
	education varchar(10) comment 'education' , 
	research_fields VARCHAR(200) COMMENT 'what your working into' ,
	responsibility varchar(200) COMMENT 'project responsibility' ,
	project_experience varchar(500) comment 'project experience' ,
	job VARCHAR(40) COMMENT 'company or shool',
	job_title VARCHAR(20) COMMENT 'title for your work' , 	
	description VARCHAR(500) COMMENT 'plan or other' ,
	apply_date DATETIME NULL COMMENT 'create date time',
	role TINYINT NULL COMMENT '0 : creator , 1: bone member , 2: attender , attender role and bone member' ,  
	state TINYINT NULL COMMENT 'state , 1 apply , 2 : working 3:completed ',
	msg VARCHAR(500) NULL COMMENT 'failure inforamtion',
	PRIMARY KEY(id) 
)ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS tb_system_dictionary;
CREATE TABLE tb_system_dictionary(
	id INTEGER NOT NULL AUTO_INCREMENT COMMENT 'KEY ID', 
	type_id TINYINT NOT NULL COMMENT 'dict type id ',
	type_name VARCHAR(50) NOT NULL COMMENT 'dict type name' ,
	type_desc VARCHAR(500) COMMENT 'business description' , 
	superior_id TINYINT COMMENT 'some have parent ' , 
	type_code TINYINT NOT NULL COMMENT 'what type in dictionary' ,
	PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

/* resource type */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '学习' , '资源类型' , 1);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '研究' , '资源类型' , 1);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 3 , '成才' , '资源类型' , 1);

/* resource audit status */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '待审核' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '审核未通过' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 3 , '审核通过' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 4 , '正在处理' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 5 , '标题或分类不准确' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 6 , '信息不完整' , '资源处理' , 2);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 7 , '系统处理' , '资源处理' , 2);

/* user type for manager */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '用户' , '用户类型' , 3);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 100 , '管理员' , '用户类型' , 3);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 101 , '超级用户' , '用户类型' , 3);
											 
/* project status */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '待审核' , '项目审核状态' , 4);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '审核通过' , '项目审核状态' , 4);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 3 , '审核失败' , '项目审核状态' , 4);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 4 , '启动中' , '项目执行状态' , 4);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 5 , '进行中' , '项目执行状态' , 4);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 6 , '完成' , '项目执行状态' , 4);

/* project audit status
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '待审核' , '项目审核状态' , 5);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '审核通过' , '项目审核状态' , 5);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 3 , '审核失败' , '项目审核状态' , 5);
 */

/* project applyer status */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '申请中' , '项目申请人状态' , 6);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '审核通过，参与中' , '项目申请人状态' , 6);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 3 , '完成' , '项目申请人状态' , 6);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 4 , '拒绝' , '项目申请人状态' , 6);

/* project attender role */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '发起人' , '项目参与者角色类型', 7);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 2 , '参与人' , '项目参与者角色类型' , 7);

/* user sex */
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 1 , '男' , '性别', 8);
INSERT INTO tb_system_dictionary(type_id , type_name , type_desc , type_code)  VALUES( 0 , '女' , '性别' , 8);

DROP TABLE if exists tb_system_user;
CREATE TABLE tb_system_user(
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'KEY ID',
	name VARCHAR(50) NOT NULL COMMENT 'user Name',
	pet_name VARCHAR(50) NULL COMMENT '',
	password VARCHAR(50) NOT NULL COMMENT 'password',
	sex tinyint NULL COMMENT 'boy or girl',
	signature VARCHAR(100) NULL default '' COMMENT 'signature for yourself',
	email VARCHAR(50) NULL default '' COMMENT 'email address',
	img_src VARCHAR(50) NULL COMMENT '' ,
	card_id VARCHAR(50) NULL COMMENT '',
	imessager VARCHAR(50) NULL ,
	phone VARCHAR(20) NULL,
	regtime DATETIME NULL COMMENT 'sign in time',
	profile VARCHAR(200) NULL COMMENT 'resume',
	last_sign_in DATETIME NULL COMMENT 'sign off time',
	prestige VARCHAR(50) NULL,
	level TINYINT NULL default 1 ,
	level_name VARCHAR(20) NULL,
	role TINYINT NULL DEFAULT 1 COMMENT '1: common ; 100: manager ; 101 : super',
	
	birthplace VARCHAR(20) NULL COMMENT '' ,
	birthdate Date NULL COMMENT '' ,
	school VARCHAR(20) NULL COMMENT 'current study ',
	major VARCHAR(20) NULL ,
	health VARCHAR(50) NULL , 
	english_level VARCHAR(20) NULL ,
	edu VARCHAR(200) NULL , 
	skill_career VARCHAR(200) NULL ,  
	PRIMARY KEY(id) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists tb_notification;
create table tb_notification(
	id bigint not null AUTO_INCREMENT comment 'key id',
	bus_id bigint comment 'business id' ,
	content varchar(500) comment 'detail',
	add_date datetime comment 'add date',
	add_user_id bigint comment 'add user id' ,
	`type` tinyint default 0 comment ' 0 : information , 1: notify all',
	`status` tinyint default 1 comment '0 : unavailable , 1 : available',
	is_main tinyint default 0  comment '1 : main  ' , 
	primary key(id)
)engine=innodb default charset=utf8;

