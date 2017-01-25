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
    <title>审核成绩</title>
    <base target="body"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet" rel="stylesheet">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->
<div class="mainbody">
    <div>
        <form target="body" onsubmit="return text_formula()" name="" id="" method="post" action="${pageContext.request.contextPath}/AdminServlet">
            <input type="hidden"  name="method" value="auditGrades"/>
            <input type="hidden" name="items" value="${requestScope.classGradeList[0].bh}+${requestScope.classGradeList[0].visit_count}"/>
            <input type="hidden" name="shenhe" value="${session_admin.admin_acount}"/>
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>课程基本信息：</option>
                <tr>
                    <td>教师编号：</td>
                    <td><span>${requestScope.classGradeList[0].expert.expacount}</span></td>
                    <td>课程名称：</td>
                    <td><span>${requestScope.classGradeList[0].title}</span></td>
                </tr>
                <tr>
                    <td>班级：</td>
                    <td><span>${requestScope.classGradeList[0].bh}</span></td>
                    <td>学期：</td>
                    <td><span>${requestScope.classGradeList[0].termth}</span></td>
                </tr>
                <tr>
                	<td>操作员：</td>
                    <td><span>${requestScope.classGradeList[0].oper}</span></td>
                    <td></td>
                    <td></td>
                </tr>
                
            </table>
            <!-- 第三个表格 -->
            <table class="table table-bordered table-condensed">
                <option>成绩输入：</option>
                <tbody>
                <c:if test="${not empty requestScope.classGradeList }">
                    <tr>
                        <td>学号</td>
                        <td>姓名</td>
                        <td>平时成绩</td>
                        <td>实验成绩</td>
                        <td>总评成绩</td>
                        <td>补考成绩</td>
                       
                    </tr>
                    <c:forEach items="${requestScope.classGradeList}" var="studentList" varStatus="status">
                        <tr>
                            <td>${studentList.user_acount}</td>
                            <td>${studentList.nickname}</td>
                            <td>${studentList.psscore}</td>
                            <td>${studentList.syscore}</td>
                            <td>${studentList.totalscores}</td>
                            <td>${studentList.bkscore}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="form-group">
                <div class="col-sm-1 col-sm-offset-5">
                    	<button type="submit" id="submit-btn" class="btn default-btn submit-btn">审核</button>
                </div>
            </div>
        </form>
    </div>
</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 </body>
</html>