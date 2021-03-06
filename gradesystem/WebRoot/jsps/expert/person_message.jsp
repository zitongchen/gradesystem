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
            	<img name="expert" class="picture" style="width:160px;height: 180px" src="${pageContext.request.contextPath}${expert.picture}">
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
            <p class="col-sm-12">备注：点击头像可更新头像，点击个人信息可更新个人信息，但并不是所有信息都可修改哦！</p>
        </div>
        <div class="beijin"></div>
        <div class="container">
            <div class="imageBox">
                <div class="thumbBox"></div>
                <div class="spinner" style="display: none">Loading...</div>
            </div>
            <div class="action">
                <div class="new-contentarea tc"> <a href="javascript:void(0)" class="upload-img">
                    <label for="upload-file">选择图像</label>
                </a>
                    <input type="file" class="" name="upload-file" id="upload-file" />
                </div>
                <input type="button" id="btnCancel" class="Btnsty_peyton" value="取消">
                <input type="button" id="btnCrop"  class="Btnsty_peyton" value="确定">

            </div>
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