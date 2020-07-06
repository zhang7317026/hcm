/*创建position_list表*/
create table position_list
(
	id varchar(200) not null primary key comment '位置id',
	name varchar(200) not null comment '位置名称',
	location_id int comment 'location_id',
	position_x double comment '横坐标前缀值',
	position_y double comment '纵坐标前缀值',
	position_x_min int comment '横坐标尾数最小值',
	position_x_max int comment '横坐标尾数最大值',
	position_y_min int comment '纵坐标尾数最小值',
	position_y_max int comment '纵坐标尾数最大值',
	is_enabled varchar(200) comment '是否可用',
	status varchar(200) comment '状态'
) comment='系统用户信息表' default charset=utf8;

ALTER TABLE position_list ADD INDEX (name);
ALTER TABLE position_list ADD INDEX (is_enabled);
ALTER TABLE position_list ADD INDEX (status);