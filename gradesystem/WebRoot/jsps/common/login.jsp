<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
	<script type="text/javascript">
	function _change(){
		var imgEle=document.getElementById("vCode");
		imgEle.src="${pageContext.request.contextPath}/VerificationServlet?"+new Date().getTime();
	}
	function _type(){
		var formEle=document.getElementById("form");
		var radioEle=document.getElementByTagName("radio");
		var value=radioEle.value();
		if(value[0]!=null){
			
		formEle.action="${pageContext.servletContext.contextPath}/UserServlet?method=login"
				alert(user)
		}else if(value[1]!=null){
		formEle.action="${pageContext.servletContext.contextPath}/ExpertServlet?method=login"
				alert(teacher);
			
		}else if(value[2]!=null){}
		formEle.action="${pageContext.servletContext.contextPath}/AdminServlet?method=login"
				alert(admin)
	}
	</script>
</head>
<!--  action="${pageContext.servletContext.contextPath}/ExpertServlet?method=login" -->
<body>
<form id="form" method="post"
 action="${pageContext.servletContext.contextPath}/ExpertServlet?method=login" >
	<div >
	  <p>${msg}</p>
	  <label>学号</label>
	  <input type="text"  placeholder="PremiumPixel" 
	  name="userId" value="${userId}"/>
	  <br>
	  <label>密码</label>
	  <input type="password"name="password" value="${password}"/>
	 	<br>
	  <label>验证码</label>
	  <input type="text" name="verification">
	  <p>${verificationError}</p>
	  <img id="vCode" src="${pageContext.request.contextPath}/VerificationServlet">
    <a href="javascript:_change()">看不清，换一张</a>
    <br>
    <input type="radio" name="type" value="user">学生
    <input type="radio" name="type" value="teacher">教师
    <input type="radio" name="type" value="admin">管理员
    
    <br>
	  <input type="submit"  value="登录" tabindex="4">
	</div>
</form>

</body>
  
</html>
