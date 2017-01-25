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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/system.css">
</head>
<body>
<div class="conntainer">
    <div class="row make-row">
        <p class="top-txt">待审核教师</p>
        <div class="col-sm-10 col-sm-offset-1">
        	<c:if test="${empty requestScope.expertList }">
        		<p class="errorMessage">不存在未审核的教师</p>
        	</c:if>
        	<c:if test="${ not empty requestScope.expertList }">
        		<c:forEach items="${requestScope.expertList }" var="expert">
		            <div class="message-bodying col-sm-12">
		                <p class="col-sm-3 ">账号：${expert.expacount}</p>
		                <p class="col-sm-4 ">用户名：${expert.name}</p>
		                <div class="col-sm-1 col-sm-offset-4">
		                    <a href="${pageContext.request.contextPath}/AdminServletOne?method=checkExpert&expacount=${expert.expacount}">
		                    	<button class="btn ">审核</button>
		                    </a>
		                </div>
		            </div>
	            </c:forEach>
            </c:if>
            

        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script>
</script>
</body>
</html>