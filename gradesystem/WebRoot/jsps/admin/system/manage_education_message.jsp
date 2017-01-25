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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
	<div>
		<ul>
			<!--  <li><a href="${pageContext.request.contextPath}/jsps/admin/system/add_profession.jsp">添加专业</a></li>-->
			<li><a href="${pageContext.request.contextPath}/jsps/admin/system/course.jsp">课程信息</a></li>
			<!--<li><a href="${pageContext.request.contextPath}/jsps/admin/system/add_xuexid.jsp">添加学习地点</a></li>-->
			<li><a href="${pageContext.request.contextPath}/jsps/admin/system/add_class_course.jsp">添加班级课程</a></li>
			<li><a href="${pageContext.request.contextPath}/AdminServletOne?method=displayExpert">教师审核</a></li>
			<!--  <li><a href="${pageContext.request.contextPath}/jsps/admin/system/search.jsp">系统查询</a></li>-->
		</ul>
	</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>