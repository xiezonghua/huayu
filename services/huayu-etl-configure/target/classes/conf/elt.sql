DROP DATABASE IF EXISTS toread_etl;

CREATE DATABASE toread_etl DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE toread_etl;

DROP TABLE IF EXISTS tb_application_info ;
CREATE TABLE tb_application_info(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	company_name VARCHAR(100) NOT NULL COMMENT 'company name' ,
	app_name VARCHAR(100) NOT NULL COMMENT 'application name'  ,
	database_name VARCHAR(100) NOT NULL COMMENT 'database name' ,
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator',
	introduce VARCHAR(500) COMMENT 'introduce for it'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_app_table ;
CREATE TABLE tb_app_table(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	app_id BIGINT NOT NULL COMMENT 'application id' ,
	NAME  VARCHAR(100) NOT NULL COMMENT 'table name' , 
	warehouse_name VARCHAR(100) NOT NULL COMMENT 'warehouse table name' ,
	partition_label TINYINT(1) DEFAULT 0  COMMENT 'partition mark' ,
	extract_type TINYINT COMMENT '1: all , 2: increment  ' ,
	exist_id_field TINYINT(1) COMMENT 'boolean ' ,
	id_column_name VARCHAR(50) COMMENT 'column name for id' ,
	exist_create_date TINYINT(1) COMMENT 'boolean for create date column' ,
	create_date_column_name VARCHAR(50) COMMENT 'column name for create date' , 
	exist_update_date TINYINT(1) COMMENT 'boolean for update date column' ,
	update_date_column_name VARCHAR(50) COMMENT 'column name for update date' ,
	introduce VARCHAR(500) COMMENT 'introduce for it',
			
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator'
)ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS tb_table_colunm ;
CREATE TABLE tb_table_colunm(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	table_id BIGINT NOT NULL COMMENT 'table id' ,
	NAME  VARCHAR(100) NOT NULL COMMENT 'column name' , 
	warehouse_name VARCHAR(100) NOT NULL COMMENT 'warehouse column name' ,
	data_type VARCHAR(20) NOT NULL COMMENT 'data type for column' ,
	warehouse_data_type VARCHAR(20) NOT NULL COMMENT 'warehouse data type for column' , 
	colunm_comment VARCHAR(100) , 
	column_order INT NOT NULL COMMENT 'column order' ,
	nullable_label  TINYINT(1) DEFAULT 0  COMMENT 'nullable mark' ,
	partition_label TINYINT(1) DEFAULT 0  COMMENT 'partition mark' ,

	need_regexp_label TINYINT(1) DEFAULT 0 COMMENT '' ,
	regexp_ids VARCHAR(50)  COMMENT 'regexp str',
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_replacement_regexp_rule;
CREATE TABLE tb_replacement_regexp_rule(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	NAME VARCHAR(50) COMMENT 'regexp rule name' ,
	regexp_replacement VARCHAR(100) NOT NULL ,
	replacement_content VARCHAR(200) DEFAULT "" COMMENT '',
	description VARCHAR(200) COMMENT 'description' ,
	
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_extract_recorder;
CREATE TABLE tb_extract_recorder(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	table_id BIGINT NOT NULL COMMENT 'table id' ,
	export_date VARCHAR(8) NOT NULL COMMENT 'export date',
	current_max_id BIGINT NOT NULL COMMENT 'current max of the extract.' ,
	
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_task;
CREATE TABLE tb_task(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	company_name VARCHAR(100) NOT NULL COMMENT 'company name' ,
	app_name VARCHAR(100) NOT NULL COMMENT 'application name'  ,
	database_name VARCHAR(100) NOT NULL COMMENT 'database name' ,
	extract_date VARCHAR(8) NOT NULL COMMENT 'date for the extractor',
	file_count INT NOT NULL COMMENT 'total of upload files' ,
	extract_type TINYINT COMMENT '1: all , 2: increment  ' ,
	create_user_id BIGINT  COMMENT 'adder user id' , 
	create_date DATETIME COMMENT 'time of add' ,
	update_user_id BIGINT  COMMENT 'updator user id' , 
	update_date DATETIME COMMENT 'time of updator'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_task_log;
CREATE TABLE tb_task_log(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	task_id BIGINT ,
	log_level TINYINT COMMENT '0: info , 1:warning , 3: error',
	msg VARCHAR(1000) ,
	create_date DATETIME COMMENT 'time of add' 
)ENGINE=INNODB DEFAULT CHARSET=utf8;


