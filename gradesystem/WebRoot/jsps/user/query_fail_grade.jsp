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
    <link href="${pageContext.request.contextPath}/css/studentCSS.css" rel="stylesheet" style="text/css">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->	
<div>
	<div class="col-xs-10">

    <!-- 面包屑导航 -->
    <ol class="breadcrumb">
        <li>成绩查询</li>
        <li class="active">学生补考成绩查询</li>
    </ol>

      <div id="t3">
           <!-- 先定义一个表格，利用ajax首先加载一次数据。 -->
          <table id="t3" class="table table-striped" >
                <thead>
                    <tr>
                         <th>课程名称</th>           
                         <th>任课教师号</th>
                         <th>考核类型</th>         
                        <th>总评成绩</th>
                    </tr>
                </thead>
               <c:if test="${not empty studentGradeList}">
               	<c:forEach var="gradeList" items="${requestScope.studentGradeList}">
	                <tbody>
	                <tr>
	                    <td>${gradeList.docourse.title}</td>
	                    <td>${gradeList.expert.name}</td>
	                    <td>${gradeList.gradelei }</td>
	                    <td>${gradeList.grade}</td>
	                  </tr>
	               </tbody>
               	</c:forEach>
               </c:if>
           </table>



        </div> 
    </div>
</div>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>