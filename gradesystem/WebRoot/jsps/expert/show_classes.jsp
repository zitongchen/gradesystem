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


<c:if test="${not empty requestScope.classList}">
	    <c:forEach items="${requestScope.classList}" var="classList">
	    		<li>
		    		<a href='<c:url value="/ExpertServlet?method=findClassStudentByClass&classId=${classList.bh}&expacount=${sessionScope.session_expert.expacount}"/>'>
		    			<c:out value="${classList.className}"></c:out>
		    		</a>
	    		</li>
	    </c:forEach>
</c:if>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-parent.js"></script><!-- 本地的bootstrap资源链接 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>