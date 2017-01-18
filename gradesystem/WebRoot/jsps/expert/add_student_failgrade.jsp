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
    <title>补考成绩录入</title>
    <base target="body"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet" rel="stylesheet">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->
<div class="mainbody">
    <div>
        <form target="body" onsubmit="return text_formula()" name="" id="" metdod="post" action="${pageContext.request.contextPath}/ExpertServlet">
            <input type="hidden"  name="method" value="saveClassStudentGrade"/>
            <input type="hidden" name="classId" value="${requestScope.studentList[0].bh}"/>
            <input type="hidden" name="state" value="${requestScope.studentList[0].state}"/>
            <input type="hidden" name="kc" value="${requestScope.studentList[0].visit_count}"/>
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>课程基本信息：</option>
                <tr>
                    <td>教师姓名：</td>
                    <td><span>${sessionScope.session_expert.name}</span></td>
                    <td>课程名称：</td>
                    <td><span>${requestScope.studentList[0].title}</span></td>
                </tr>
                <tr>
                    <td>班级：</td>
                    <td><span>${requestScope.studentList[0].bh}</span></td>
                    <td>学年学期：</td>
                    <td><span>2016-2017-1(静态数据)</span></td>
                </tr>
            </table>
            <!-- 第三个表格 -->
            <table class="table table-bordered table-condensed">
                <option>成绩输入：</option>
                <tbody>
                <c:if test="${not empty requestScope.studentList }">
                    <tr>
                        <td>学号</td>
                        <td>姓名</td>
                        <td>补考成绩</td>
                        <td>总评成绩</td>
                    </tr>
                    <c:forEach items="${requestScope.studentList}" var="studentList" varStatus="status">
                        <tr>
                            <td><input type="text" name="userId" value="${studentList.user_acount}" size="12" readonly="readonly" style="border: 0px"></td>
                            <c:set var="index" value="${status.index}"></c:set>
                            <td>${studentList.nickname}</td>
                            <td><input class="input-style" type="number" name="bkscore" value="${studentList.bkscore}" size="8"></td>
                            <td>${studentList.totalscores}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="form-group">
                <div class="col-sm-1 col-sm-offset-4">
                	<c:if test="${requestScope.studentList[0].state=='1'}">
                    	<button type="submit" id="submit-btn" class="btn default-btn submit-btn">保存</button>
                    </c:if>
                </div>
            </div>
        </form>
        <c:if test="${requestScope.studentList[0].state=='1' }">
	        <div class="col-sm-1 ">
	        	<button id="loadGrade" class="btn default-btn submit-btn">提交</button>
	        </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/StudentGradeSheetServlet">
            <input type="hidden" name="classId" value="${requestScope.studentgrades[0].bh}"/>
            <input type="hidden" name="expacount" value="${sessionScope.session_expert.expacount}"/>
            <div class="form-group ">
                <div class="col-sm-3">
                    <button type="submit" class="btn default-btn submit-btn last-btn">成绩输出打印</button>
                </div>
            </div>
        </form>
    </div>
</div>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script>
    	var root="<%=basePath%>";
    	var bh='${requestScope.studentgrades[0].bh}';
    	var kcId='${requestScope.studentgrades[0].visit_count}';
    </script>
    <script src="${pageContext.request.contextPath}/js/teacher.js"></script>
 </body>
</html>