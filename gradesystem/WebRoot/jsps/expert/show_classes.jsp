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
	<title>班级</title>
	<base target="body"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
	<div>
		<ul>
			<c:if test="${empty requestScope.classList && empty requestScope.failList }">
				<p class="expert-text">不存在任教班级</p>
			</c:if>
			<c:if test="${not empty requestScope.classList}">
			    <c:forEach items="${requestScope.classList}" var="classList">
			    		<li>
				    		<a href='<c:url value="/ExpertServlet?method=findClassStudentByClass&classId=${classList}&expacount=${sessionScope.session_expert.expacount}"/>'>
				    			<c:out value="${classList}"></c:out>
				    		</a>
			    		</li>
			    </c:forEach>
			</c:if>
			<c:if test="${not empty requestScope.failList}">
			<c:forEach items="${requestScope.failList}" var="failList">
			    		<li>
				    		<a href='<c:url value="/ExpertServlet?method=findFailStudent&classId=${failList}&expacount=${sessionScope.session_expert.expacount}"/>'>
				    			<c:out value="${failList}"></c:out>[补考]
				    		</a>
			    		</li>
			    </c:forEach>
			</c:if>
		</ul>
	</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>

