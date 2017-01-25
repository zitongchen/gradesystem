<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>毕业生成绩下载</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-perfession.css">
</head>
<body>
<c:if test="${not empty errorMessage }">
	<script type="text/javascript">
		alert('${errorMessage}');
	</script>
</c:if>
<div class="conntainer">
    <div class="row make-row class-course">
        <div class="col-sm-6 col-sm-offset-4 ">
            <p class="top-txt class-course-txt col-sm-8 ">毕业生成绩登记表下载</p>
        </div>
    </div>
    <div class="row make-row">
        <div class="col-sm-6 col-sm-offset-2">
            <form class="form-horizontal" method="post" role="form" action="${pageContext.request.contextPath}/DownGraduateGradeSheet">
                <div class="form-group">
                    <label class="col-sm-4 control-label">专业</label>
                    <div class="col-sm-8">
                        <select   class="form-control maijor-select">
                        	<option>请选择专业</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">班级</label>
                    <div class="col-sm-8">
                        <select name="bh" class="form-control bj-select">
                        	<option>请选择班级</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">学院</label>
                    <div class="col-sm-8">
                       <input name="college" type="text" class="form-control" value="广东药科大学继续教育学院">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-7 col-sm-6">
                        <button type="submit" class="btn submit-btn btn-lg">下载</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var root='<%=basePath%>';
</script>
<script src="${pageContext.request.contextPath}/js/graduate-sheet.js"></script>
</body>
</html>