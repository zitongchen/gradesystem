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
    <title>班级课程</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-perfession.css">
</head>
<body>
<div class="conntainer">
    <div class="row make-row class-course">
        <div class="col-sm-6 col-sm-offset-4 ">
            <p class="top-txt class-course-txt col-sm-8 col-sm-offset-1">添加班级课程</p>
        </div>
    </div>
    <div class="row make-row">
        <div class="col-sm-6 col-sm-offset-2">
            <form class="form-horizontal" method="post" role="form" action="${pageContext.request.contextPath}/AdminServlet">
            	<input type="hidden" name="method" value="addStudentGrade">
                <div class="form-group">
                    <label class="col-sm-4 control-label">专业</label>
                    <div class="col-sm-8">
                        <select id="zydm" class="form-control ">
                        	<option>请选择专业！</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">课程</label>
                    <div class="col-sm-8">
                        <select id="kc-select" name="kc" class="form-control">
                       		 <option>请选择课程！</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">老师</label>
                    <div class="col-sm-8">
                        <select id="js-select" name="expacount" class="form-control">
                        	<option>请选择老师！</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">班级</label>
                    <div class="col-sm-8">
                        <select id="bj-select" name="bh" class="form-control">
                        	<option>请选择班级！</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-6 col-sm-6">
                        <button type="submit" class="btn submit-btn btn-lg">提交</button>
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
	var root="<%=basePath%>"
</script>
<script src="${pageContext.request.contextPath}/js/manage-class-course.js"></script>
</body>
</html>