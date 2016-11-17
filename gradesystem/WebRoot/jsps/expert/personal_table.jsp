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
    <link href="${pageContext.request.contextPath }/css/teacherCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>

<div class="mainbody">
  <h4 class="text-center">教师基本信息</h4>
  <div class="container-fluid" >
      <div class="col-xs-10" >
          <table class="table table-striped table-bordered" style="table-layout:fixed">
              <tbody >
                  <tr>
                      <td class="col-xs-1">教师号</td>
                      <td >xxxxxxxxx</td>
                      <td class="col-xs-1">姓名</td>
                      <td >xxx</td>  
                      <td class="col-xs-1">出生年月</td>
                      <td >xxxx-xx-xx</td>          
                  </tr>
              </tbody>
          </table>
      </div>
      <div class="col-xs-1"></div>
      <div class="col-xs-offset-1">
          <img src="images/ljc.jpg" width="135" height="180">
      </div>
  </div>	
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-parent.js"></script><!-- 本地的bootstrap资源链接 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="myJS.js"></script>
</body>
</html>