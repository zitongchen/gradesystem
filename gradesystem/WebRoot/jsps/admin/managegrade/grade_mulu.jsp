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
<title>成绩管理</title>
<base target="body"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/sidebar.css" rel="stylesheet" style="text/css">
</head>
<body>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/jsps/admin/managegrade/manage_grade.jsp">代教师管理成绩</a></li>
			<li><a href="${pageContext.request.contextPath }/jsps/admin/managegrade/search_student_grade.jsp" target="body">学生成绩查询</a></li>	
			<li><a href="${pageContext.request.contextPath}/AdminServlet?method=displayNoAuditGrades">成绩审核</a></li>
		</ul>
	</div>
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>