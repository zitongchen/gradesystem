<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Profession</title>
    <base target="body"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-perfession.css">
</head>
<body>
    <div class="conntainer">
        <div class="row make-row">
            <div class="col-sm-4 col-sm-offset-5 ">
                <p class="top-txt">添加专业</p>
            </div>
        </div>
        <div class="row make-row">
            <div class="col-sm-6 col-sm-offset-2">
                <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServlet" method="post"  role="form">
                	<input type="hidden" name="method" value="addMaijor"/> 
                    <div class="form-group">
                        <label for="inputZydm" class="col-sm-4 control-label">专业编号</label>
                        <div class="col-sm-8">
                            <input type="text" name="zydm" class="form-control" id="inputZydm" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputZymc" class="col-sm-4 control-label">专业名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="zymc" class="form-control" id="inputZymc" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputXxxs" class="col-sm-4 control-label">学习形式</label>
                        <div class="col-sm-8">
                            <input type="text" name="xxxs" class="form-control" id="inputXxxs" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPycc" class="col-sm-4 control-label">培养层次</label>
                        <div class="col-sm-8">
                            <input type="text" name="pycc" class="form-control" id="inputPycc" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputXznx" class="col-sm-4 control-label">学制年限</label>
                        <div class="col-sm-8">
                            <input type="text" name="xznx" class="form-control" id="inputXznx">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputXz" class="col-sm-4 control-label">学制</label>
                        <div class="col-sm-8">
                            <input type="text" name="xz" class="form-control" id="inputXz">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputLy" class="col-sm-4 control-label">领域大类</label>
                        <div class="col-sm-8">
                            <input type="text" name="lyid" class="form-control" id="inputLy">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputBz" class="col-sm-4 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" name="bz" class="form-control" id="inputBz">
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
</body>
</html>