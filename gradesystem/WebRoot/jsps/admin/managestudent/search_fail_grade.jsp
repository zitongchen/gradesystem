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
    <title>查询补考名单</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
</head>
<body>
<div class="conntainer">
    <div class="row search-row">
        <div class="search-boding">
            <div class="col-sm-8 col-sm-offset-1">
                <div class="col-sm-10 col-sm-offset-2">
                    <p class="search-heading">教学点补考信息查询</p>
                </div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServletOne" method="post" role="form">
                	<input type="hidden" name="method" value="searchFailStudentByXuxid">
                    <div class="col-sm-10 col-sm-offset-2">
                        <div class="input-group">
                            <select name="jxd" class="form-control jxd-select">
                                <option>请选择教学点</option>
                            </select>
                            <span class="input-group-btn">
                                    <button type="submit" class="btn search-button">搜索一下</button>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${not empty requestScope.errorMessage}">
	        <div class="col-sm-9 col-sm-offset-1 error-message-boding">
	                <p class="error-message">${errorMessage}</p>
	        </div>
        </c:if>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script >
 	var root='<%=basePath%>';
</script>
<script src="${pageContext.request.contextPath}/js/searchFailstudent.js"></script>
</body>
</html>