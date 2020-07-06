/*创建sys_user_info表*/
create table sys_user_info
(
	id varchar(200) not null primary key comment '用户ID',
	name varchar(200) not null comment '用户名',
	account varchar(200) not null comment '账号',
	password varchar(200) not null comment '密码',
	create_time varchar(200) not null comment '创建时间',
	last_login varchar(200) comment '最后登录时间',
	token varchar(2000) comment 'token值',
	token_update_time int comment 'token更新时间',
	success_times int comment '成功次数',
	ex_am_hour int comment '早上执行时间(时)',
	ex_am_minute int comment '早上执行时间(分)',
	ex_am_second int comment '早上执行时间(秒)',
	ex_pm_hour int comment '晚上执行时间(时)',
	ex_pm_minute int comment '晚上执行时间(分)',
	ex_pm_second int comment '晚上执行时间(秒)',
	position_x double comment '横坐标',
	position_y double comment '纵坐标',
	position_id varchar(200) comment '位置id',
	is_enabled varchar(200) comment '是否可用',
	status varchar(200) comment '状态'
) comment='系统用户信息表' default charset=utf8;

ALTER TABLE sys_user_info ADD INDEX (name);
ALTER TABLE sys_user_info ADD INDEX (account);
ALTER TABLE sys_user_info ADD INDEX (account,password);
ALTER TABLE sys_user_info ADD INDEX (status);
