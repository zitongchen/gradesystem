<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/active-message.css">
</head>
<body>
<div class="conntainer">
    <div class="row make-row">
        <div class="col-sm-6 col-sm-offset-2 mainbodying">
        	<c:if test="${not empty requestScope.successMessage }">
	            <div class="success-box">
	                <p class="success-heading">${requestScope.successMessage }</p>
	                <p class="success-txt">${requestScope.successTxt}</p>
	            </div>
            </c:if>
            <c:if test="${not empty requestScope.errorMessage}">
	            <div class="error-box">
	                <p class="error-heading">${requestScope.errorMessage}</p>
	                <p class="error-txt"></p>
	            </div>
            </c:if>
        </div>
    </div>
</div>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>