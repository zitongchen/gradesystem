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
    <title>班级成绩登记表</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
</head>
<body>
<div class="conntainer">
    <div class="row search-row">
        <div class="search-boding">
            <div class="col-sm-8 col-sm-offset-1">
                <div class="col-sm-10 col-sm-offset-2">
                    <p class="search-heading">班级成绩登记表</p>
                </div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServletOne" method="post" role="form">
                	<input type="hidden" name="method" value="searchClassByName">
                    <div class="col-sm-10 col-sm-offset-2">
                        <div class="input-group">
       
                            <input type="text" name="bh" placeholder="请输入班级名称，可输入部分信息..." value="${requestScope.bh}" class="form-control search-input" maxlength="30">
                            <span class="input-group-btn">
                                    <button type="submit" class="btn search-button">搜索一下</button>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
		<c:if test="${empty requestScope.classList }">
	        <div class="col-sm-9 col-sm-offset-1 error-message-boding">
	            <p class="error-message">查询不到班级</p>
	        </div>
        </c:if>

        <div class="col-sm-8 col-sm-offset-2 manage-grade-boding">
            <c:if test="${ not empty requestScope.classList }">
            	<c:forEach items="${requestScope.classList}"  var="classList">
            		<div class="col-sm-4 col-sm-offset-1">
                		<a href="${pageContext.request.contextPath}/ClassGradeSheetServlet?classId=${classList}"><button class="btn manage-grade-btn">${classList}</button></a>
            		</div>
            	</c:forEach>
            </c:if>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>