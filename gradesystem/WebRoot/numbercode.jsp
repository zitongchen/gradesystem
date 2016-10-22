<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'numbercode.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function _change(){
		var imgEle=document.getElementById("vCode");
		imgEle.src="${pageContext.request.contextPath}/VerificationServlet?"+new Date().getTime();
	}
	</script>
  </head>
  
  <body>
    <h3>验证码</h3>
    <img id="vCode" src="${pageContext.request.contextPath}/VerificationServlet">
    <a href="javascript:_change()">看不清，换一张</a>
    
    <a href="${pageContext }"></a>
   <fmt:formatDate value="" pattern="yyyy-MM-dd"/>
 
  </body>
</html>
