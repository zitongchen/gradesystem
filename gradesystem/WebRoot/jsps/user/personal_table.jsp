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
<title>个人中心</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/studentCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>

<!--div class="mainbody"-->
<div>
<h4 class="text-center">考生基本信息</h4>
<div class="container-fluid" >
    <div class="col-md-10" >
<table class="table table-striped table-bordered" style="table-layout:fixed">
    <tbody >
        <tr>
            <td class="col-md-1">学号</td>
            <td >${session_user.user_acount}</td>
            <td class="col-md-1">姓名</td>
            <td >${session_user.nickname}</td>  
             <td class="col-md-1">出生年月</td>
            <td >${session_user.csrq}</td>          
        </tr>
       <tr>
            <td >性别</td>
            <td>${session_user.xb}</td>
            <td >贯籍</td>
            <td></td>   
            <td >入学年月</td>
            <td>${session_user.rxrq}</td>                  
        </tr>
        <tr>
            <td >民族</td>
            <td >${session_user.mz}</td>
            <td >专业</td>
            <td>${session_user.zymc}</td>   
            <td >专业方向</td>
            <td></td>                  
        </tr>
       <tr>
            <td >学院</td>
            <td ></td>
            <td >班级</td>
            <td></td>   
            <td >身份证号</td>
            <td></td>                  
        </tr>
        <tr>
            <td >年级</td>
            <td></td>
            <td >联系电话</td>
            <td></td>   
            <td >考生号</td>
            <td></td>                  
        </tr>
               <tr>
            <td >准考证号</td>
            <td ></td>
            <td >学制</td>
            <td></td>   
            <td >考生类别</td>
            <td></td>                  
        </tr>
               <tr>
            <td >出生所在地</td>
            <td ></td>
            <td >家庭住址</td>
            <td></td>   
            <td >邮政编码</td>
            <td></td>                  
        </tr>
               <tr>
            <td >政治面貌</td>
            <td ></td>
            <td >入学总分</td>
            <td></td>   
            <td >学籍状态</td>
            <td></td>                  
        </tr>
    </tbody>
</table>
    </div>
    <div class="col-md-offset-1">
        <img src="../images/ljc.jpg" width="135" height="180">
    </div>
</div>	
</div>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>