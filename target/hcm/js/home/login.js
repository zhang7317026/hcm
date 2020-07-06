/**
 * 
 */

$(function() {
	
	
});

function login(){
	$("#register_div").hide("normal");
	$("#login_div").show("normal");
}
function register(){
	$("#login_div").hide("normal");
	$("#register_div").show("normal");
}

function loginCommit(){
	var account = $("#account_login").val();
	var password = $("#password_login").val();
	if(isEmpty(account)){
		$("#message_login").text("账号不能为空！");
		return;
	}
	if(isEmpty(password)){
		$("#message_login").text("密码不能为空！");
		return;
	}
	
	$.ajax({
        url:"/hcm/loginCommit",
        type:"POST",
        data:{
        	account : $("#account_login").val(),
        	password : $("#password_login").val()
        },
        success:function(data){
            if(data.flag == "SUCCESS"){
            	window.location.href = "/hcm";
            }else{
            	$("#message_login").text(data.message);
            }
        },
        error:function(){
        	$("#message_login").text("连接失败。。。");
        }
    });
}
function registerCommit(){
	var account = $("#account").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if(isEmpty(account)){
		$("#message_register").text("账号不能为空！");
		return;
	}
	if(isEmpty(name)){
		$("#message_register").text("昵称不能为空！");
		return;
	}
	if(isEmpty(password)){
		$("#message_register").text("密码不能为空！");
		return;
	}
	if(isEmpty(password2)){
		$("#message_register").text("确认密码不能为空！");
		return;
	}
	if(password!=password2){
		$("#message_register").text("两次输入的密码不一致！");
		return;
	}
	
	$.ajax({
        url:"/hcm/registerCommit",
        type:"POST",
        data:{
        	account : $("#account").val(),
        	name : $("#name").val(),
        	password : $("#password").val()
        },
        success:function(data){
            if(data.flag == "SUCCESS"){
            	window.location.href = "/hcm";
            }else{
            	$("#message_register").text(data.message);
            }
        },
        error:function(){
        	$("#message_register").text("连接失败。。。");
        }
    });
}