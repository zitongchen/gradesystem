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
    <title>未审核班级课程</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/system.css">
</head>
<body>
<div class="conntainer">
    <div class="row col-sm-12">
        <p class="top-txt">待审核班级成绩</p>
        <c:if test="${not empty requestScope.errorMessage}">
        	<p class="errorMessage">不存在未审核的班级</p>
        </c:if>
        <div class="col-sm-11 col-sm-offset-1">
            <form class="form-horizontal" method="post" role="form" action="${pageContext.request.contextPath}/AdminServlet">
            	<input type="hidden" name="method" value="auditGrades">
            	<c:if test="${not empty requestScope.gradeList }">
	                <div class="checkbox">
	                	<c:forEach items="${requestScope.gradeList}" var="grade">
		                    <label class="col-sm-6 check-box">
		                        <input type="checkbox" name="items" value="${grade.bh}+${grade.visit_count}">
		                       	 班级：${grade.bh } 课程：${grade.title}
		                        <a class="link-box" href="${pageContext.request.contextPath}/AdminServlet?method=searchClassGrade&bh=${grade.bh}&visit_count=${grade.visit_count}"><button type="button" class="check-btn">审核</button></a>
		                    </label>
	                   	</c:forEach>
	

	                    <label class="col-sm-12">
	                        <input type="checkbox" id="all" value="">
	                        	全选
	                    </label>
	                </div>
	                <div class="form-group">
	                    <div class="col-sm-offset-5 col-sm-6">
	                        <button type="submit" class="btn submit-btn ">批量审核</button>
	                    </div>
	                </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script>
    $(function() {
        //全选/全不选
        $("#all").click(function () {

            if (this.checked) {
                $("[name=items]:checkbox").attr("checked", true);
            }else{
                $("[name=items]:checkbox").attr("checked", false);
            }
        });


    })
</script>
</body>
</html>