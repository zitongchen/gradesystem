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
<title>个人中心表格</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/teacherCSS2.css" >
</head>
<body>

<div class="mainbody">
  <h4 class="text-center">教师基本信息</h4>
  <div class="container-fluid" >
      <div class="col-xs-10" >
      	<c:if test="${not empty sessionScope.session_expert}">
      		<c:set var="expert" value="${sessionScope.session_expert}"></c:set>
      		
      	</c:if>
          <table class="table table-striped table-bordered" style="table-layout:fixed">
              <tbody >
                  <tr>
                      <td class="col-xs-1">教师号</td>
                      <td >${expert.expacount}</td>
                      <td class="col-xs-1">姓名</td>
                      <td >${expert.name}</td>  
                      <td class="col-xs-1">出生年月</td>
                      <td ></td>          
                  </tr>
                   <tr>
			            <td >性别</td>
			            <td>${expert.sex}</td>
			            <td >职称</td>
			            <td>${expert.title}</td>   
			            <td >注册时间</td>
			            <td>${expert.regtime}</td>                  
			        </tr>
			        <tr>
			            <td >学历</td>
			            <td >${expert.education}</td>
			            <td >QQ号码</td>
			            <td>${expert.qq}</td>   
			            <td >电话</td>
			            <td>${expert.telephone}</td>                  
			        </tr>
			       <tr>
			            <td >E-mail</td>
			            <td >${expert.email}</td>
			            <td >微信号</td>
			            <td>${expert.misigal}</td>   
			            <td >城市</td>
			            <td>${expert.city}</td>                  
			        </tr>
			        <tr>
			            <td >状态</td>
			            <td>${expert.state}</td>
			            <td >专家类型编码</td>
			            <td>${expert.expcode}</td>   
			            <td >简介</td>
			            <td>${expert.description}</td>                  
			        </tr>
              </tbody>
          </table>
      </div>
      <div class="col-xs-1"></div>
      <div class="col-xs-offset-1">
          <img src="${pageContext.request.contextPath}/resource/images/ljc.jpg" width="135" height="180">
      </div>
  </div>	
</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="myJS.js"></script>
</body>
</html>