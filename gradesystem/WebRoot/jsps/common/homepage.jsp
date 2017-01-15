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
    <link href="${pageContext.request.contextPath}/css/main-style.css" type="text/css" rel="stylesheet">
</head>
<body>
<!-- 以下是头部header -->
<div class="heading">
    <iframe frameborder="0" src="${pageContext.request.contextPath}/jsps/common/header.jsp" scrolling="no" ></iframe>
</div>
<!-- 以下是菜单menu以及左侧的二级菜单 -->
<div class="bodying" id="main-bodying">
    <div class="left">
        <div class="left_body">
            <p class="left_title">相关操作</p>
            <iframe name="sidebar" src="${pageContext.request.contextPath}/jsps/common/sidebar.jsp" frameborder="0"></iframe>
        </div>
    </div>
    <div class="right">
        <div class="right_top">
            <ul class="navigation">
            	<c:if test="${not empty requestScope.session.session_user}">
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/sidebar.html" target="sidebar">首页</a></li>
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/query.html" target="sidebar">成绩查询</a></li>
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/personal.html" target="sidebar">个人中心</a></li>
                </c:if>
                <c:if test="${not empty requestScope.session.session_expert}">
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/sidebar.html" target="sidebar">首页</a></li>
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/query.html" target="sidebar">成绩查询</a></li>
	                <li class="navigation-li"><a class="navigation-li-a" href="../user/personal.html" target="sidebar">个人中心</a></li>
                </c:if>
                <c:if test="${not empty session_admin}">
	                <li class="navigation-li"><a class="navigation-li-a" href="${pageContext.request.contextPath}/jsps/common/sidebar.jsp" target="sidebar">首页</a></li>
			        <li class="navigation-li"><a class="navigation-li-a" href="${pageContext.request.contextPath}/jsps/admin/show_classes.jsp" target="sidebar">成绩管理</a></li>
			        <li class="navigation-li"><a class="navigation-li-a" href="${pageContext.request.contextPath}/jsps/admin/manage_student_message.jsp" target="sidebar">学生信息管理</a></li>
			        <li class="navigation-li"><a class="navigation-li-a" href="${pageContext.request.contextPath}/jsps/admin/manage_education_message.jsp" target="sidebar">系统信息管理</a></li>
			        <li class="navigation-li"><a class="navigation-li-a" href="${pageContext.request.contextPath}/jsps/admin/personal.jsp" target="sidebar">个人中心</a></li>
                </c:if>
            </ul>
        </div>
        <div id="right-iframe" class="right_bottom">
            <iframe name="body" src="${pageContext.request.contextPath}/jsps/common/sidebar.jsp" frameborder="0" scrolling="yes"></iframe>
        </div>
    </div>
</div>

    <script>
        window.onresize= function(){
            total = document.documentElement.clientHeight;
            main_bodying_Height = total-4-document.getElementById("main-bodying").offsetTop;
            document.getElementById("main-bodying").style.height=main_bodying_Height+"px";
            main_bodying_right_iframe_Height = total-104-document.getElementById("right-iframe").offsetTop;
            document.getElementById("right-iframe").style.height=main_bodying_right_iframe_Height+"px";
        }
        window.onload= function(){
            total = document.documentElement.clientHeight;
            main_bodying_Height = total-4-document.getElementById("main-bodying").offsetTop;
            document.getElementById("main-bodying").style.height=main_bodying_Height+"px";
            main_bodying_right_iframe_Height = total-104-document.getElementById("right-iframe").offsetTop;
            document.getElementById("right-iframe").style.height=main_bodying_right_iframe_Height+"px";
        }
    </script>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>