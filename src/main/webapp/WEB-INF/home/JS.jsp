<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
  String pathJS = request.getContextPath();
  String basePathJS = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathJS;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>index</title>

</head>

<body>

<%-- jquery --%>
<script type="text/javascript" src="/hcm/home/js/jquery.1.11.1.js"></script>
<%-- bootstrap --%>
<script type="text/javascript" src="/hcm/home/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="/hcm/home/css/bootstrap.css">
<%-- 百度echarts 
<script type="text/javascript" src="/hcm/home/js/echarts.min.js"></script>--%>
<%-- cookie --%>
<script type="text/javascript" src="/hcm/home/js/jquery.cookie.js"></script>
<%-- jquery.tmpl --%>
<script type="text/javascript" src="/hcm/home/js/jquery.tmpl.js"></script>
<%-- Vue --%>
<script type="text/javascript" src="/hcm/home/js/vue.min.js"></script>
<%-- bootstrap-switch.min --%>
<script type="text/javascript" src="/hcm/home/js/bootstrap-switch.min.js"></script>
<link rel="stylesheet" type="text/css"	href="/hcm/home/css/bootstrap-switch.min.css">



<!-- 个人共用JS -->
<script type="text/javascript" src="<%=basePathJS%>/home/js/private_commonJS.js?randomId=<%=Math.random()%>"></script>
<!-- 个人共用css -->
<link rel="stylesheet" type="text/css"	href="/hcm/home/css/private-commonCSS.css?randomId=<%=Math.random()%>">

</body>
</html>