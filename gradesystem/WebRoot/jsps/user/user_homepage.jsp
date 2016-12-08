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
<title>首页</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/studentCSS.css" rel="stylesheet" style="text/css">
</head>
<body>
<!-- 以下是头部header -->
<iframe width="101%" height="65" name="top" frameborder="0" src="${pageContext.request.contextPath}/jsps/user/header.jsp" scrolling="no" style="margin-left: -7px; "></iframe>


<!-- 以下是菜单menu以及左侧的二级菜单 -->
<div id="tabs">
    <ul id="nav0">
        <li ><a href="${pageContext.request.contextPath}/jsps/user/sidebar.jsp" target="sidebar">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/user/query.jsp" target="sidebar">成绩查询</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/user/personal.jsp" target="sidebar">个人中心</a></li>
    </ul>
    <div>		
         <p class="left_title">相关操作</p>
         <ul>
             <iframe name="sidebar" src="${pageContext.request.contextPath}/jsps/user/sidebar.jsp" width="100%" height="91%" frameborder="0"></iframe>
         </ul>
    </div>
</div>

<!-- 以下是内容主体部分mainbody -->	
<iframe width="83%" height="550" name="body" src="${pageContext.request.contextPath}/jsps/user/inform.jsp" style="position:absolute; top: 105px; left: 210px;" frameborder="0" ></iframe>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>