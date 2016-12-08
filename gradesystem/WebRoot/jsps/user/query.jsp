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
<base target="body"/>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/studentCSS.css" rel="stylesheet" style="text/css">
</head>
<body>
<!-- 以下是菜单menu以及左侧的二级菜单 -->

    
<div>	
    <ul>
        <li><a href="${pageContext.request.contextPath}/UserServlet?method=queryUserGrade&user_acount=${session_user.user_acount}">学生成绩查询</a></li>
        <li><a href="${pageContext.request.contextPath}/UserServlet?method=queryUserFail&user_acount=${session_user.user_acount}">学生补考成绩查询</a></li>
    </ul>
</div>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>