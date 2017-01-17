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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css">
</head>
<body>
<!-- 以下是头部header -->
<div class="header">
	<div class="logo">
        <p>继续教育学院成绩管理系统</p>
    </div>
    <div class="heading_right">
        <div class="exit_active">
            <a href="${pageContext.request.contextPath}/AdminServlet?method=exit" onclick="javascript:window.top.close();" class="exit_active_message">
                <span class="glyphicon glyphicon-remove-circle"></span>
                <span>退出</span>
            </a>
        </div>
        <div class="user_message">
            <span class="glyphicon glyphicon-user"></span>
            <c:if test="${not empty sessionScope.session_user}">
            <span>${sessionScope.session_user.nickname }</span>
            </c:if>
        </div>
    </div>
</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>