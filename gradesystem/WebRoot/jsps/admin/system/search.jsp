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
    <title>系统搜索</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/search.css">
</head>
<body>
    <div class="conntainer">
        <div class="row search-row">
            <div class="search-boding">
                <div class="col-sm-8 col-sm-offset-1">
                    <div class="col-sm-10 col-sm-offset-2">
                        <p class="search-heading">系统查询</p>
                    </div>
                    <form class="form-horizontal" action="#" method="post" role="form">
                        <div class="col-sm-10 col-sm-offset-2">
                            <div class="input-group">
                                <input type="text" class="form-control search-input" maxlength="30" placeholder="请输入查询条目的编号或名称...">
                                <span class="input-group-btn">
                                    <button type="submit" class="btn search-button">搜索一下</button>
                                </span>
                            </div>
                            <div class="form-group">
                                <div class="search-btn-boding">
                                    <div class="radio col-sm-11 col-sm-offset-1">
                                        <label class="radio-inline" >
                                            <input name="search_type" type="radio" value="" checked>学生
                                        </label>
                                        <label class="radio-inline">
                                            <input name="search_type" type="radio" value="">教师
                                        </label>
                                        <label class="radio-inline" >
                                            <input name="search_type" type="radio" value="" >管理员
                                        </label>
                                        <label class="radio-inline">
                                            <input name="search_type" type="radio" value="">专业
                                        </label>
                                        <label class="radio-inline" >
                                            <input name="search_type" type="radio" value="" >课程
                                        </label>
                                        <label class="radio-inline">
                                            <input name="search_type" type="radio" value="">班级课程
                                        </label>
                                        <label class="radio-inline">
                                            <input name="search_type" type="radio" value="">教学点
                                        </label>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-9 col-sm-offset-1 error-message-boding">
                <p class="error-message">没有查询到相关信息</p>
            </div>
            <div class="col-sm-8 col-sm-offset-2 manage-grade-boding">
                <div class="col-sm-4 col-sm-offset-1">
                    <a href="#"><button class="btn manage-grade-btn">陈紫铜</button></a>
                </div>
                <div class="col-sm-4 col-sm-offset-1">
                    <a href="#"><button class="btn manage-grade-btn">14计算机科学</button></a>
                </div>
                <div class="col-sm-4 col-sm-offset-1">
                    <a href="//www.baidu.com"><button class="btn manage-grade-btn">15计算机科学与技术(医学应用)</button></a>
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