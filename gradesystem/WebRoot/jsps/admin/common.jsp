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
    <link href="${pageContext.request.contextPath}/css/sidebar.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="conntainer">
    <div class="row make-row">
        <div class="col-sm-6 col-sm-offset-3 common-box">
            <p class="common-heading">功能介绍</p>
            <ol class="common-boding">
                <li>成绩管理：先根据教师编号查询教师所教班级，选择要代教师录入成绩的班级（包含补考班级）</li>
                <li>成绩提交之前请先保存成绩，成绩提交之后不可再修改成绩</li>
                <li>学生信息管理：通过Excel表上传学生的信息，上传信息中学号必须要唯一</li>
                <li>学生信息管理：也可以下载学生信息的格式表（Excel)</li>
                <li>系统信息管理：添加专业，添加课程，添加学习地点，添加班级课程，教师审核，系统查询</li>
                <li>教师审核：教师注册后，审核其真实信息，若信息真实，审核通过后教师便可顺利登陆，否则不予登陆</li>
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