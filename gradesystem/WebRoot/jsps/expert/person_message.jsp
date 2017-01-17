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
            <img name="expert" class="picture" src="${pageContext.request.contextPath}/resource/images/he.png">
        </div>
        <div class="col-sm-12">
            <table class="table table-striped table-bordered" >
                <tbody >
                    <tr>
                        <td >学号</td>
                        <td id="id" name="xuehao" class="col-sm-2 change-txt" >1405553103</td>
                        <td >姓名</td>
                        <td name="name" class="col-sm-2 change-txt">李金城</td>
                        <td >出生年月</td>
                        <td class="col-sm-2 change-txt">xxx-xx-xx</td>
                    </tr>
                    <tr>
                        <td >性别</td>
                        <td class="col-sm-2" >男</td>
                        <td >贯籍</td>
                        <td class="col-sm-2">广东梅州</td>
                        <td >入学日期</td>
                        <td class="col-sm-2">xxx-xx-xx</td>
                    </tr>
                    <tr>
                        <td >名族</td>
                        <td class="col-sm-2" >汉族</td>
                        <td >专业</td>
                        <td class="col-sm-2">计算机科学与技术</td>
                        <td >专业方向</td>
                        <td class="col-sm-2">医学应用</td>
                    </tr>
                </tbody>
            </table>
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