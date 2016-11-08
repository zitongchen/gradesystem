<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	    	<c:if test="${not empty sessionScope.session_expert}">
	    		<c:out value="${sessionScope.session_expert.expacount}"></c:out>
	    		<c:out value="${sessionScope.session_expert.name}"></c:out>
	    		<c:out value="${sessionScope.session_expert.nickname}"></c:out>
	    	</c:if>
	    	</br>
	    	<button><a href="${pageContext.request.contextPath}/ExpertServlet?method=exit">退出</a></button>
	    	</br>
	    	<button><a href="jsps/expert/update_expert_password.jsp">修改密码</a></button>
	    	</br>
	    	<button><a href="${pageContext.request.contextPath}/ExpertServlet?method=findClassNameByExpert&expacount=${sessionScope.session_expert.expacount}">显示班级</a></button>
	    	<c:if test="${not empty requestScope.class_id}">
	    		<c:forEach items="${requestScope.class_id}" var="className">
	    			<a href='<c:url value="/ExpertServlet?method=findClassStudentByClass&classId=${className}&expacount=${sessionScope.session_expert.expacount}"/>'><c:out value="${className}"></c:out></a>
	    		</c:forEach>
	    	</c:if>
  </body>
</html>
