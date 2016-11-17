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
<title>头部页面</title>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>

<div class="header">
	<div class="logo">继续教育学院成绩管理系统</div>
	<div class="right_nav">
		<div class="text_right">
            <ul class="nav_return">
                <li> [<a href="${pageContext.request.contextPath}/ExpertServlet?method=exit" target="_top">退出</a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
		</div>
	</div>
<div class="under_header"> 
<span>${sessionScope.session_expert.name}</span>,您好！职工号：<span>${sessonScope.session_expert.expacount}</span>&nbsp;&nbsp;学院：<span>医药信息工程学院</span>&nbsp;&nbsp;职称：<span>讲师</span> </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-parent.js"></script><!-- 本地的bootstrap资源链接 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>