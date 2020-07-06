var width = document.documentElement.clientWidth;
var height = document.documentElement.clientHeight;

var timeInterval;
$(function() {
	
	//初始化开关按钮
	$("#isEnabled").bootstrapSwitch({
		onText:"启动",
		offText:"停止",
		onColor:"success",
		offColor:"info",
		size:"large",
		onSwitchChange:function(event,state){
			var url;
			if(state==true){
				url = "/hcm/begin";
			}else{
				url = "/hcm/end";
			}
			$.ajax({
				url : url,
				type : "POST",
				data : {},
				success : function(data){
					if(data=="SUCCESS"){
						//刷新列表
						refreshList();
					}else{
						alert("开关启动失败！请联系管理员");
					}
				},
				error : function(){
					alert("开关启动失败(error)！请联系管理员");
				}
			});
		}
	});
	
	//初始化positionId
	$.ajax({
		url : "/hcm/getPositionList",
		type : "POST",
		async : false,
		data : {},
		success : function(list){
			for(var i=0;i<list.length;i++){
				$("#positionId").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
			}
		}
	});
	
	//初始化用户信息
	$.ajax({
		url : "/hcm/user/getUserInfo",
		type : "POST",
		async : false,
		data : {},
		success : function(rs){
			var data = rs.userInfo;
			//初始化按钮状态
			if(data.isEnabled=="1"){
				$("#isEnabled").bootstrapSwitch('state',true);
			}else{
				$("#isEnabled").bootstrapSwitch('state',false);
			}
			//当前token
			$("#currentToken").text(data.token);
			
			//初始化位置选择
			$("#positionId").val(data.positionId);
			
			if(data.status=="9"){
				$("#time").text("token已经过期，请及时更新！");
			}else{
				//没token更新时间
				if(isEmpty(data.tokenUpdateTime)){
					$("#time").text("token为空，请及时更新！");
				}else{
					//开启定时器
					timeInterval = setInterval(function(){ 
						//token倒计时
						var timestamp = data.tokenUpdateTime;
						var longTime = timestamp*1000
							+ 6*24*60*60*1000
							-new Date().getTime();
						if(longTime>0){	
							var timestr = leftTimer(longTime);
							$("#time").text("token还有 "+timestr+" 过期");
						}else{
							$("#time").text("token已过期，请及时更新！");
						}
						 
						//获取日
						var timestr = leftTimer(new Date().getTime() - new Date(data.createTime).getTime());
						$("#usedTime").text(timestr);
						$("#successTimes").text(data.successTimes);
					},1000);
				 }
			}
			
		},
		error : function(){
			alert("开关启动失败(error)！请联系管理员");
		}
	});
	
	//刷新列表	
	refreshList();
});

//刷新列表
function refreshList(){
	//获取记录列表
	$.ajax({
		url : "/hcm/getSendDetailList",
		type : "POST",
		data : {},
		success : function(map){
			$("#panelAllId").text("");
			//1-循环map得list
			for (var key in map) {  
				//2-循环list
				var panel = '<div class="panel panel-info">';
				panel += '<div class="panel-heading">'+key+'</div>';
				panel += '<div class="panel-body">';
				panel += '<div class="table-responsive">'
					+ '<table class="table table-striped table-bordered table-hover">'
					+ '<thead><tr>'
                    + '<th class="text-center">#</th>'
                    + '<th class="text-center">更新时间</th>'
                    + '<th class="text-center">状态</th>'
                    + '</tr></thead>'
                    + '<tbody>';
				
				var data = map[key];
				for(var i=0;i<data.length;i++){
					var status;
					if(data[i].status=="true"){
						status = "成功";
					}else if(data[i].status=="false"){
						status = "失败";
					}else{
						status = "数据异常";
					}
					var tr = '<tr>'
						+'<td>'+(i+1)+'</td>'
						+'<td>'+data[i].sendTime+'</td>'
						+'<td>'+status+'</td>'
						+'</tr>';
					
					panel += tr;
				}
				
				panel += '</tbody></table></div>';
				panel += '</div>';
				panel += '<div class="panel-footer">共计&nbsp;<strong>'+data.length+'</strong>&nbsp;次</div>';
				panel += '</div>';
				$("#panelAllId").append(panel);
			}
		},
		error : function(){
			alert("获取列表失败(error)！请联系管理员");
		}
	});
}

// 更新token
function updateToken() {
	var tokenContent = $("#tokenContent").val();
	if(isEmpty(tokenContent)){
		alert("token值不能为空！");
		return;
	}
	
	$.ajax({
		url : "/hcm/updateToken",
		type : "POST",
		data : {
			tokenContent : tokenContent
		},
		success : function(data) {
			if(data == "SUCCESS") {
				//刷新界面
				location.reload();
			}else if(data == "token_invalid"){
				alert("token无效！请查看token值是否填写正确！");
			}else{
				alert("更新token失败！请联系管理员");
			}
		},
		error : function() {
			alert("更新token失败(error)！请联系管理员");
		}
	});
}

//立即打卡
function now() {
	$.ajax({
		url : "/hcm/now",
		type : "POST",
		data : {
		},
		success : function(data) {
			if (data == "SUCCESS") {
				alert("立即打卡成功！");
				//刷新界面
				location.reload();
			} else {
				alert("立即打卡失败！请联系管理员");
			}
		},
		error : function() {
			alert("立即打卡失败(error)！请联系管理员");
		}
	});
}

//改变位置
function changePosition() {
	$.ajax({
		url : "/hcm/changePosition",
		type : "POST",
		data : {
			positionId : $("#positionId").val()
		},
		success : function(data) {
			if (data == "ERROR") {
				alert("改变位置失败！");
			}
		},
		error : function() {
			alert("改变位置失败！");
		}
	});
}


