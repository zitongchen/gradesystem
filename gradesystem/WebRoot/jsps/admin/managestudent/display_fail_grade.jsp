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
<title>补考成绩查询</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->	

<!-- 以下是内容主体部分mainbody -->
<div>
    <div class="col-sm-12">

        <!-- 面包屑导航 -->
        <ol class="breadcrumb">
            <li>成绩查询</li>
            <li class="active">教学点:${requestScope.jxd}</li>
        </ol>
        <div>
            <table  class="table table-striped" >
                <thead>
	                <tr>
	                	<th>学号</th>
	                	<th>姓名</th>
	                    <th>课程名称</th>
	                    <th>总评成绩</th>
	                </tr>
                </thead>
                <c:if test="${not empty studentList}">
	                <tbody>
		                <c:forEach items="${studentList}" var="stugrade">
			                <tr>
			                	<td>${stugrade.user_acount}</td>
			                	<td>${stugrade.nickname}</td>
			                    <td>${stugrade.title}</td><!-- 课程名称 -->
			                    <td>${stugrade.totalscores}</td><!-- 总评成绩 -->
			                </tr>
		                </c:forEach>
	                </tbody>
                </c:if>
                
            </table>
            <div class="col-sm-2 col-sm-offset-5">
                <a href="${pageContext.request.contextPath}/DownFailGradeByJxd?jxd=${jxd}"><button>下载补考名单</button></a>
            </div>
        </div>
    </div>
</div>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>