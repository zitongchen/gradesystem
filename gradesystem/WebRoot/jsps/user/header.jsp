<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/studentCSS.css" rel="stylesheet" style="text/css">
</head>
<body>
<!-- 以下是头部header -->
<div class="header">
	<div class="logo">继续教育学院成绩管理系统</div>
	<div class="right_nav">
		<div class="text_right">
            <ul class="nav_return">
                <li> [<a href="${pageContext.request.contextPath}/UserServlet?method=exit" target="_top">退出</a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
		</div>
	</div>
<div class="under_header"> 
<span>${sessionScope.session_user.nickname}</span>,你好！专业：<span>${sessionScope.session_user.zymc}</span>|</span></span>班级：<span>${sessionScope.session_user.bh }</span><span>|</span>学号：<span>${sessionScope.session_user.user_acount}</span> </div>
</div>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>