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
    <title>个人信息</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage-personage-message.css">

</head>

<body>
<div class="conntainer">
    <div class="row make-row">
        <div  class="photo" title="支持jpg、jpeg、png格式，文件小于1M" >
            <c:if test="${ not empty expert.picture}">
            	<img name="expert" style="width:160px;height: 180px" src="${pageContext.request.contextPath}${expert.picture}">
            </c:if>
        	<c:if test="${ empty expert.picture}">
            	<img name="expert" class="picture" src="${pageContext.request.contextPath}/resource/images/he.png">
            </c:if>
        </div>
        <div class="col-sm-12">
            <table class="table table-striped table-bordered" >
                <tbody >
                    <tr>
                        <td >账号</td>
                        <td id="id" name="expert" class="col-sm-2 " >${expert.expacount}</td>
                        <td >姓名</td>
                        <td name="name" class="col-sm-2 change-txt ">${expert.name }</td>
                        <td >性别</td>
                        <td class="col-sm-2 ">${expert.sex}</td>
                    </tr>
                    <tr>
                        <td >学历</td>
                        <td class="col-sm-2 change-txt" name="education" >${expert.education}</td>
                        <td >职称</td>
                        <td class="col-sm-2" >${expert.title}</td>
                        <td >专家类型编码</td>
                        <td class="col-sm-2">${expert.expcode}</td>
                    </tr>
                    <tr>
                        <td >电话</td>
                        <td class="col-sm-2 change-txt " name="telephone">${expert.telephone}</td>
                        <td >qq</td>
                        <td class="col-sm-2 change-txt" name="qq">${expert.qq}</td>
                        <td >E-mail</td>
                        <td class="col-sm-2 change-txt" name="email">${expert.email}</td>
                    </tr>
                    <tr>
                        <td >微信</td>
                        <td class="col-sm-2 change-txt" name="weixin">${expert.weixin}</td>
                        <td >城市</td>
                        <td class="col-sm-2 change-txt" name="city">${expert.city}</td>
                        <td >状态</td>
                        <td class="col-sm-2">${expert.state}</td>
                    </tr>
                    <tr>
                        <td >注册时间</td>
                        <td class="col-sm-2" >${expert.regist_time}</td>
                        <td >所属单位编号</td>
                        <td class="col-sm-2">${expert.departid}</td>
                        <td ></td>
                        <td class="col-sm-2"></td>
                    </tr>
                    <tr>
                        <td >简介</td>
                        <td colspan="5" class="change-txt" name="description">${expert.description}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="pass-div ">
        	<a href=${pageContext.request.contextPath}/AdminServletOne?method=passExpert&expacount=${expert.expacount}>
        		<button class="btn pass-btn">审核通过</button>
        	</a>
        </div>
       
    </div>
</div>
    <script>
        var root='<%=basePath%>';
    </script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script  src="${pageContext.request.contextPath}/js/cropbox.js"></script>
    <script src="${pageContext.request.contextPath}/js/manage-personage-message.js"></script>
</body>
</html>