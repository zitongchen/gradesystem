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
<title>计算机科学与技术15(2)</title>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->  
<div class="mainbody">
    <p>当前位置：教师-->教师成绩录入-->计算机科学与技术15(2)</p>
    <div>
    <form  name="" id="" method="post" action="">
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>教师基本信息：</option>
                <tr>
                    <td>教师姓名：</td>
                    <td><span>XXX</span></td>
                    <td>课程名称：</td>
                    <td><span>数据结构与算法实训</span></td>
                </tr>
                <tr>
                    <td>班级构成：</td>
                    <td><span>计算机科学与技术15（2）</span></td>
                    <td>学年学期：</td>
                    <td><span>2016-1017-1</span></td>
                </tr>
                <tr>
                    <td>课程性质：</td>
                    <td><span>必修课</span></td>
                    <td>考核方式：</td>
                    <td><span>其他</span></td>
                </tr>
            </table>
            

            <!-- 第二个表格 -->
            <table class="table table-bordered table-condensed"> 
                <option>成绩输入前期处理：</option>         
                <tr>
                    <td style="width: 80%">
                        <label>平时(%):</label>
                        <input type="text" name="" id="" size="4">    
                        <label>期中(%):</label>
                        <input type="text" name="" id="" size="4">
                        <label>实验(%):</label>
                        <input type="text" name="" id="" size="4">
                        <label>期末(%):</label>
                        <input type="text" name="" id="" size="4">
                        <input type="submit" name="" value="确定" class="input_bgc" style="width: 50px;margin:0 10px;" onclick="text_formula()">
                        <font color="red">折算总评成绩之前请清空总评成绩</font>
                        <input type="reset" name="" value="清空总评成绩" class="input_bgc">
                    </td>
                </tr>
            </table>


        <!-- 第三个表格 -->
        <table class="table table-bordered table-condensed">
        <option>成绩输入：</option>
        <tbody>
            <tr>
                <td>序号</td>
                <td>班级名称</td>
                <td>学号</td>
                <td>姓名</td>
                <td>平时成绩</td>
                <td>期中成绩</td>
                <td>实验成绩</td>
                <td>期末成绩</td>
                <td>总评成绩</td>
            </tr>
            <tr>
                <td>1</td>
                <td style="width: 20%">计算机科学与技术15(2)</td>
                <td>1500000001</td>
                <td>XXX</td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
            </tr>
            <tr>
                <td>2</td>
                <td style="width: 20%">计算机科学与技术15(2)</td>
                <td>1500000002</td>
                <td>XXX</td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
            </tr>
            <tr>
                <td>3</td>
                <td style="width: 20%">计算机科学与技术15(2)</td>
                <td>1500000003</td>
                <td>XXX</td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
            </tr>
             <tr>
                <td>4</td>
                <td style="width: 20%">计算机科学与技术15(2)</td>
                <td>1500000003</td>
                <td>XXX</td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
            </tr>
             <tr>
                <td>5</td>
                <td style="width: 20%">计算机科学与技术15(2)</td>
                <td>1500000003</td>
                <td>XXX</td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
                <td><input type="text" name="" id="" size="8"></td>
            </tr>
        </tbody>
        </table>

         <!-- 第四个表格 -->
        <table class="table table-bordered table-condensed">
            <tr>
                <td style="width: 100%">
                    <input type="submit" name="" value="成绩校对稿打印" class="input_bgc">
                    <input type="submit" name="" value="成绩输出打印" class="input_bgc">
                    <input type="submit" name="" value="学生照片查看" class="input_bgc">
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-parent.js"></script><!-- 本地的bootstrap资源链接 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>