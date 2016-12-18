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
<title>学习信息</title>
<base target="body"/>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>
	<form action="${pageContext.request.contextPath}/AdminServlet?method=downloadUserExcel"  method="post">
	<c:if test="${not empty errorMessage}">
			<p><script>alert("${errorMessage}")</script> </p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p><script>alert("${successMessage}")</script></p>
		</c:if>
		<input type="submit" value="请下载学生信息导入格式表"/>
	</form>
	<form class="form-horizontal" 
	action="<c:url value="/AdminServlet?method=inputStudentMessage"></c:url>" method="post" enctype="multipart/form-data">
		
		<c:if test="${not empty errorMessage}">
			<p><script>alert("${errorMessage}")</script> </p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p><script>alert("${successMessage}")</script></p>
		</c:if>
		
		<div class="control-group">
			<label class="control-label" for="inputExcel">请导入学生信息的Excel表</label>
			<div class="controls">
				<input type="file" id="inputExcel" name="studentExcelMessage" placeholder="Excel" value="">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">上传</button>
			</div>
		</div>
	</form>
	
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>