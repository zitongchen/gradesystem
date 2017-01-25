<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
</head>
<body>
<div class="conntainer">
    <div class="row search-row">
        <div class="search-boding">
            <div class="col-sm-8 col-sm-offset-1">
                <div class="col-sm-10 col-sm-offset-2">
                    <p class="search-heading">学生成绩查询</p>
                </div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServlet" method="post" role="form">
                	<input type="hidden" name="method" value="searchStudentGrade">
                    <div class="col-sm-10 col-sm-offset-2">
                        <div class="input-group">
                            <input type="text" name="text" class="form-control search-input" maxlength="30" placeholder="请输入学生学号或姓名...">
                            <span class="input-group-btn">
                                    <button type="submit" class="btn search-button">搜索一下</button>
                                </span>
                        </div>
                        <div class="form-group">
                            <div class="search-btn-boding">
                                <div class="radio col-sm-11 col-sm-offset-5">
                                    <label class="radio-inline" >
                                        <input name="search_type" type="radio" value="acount" checked>学号
                                    </label>
                                    <label class="radio-inline">
                                        <input name="search_type" type="radio" value="nickname">姓名
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-6 col-sm-offset-4">
            <p >注：为避免重名情况导致信息有误，请使用学号查询！</p>
        </div>
        <c:if test="${not empty errorMessage}">
	        <div class="col-sm-9 col-sm-offset-1 error-message-boding">
	            <p class="error-message">不存在该学生的成绩信息！</p>
	        </div>
        </c:if>

    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>