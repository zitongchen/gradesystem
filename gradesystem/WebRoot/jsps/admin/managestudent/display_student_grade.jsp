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
    <title>成绩查询</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->
<div>
    <div class="col-sm-12">

        <!-- 面包屑导航 -->
        <ol class="breadcrumb">
            <li>成绩查询</li>
            <li class="active">个人成绩</li>
        </ol>
        <div>
            <table  class="table table-striped" >
                <thead>
	                <tr>
	                	<th>学号</th>
	                    <th>姓名</th>
	                    <th>课程编号</th>
	                    <th>课程名称</th>
	                    <th>任课教师</th>
	                    <th>平时成绩</th>
	                    <th>实验成绩</th>
	                    <th>考试成绩</th>
	                    <th>总评成绩</th>
	                    <th>补考成绩</th>
	                </tr>
                </thead>
                <c:if test="${not empty studentGradeList }">
	                <tbody>
		                <c:forEach items="${studentGradeList}" var="stugrade">
			                <tr>
			                	<td>${stugrade.user_acount}</td><!-- 学号 -->
			                    <td>${stugrade.nickname}</td><!-- 姓名 -->
			                    <td>${stugrade.visit_count}</td><!-- 课程号 -->
			                    <td>${stugrade.title}</td><!-- 课程名称 -->
			                    <td>${stugrade.expert.name}</td><!-- 教师姓名 -->
			                    <td>${stugrade.psscore}</td><!-- 总评成绩 -->
			                    <td>${stugrade.syscore}</td><!-- 总评成绩 -->
			                    <td>${stugrade.ksscore}</td><!-- 总评成绩 -->
			                    <td>${stugrade.totalscores}</td><!-- 总评成绩 -->
			                    <td>${stugrade.bkscore}</td><!-- 补考成绩 -->
			                </tr>
		                </c:forEach>
	                </tbody>
                </c:if>
                
            </table>
        </div>
    </div>
</div>

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>