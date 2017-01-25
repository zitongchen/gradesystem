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
	<title>学生信息导入</title>
	<base target="body"/>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
	<link href="${pageContext.request.contextPath }/css/sidebar.css" rel="stylesheet">
</head>
<body>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath }/jsps/admin/managestudent/add_student_message.jsp" target="body">上传班级信息</a></li>
			<li><a href="${pageContext.request.contextPath }/jsps/admin/managestudent/down_gradesheet.jsp" target="body">下载班级成绩登记表</a></li>	
			<li><a href="${pageContext.request.contextPath }/jsps/admin/managestudent/search_fail_grade.jsp" target="body">教学点补考成绩查询</a></li>	
			<li><a href="${pageContext.request.contextPath }/jsps/admin/managestudent/down_graduate_sheet.jsp" target="body">下载毕业班成绩表</a></li>	
		</ul>
	</div>
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>