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
    <title>Add Student</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/manage-student.css">
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<p><script>alert("${errorMessage}")</script> </p>
	</c:if>
	<c:if test="${not empty successMessage}">
		<p><script>alert("${successMessage}")</script></p>
	</c:if>
    <div class="conntainer">
        <div class="row make-row">
            <div class="col-sm-6 col-sm-offset-3 main-bodying">
                <form class="form" action="<c:url value="/AdminServletOne?method=inputStudentMessage"></c:url>" enctype="multipart/form-data" method="post" role="form">
                    <div class="form-group">
                        <p class="main-toping">请上传班级信息的Excel表</p>
                        <input type="file" name="studentExcelMessage" placeholder="Excel" class="form-control file-input">
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-5">
                            <button type="submit" class="btn default-btn submit-btn">上传</button>
                        </div>
                    </div>
                </form>
                <div class="col-sm-10 col-sm-offset-3 remark">
                    <span>注：</span>
                    <a href="${pageContext.request.contextPath}/AdminServletOne?method=downloadUserExcel">学生信息格式表(Excel)下载</a>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
