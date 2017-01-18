<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统功能介绍</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
</head>
<body>
<div class="conntainer">
    <div class="row make-row">
        <div class="col-sm-6 col-sm-offset-3 common-box">
            <p class="common-heading">功能使用</p>
            <ol class="common-boding">
                <li>成绩管理：录入所教班级的成绩；若有补考，录入补考班级的成绩</li>
                <li>成绩提交之前请先保存成绩，成绩提交之后不可再修改成绩</li>
                <li>个人中心：个人信息包含头像上传，信息修改；修改密码可更新密码</li>
            </ol>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>