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
<title>成绩录入</title>
<base target="body"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS.css" rel="stylesheet" style="text/css">
</head>
<body>

<li><a href="${pageContext.request.contextPath }/jsps/newPassword/class1.jsp" target="body">数据结构与算法实训【计算机科学与技术15(1)】</a></li>
<li><a href="${pageContext.request.contextPath }/jsps/newPassword/class2.jsp" target="body">数据结构与算法实训【计算机科学与技术15(2)】</a></li>
<li><a href="${pageContext.request.contextPath }/jsps/newPassword/class3.jsp" target="body">数据结构与算法实训【第一次补考】</a></li>
 
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>