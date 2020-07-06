//判断是否为空
function isEmpty (value){
	if(value==""||value==null||value==undefined){
		return true;
	}else{
		return false;
	}
};
//判断是否非空
function isNotEmpty (value){
	if(value==""||value==null||value==undefined){
		return false;
	}else{
		return true;
	}
};

//自定义左滑，右滑
jQuery.fn.slideLeftHide = function( speed, callback ) {  
    this.animate({  
        width : "hide",  
        paddingLeft : "hide",  
        paddingRight : "hide",  
        marginLeft : "hide",  
        marginRight : "hide"  
    }, speed, callback );  
};  
jQuery.fn.slideLeftShow = function( speed, callback ) {  
    this.animate({  
        width : "show",  
        paddingLeft : "show",  
        paddingRight : "show",  
        marginLeft : "show",  
        marginRight : "show"  
    }, speed, callback );  
};

//倒计时
function leftTimer(longTime) {
	var leftTime = longTime*1; //计算剩余的毫秒数 
	var days = parseInt(leftTime / 1000 / 60 / 60 / 24, 10); //计算剩余的天数 
	var hours = parseInt(leftTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时 
	var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
	var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数 
	days = checkTime(days);
	hours = checkTime(hours);
	minutes = checkTime(minutes);
	seconds = checkTime(seconds);
	return days+"天"+hours+"小时"+minutes+"分"+seconds + "秒";
}
//将0-9的数字前面加上0，例1变为01 
function checkTime(i) { 
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}