<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0 
	,target-densitydpi=high-dpi">
<title>一鸣科技小助手</title>
</head>
<body class="back" style="width: 100%; height: 100%;" id="body">
	<%@include file="../home/JS.jsp"%>
	<link rel="stylesheet" type="text/css" href="/hcm/css/home/login.css">
	<script type="text/javascript" src="/hcm/js/home/login.js"></script>

	<div class="col-md-4 col-md-offset-4 text-center">
	
		<div class="" style="margin-top: 100px;">
			<h4><strong>一鸣科技小助手</strong></h4>
			<hr/>
		</div>
		
		<div id="login_div" class="col-md-12">
			<div class="panel-body">
				<div class="form-group input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
					<input type="text" class="form-control" id="account_login"
						placeholder="请输入账号" />
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					<input type="password" class="form-control" id="password_login"
						placeholder="请输入密码" />
				</div>
				<div class="form-group input-group">
					<span id="message_login" style="color:red;font-size: 18px;"></span>
				</div>
				<button class="btn btn-primary" onclick="loginCommit()">登&nbsp;&nbsp;陆</button>
			</div>
		</div>
		
		<div id="register_div" class="col-md-12" style="display: none;">
			<div class="">
				<div class="col-md-12">
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span> 
						<input id="account" class="form-control input-md" type="text" placeholder="请输入账号"> 
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
						<input id="name"    class="form-control input-md" type="text" placeholder="请输入昵称"> 
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
						<input id="password" class="form-control input-md" type="password" placeholder="请输入密码">
					</div>
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
						<input id="password2" class="form-control input-md" type="password"	placeholder="请输入确认密码">
					</div>
					<div class="form-group input-group">
						<span id="message_register" style="color:red;font-size: 18px;"></span>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<button class="btn btn-primary" onclick="registerCommit()">注&nbsp;&nbsp;册</button>
			</div>
		</div>
		
		<div class="col-md-12">
			<hr/>
			没有账号? <a href="javascript:void(0)" onclick="register()">点击我注册</a>,
			我有账号 <a href="javascript:void(0)" onclick="login()">点击我登陆</a>
		</div>
	
	</div>

</body>

</html>