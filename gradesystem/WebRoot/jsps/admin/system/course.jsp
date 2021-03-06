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
    <title>Add Course</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/system.css">
</head>
<body>
<div class="conntainer">
    <div class="row col-sm-12">
        <p class="top-txt">课程信息</p>
        <!--<p class="errorMessage">暂时未存在课程信息</p>-->
        <div class="col-sm-10 col-sm-offset-1 ">
            <a><button class="message-btn add-btn show-course">添加课程.......</button></a>
        </div>
    </div>
    <div class="row col-sm-12 add-course">
        <div class="col-sm-4 col-sm-offset-4 ">
            <p class="top-txt">添加课程</p>
        </div>
    </div>
    <div class="row col-sm-12 add-course">
        <div class="col-sm-6 col-sm-offset-2">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServlet" method="post" role="form">
            	<input type="hidden" name="method" value="addObjcenter"/><!-- 使用AdminServlet中的addObjcenter方法 -->
                
                <div class="form-group">
                    <label for="inputKcmc" class="col-sm-4 control-label">课程名称</label>
                    <div class="col-sm-8">
                        <input type="text" name="title" class="form-control" id="inputKcmc" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label   class="col-sm-4 control-label">专业代码</label>
                    <div class="col-sm-8">
                        <select name="zydm"  class="form-control zydm-select">
                            <option value="null" selected="selected">请选择专业</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputXxss" class="col-sm-4 control-label">学习时数</label>
                    <div class="col-sm-8">
                        <input type="text" name="sthours" class="form-control" id="inputXxss" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMsss" class="col-sm-4 control-label">面授时数</label>
                    <div class="col-sm-8">
                        <input type="text" name="classhour" class="form-control" id="inputMsss" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label  for="inputSyss" class="col-sm-4 control-label">实验时数</label>
                    <div class="col-sm-8">
                        <input type="text" name="sbhour" class="form-control" id="inputSyss" required="required" >
                    </div>
                </div>
                <div class="form-group">
                    <label  for="inputFlid" class="col-sm-4 control-label">领域大类</label>
                    <div class="col-sm-8">
                        <input type="text" name="lyid" class="form-control" id="inputFlid">
                    </div>
                </div>
                <div class="form-group">
                    <label  for="inputXf" class="col-sm-4 control-label">学分</label>
                    <div class="col-sm-8">
                        <input type="text" name="score" class="form-control" id="inputXf" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputSyzt" class="col-sm-4 control-label">使用状态</label>
                    <div class="col-sm-8 sex-radio">
                        <div class="radio">
                            <label class="radio-inline" >
                                <input name="states" type="radio" value="1" checked>使用
                            </label>
                            <label class="radio-inline">
                                <input name="states" type="radio" value="0">未使用
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">开课学期</label>
                    <div class="col-sm-3">
                        <select name="termth" class="form-control">
                            <option value="1" selected="selected">1</option>
                            <option value="2" >2</option>
                            <option value="3" >3</option>
                            <option value="4" >4</option>
                            <option value="5" >5</option>
                            <option value="6" >6</option>
                            <option value="7" >7</option>
                            <option value="8" >8</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label  for="inputBz" class="col-sm-4 control-label">备注</label>
                    <div class="col-sm-8">
                        <input type="text" name="remark" class="form-control" id="inputBz" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">简介</label>
                    <div class="col-sm-8">
                        <textarea name="description" placeholder="请输入课程简介..."  class="form-control" rows="4"></textarea>
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
	<!--导入外部的JS文件-->
	<script  type="text/javascript">
    	var root="<%=basePath%>";
    </script>
	<script src="${pageContext.request.contextPath}/js/course.js"></script>
</body>
</html>