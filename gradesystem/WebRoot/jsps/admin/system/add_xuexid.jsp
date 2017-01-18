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
    <title>学习地点</title>
    <base target="body"/>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-perfession.css">
</head>
<body>
    <div class="conntainer">
        <div class="row make-row">
            <div class="col-sm-4 col-sm-offset-5 ">
                <p class="top-txt">添加学习地点</p>
            </div>
        </div>
        <div class="row make-row">
            <div class="col-sm-6 col-sm-offset-2">
                <form class="form-horizontal" action="${pageContext.request.contextPath}/AdminServlet" method="post"  role="form">
                	<input type="hidden" name="method" value="addXuexid"/> 
                    <div class="form-group">
                        <label for="inputXxddm" class="col-sm-4 control-label">学习点编码</label>
                        <div class="col-sm-8">
                            <input type="text" name="deparid" class="form-control" id="inputXxddm" required="required" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputXxdmc" class="col-sm-4 control-label">学习点名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="xxdd" class="form-control" id="inputXxdmc" required="required" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputXxddz" class="col-sm-4 control-label">学习点地址</label>
                        <div class="col-sm-8">
                            <input type="text" name="dizhi" class="form-control" id="inputXxddz" placeholder=""required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputLxr" class="col-sm-4 control-label">联系人</label>
                        <div class="col-sm-8">
                            <input type="text" name="lianxiren" class="form-control" id="inputLxr" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-4 control-label">邮箱地址</label>
                        <div class="col-sm-8">
                            <input type="email" name="email" class="form-control" id="inputEmail" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputYtdw" class="col-sm-4 control-label">依托单位</label>
                        <div class="col-sm-8">
                            <input type="text" name="ytdw" class="form-control" id="inputYtdw" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputLeixing" class="col-sm-4 control-label">单位类型</label>
                        <div class="col-sm-8">
                            <input type="text" name="leixing" class="form-control" id="inputLeixing">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputYongdate" class="col-sm-4 control-label">建立日期</label>
                        <div class="col-sm-8">
                            <input  type="text"  name="yongdate" class="form-control" id="inputYongdate" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputHuze" class="col-sm-4 control-label">负责人</label>
                        <div class="col-sm-8">
                            <input type="text" name="huze" class="form-control" id="inputHuze" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  for="inputTel" class="col-sm-4 control-label">联系电话</label>
                        <div class="col-sm-8">
                            <input type="text" name="telephone" class="form-control" id="inputTel" required="required">
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
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script>
		laydate({
		  elem: '#inputYongdate',
		  max: laydate.now(+0) //+1代表明天，+2代表后天，以此类推
		});
	</script>
</body>
</html>