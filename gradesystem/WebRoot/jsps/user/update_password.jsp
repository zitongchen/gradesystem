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
    <title>密码修改</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/manage-personage-message.css">
</head>
<body>
    <div class="conntainer">
        <div class="row make-row">
            <div class="main-bodying  col-sm-6 col-sm-offset-3">
                <div class="">
                    <p class="bodying-heading">密码修改</p>
                </div>
                <form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet" method="post" role="form" onsubmit="return doupdate()">
                	<input type="hidden" name="method" value="updateUserPassword">
                    <div class="col-sm-8 col-sm-offset-2">
                    	<c:if test="${ not empty requestScope.errorMessage }">
                        	<p class="error-message">${requestScope.errorMessage}</p>
                    	</c:if>
                    </div>
                    <div class="form-group">
                        <label for="inputAcount" class="control-label col-sm-3">账号</label>
                        <div class="col-sm-8">
                            <input type="text" id="inputAcount" name="acount" class="form-control" value="${sessionScope.session_user.user_acount}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="oldpassword" class="control-label col-sm-3">原密码</label>
                        <div class="col-sm-8">
                            <input type="password" id="oldpassword" name="oldpassword" class="form-control" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password1" class="control-label col-sm-3">新密码</label>
                        <div class="col-sm-8">
                            <input type="password" id="password1" name="newpassword" class="form-control" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password2" class="control-label col-sm-3">确认密码</label>
                        <div class="col-sm-8">
                            <input type="password" id="password2" name="cheekpassword" class="form-control" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-4">
                            <button type="submit" class="btn submit-btn">确认</button>
                        </div>
                        <div class="col-sm-2 col-sm-offset-1 ">
                            <button type="reset" class="btn submit-btn">重置</button>
                        </div>
                    </div>
                </form>
                <div class="col-sm-8 col-sm-offset-2">
                    <p class="error-message">注：密码不得小于6位且必须包含数字、字母</p>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manage-personage-message.js"></script>
    </body>
</html>