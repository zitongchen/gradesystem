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
<title>个人中心</title>
<base target="body"/>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS.css" rel="stylesheet" style="text/css">
</head>
<body>
<!--
<div id="tabs">
    <div>  
         <ul>
             <li><a href="personal.html">教师基本信息</a></li>
             <li><a href="modifyPassword.html">密码修改</a></li>
         </ul>
    </div>
</div>
-->
            <li><a href="${pageContext.request.contextPath}/jsps/admin/personal_table.jsp">管理员基本信息</a></li>
            <li><a href="${pageContext.request.contextPath}/jsps/admin/update_password.jsp">密码修改</a></li>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="myJS.js"></script>
  </body>
</html>