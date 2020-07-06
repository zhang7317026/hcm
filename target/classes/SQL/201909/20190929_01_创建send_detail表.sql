/*创建send_detail表*/
create table send_detail(
	id varchar(200) primary key comment '表ID',
	user_id varchar(200) not null comment '用户ID',
	send_time varchar(200) not null comment '发送时间',
	status varchar(200) not null comment '状态',
	position_x double comment '横坐标',
	position_y double comment '纵坐标',
	position_type varchar(200) comment '位置id'
) comment='发送详情表' default charset=utf8;

ALTER TABLE send_detail ADD INDEX (user_id);
ALTER TABLE send_detail ADD INDEX (user_id, status);
ALTER TABLE send_detail ADD INDEX (user_id, send_time);
ALTER TABLE send_detail ADD INDEX (send_time);
ALTER TABLE send_detail ADD INDEX (send_time, status);
ALTER TABLE send_detail ADD INDEX (status);