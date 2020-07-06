<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0 
	,target-densitydpi=high-dpi">
<title>一鸣科技</title>
</head>
<body>
<%@include file="../home/JS.jsp"%>
<link rel="stylesheet" type="text/css" href="/hcm/css/main/main.css?randomId=<%=Math.random()%>">

<div class="col-md-12" style="margin-top: 10px;">
	<div class="col-md-10 text-left" style="font-size: larger;font-weight: bolder;">
		<span style="color:">一鸣科技已为您服务</span><span id="usedTime"></span>,
		成功打卡<span id="successTimes"></span>次
	</div>
	<div class="col-md-2 text-right" style="font-size: larger;font-weight: bolder;">版本：V2.9</div>
</div>
<div class="col-md-12" style="margin-top: 10px;color: red;">
	通知：新版已升级，可以自由选择位置
</div>
<div class="col-md-12" style="margin-top: 10px;color: red;">
	<a href="https://inspur.hcmcloud.cn/#/index" target="_blank">HCM快速入口</a>
</div>
<div class="col-md-12" style="margin-top: 30px;">
	<div class="col-md-3" style="background-color: #FFF;">
		<div class="col-md-12" style="margin-top: 30px;">
			<button class="btn btn-primary btn-lg" type="button" onclick="now()">立即签到</button>
		</div>
		<div class="col-md-12" style="margin-top: 15px;margin-bottom: 50px;">
			<div class="switch" data-on="success" data-off="warning">
				<span style="color: #ff9900;font-size: large;font-weight: bolder;">自动打卡:</span>
			    <input type="checkbox" checked="" id="isEnabled" />
			</div>
		</div>
		<div class="col-md-12" style="margin-top: 50px;margin-bottom: 50px;">
			<strong style="font-size: large;">安卓APP下载：</strong>
			<br/>
			<br/>
			<img alt="" src="/hcm/img/QRCode.png" width="150" height="150">
		</div>
	</div>
	<div class="col-md-1" style="width:2%;">
	</div>
	<div class="col-md-8" style="background-color: #FFF;">
		<div class="col-md-12" style="font-size: larger;">
			<span id="time" style="color: red;">token还有过期</span>
		</div>
		<div class="col-md-12" style="margin-top: 20px;">
			当前token值：<span id="currentToken"></span>
		</div>
		<div class="col-md-12" style="margin-top: 30px;">
			<input type="text" class="form-control input-md" id="tokenContent" 
				placeholder="在此输入token值后点击更新即可" style="float: left;width: 85%;" />
			<button class="btn btn-primary btn-md" style="float: left;width: 15%;"
				type="button" onclick="updateToken()">更新</button>
			<br/>
		</div>
		<div class="col-md-12" style="margin-top: 30px;">
			位置：<select id="positionId" onchange="changePosition()"></select>
		</div>
		<div class="col-md-12" style="margin-top: 30px;">
            <div class="panel panel-default">
                <div class="panel-heading">
                    	打卡记录
                </div>
                <div class="panel-body" id="panelAllId">
                    
                </div>
            </div>
		</div>
	</div>
</div>



<script type="text/javascript" src="/hcm/js/main/main.js?randomId=<%=Math.random()%>"></script>
</body>

</html>