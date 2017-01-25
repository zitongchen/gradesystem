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
    <title>成绩录入</title>
    <base target="body"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet" rel="stylesheet">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->
<div class="mainbody">
    <div>
        <form target="body" onsubmit="return text_formula()"  method="post" action="${pageContext.request.contextPath}/ExpertServlet">
            <input type="hidden"  name="method" value="saveClassStudentGrade"/>
            <input type="hidden" name="classId" value="${requestScope.studentgrades[0].bh}"/>
            <input type="hidden" name="kc" value="${requestScope.studentgrades[0].visit_count }"/>
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>课程基本信息：</option>
                <tr>
                    <td>教师姓名：</td>
                    <td><span>${sessionScope.session_expert.name}</span></td>
                    <td>课程名称：</td>
                    <td><span>${requestScope.studentgrades[0].title }</span></td>
                </tr>
                <tr>
                    <td>班级：</td>
                    <td><span>${requestScope.studentgrades[0].bh}</span></td>
                    <td>学年学期：</td>
                    <td><span>${requestScope.studentgrades[0].termth }</span></td>
                </tr>
            </table>
            <!-- 第二个表格 -->
            <table class="table table-bordered table-condensed">
                <option>成绩占比：</option>
                <tr>
                    <td style="width: 100%">
                        <label>请输入成绩占比：</label>
                        <label>平时(%):</label>
                        <input type="number" name="ps"  id="ps" size="4">
                        <label>实验(%):</label>
                        <input type="number" name="sy" id="sy" size="4">
                        <label>期末(%):</label>
                        <input type="number" name="qm" id="qm" size="4">
                    </td>
                </tr>
            </table>
            <!-- 第三个表格 -->
            <table class="table table-bordered table-condensed">
                <option>成绩输入：</option>
                <tbody>
                <c:if test="${not empty requestScope.studentgrades }">
                    <tr>
                        <td>学号</td>
                        <td>姓名</td>
                        <td>平时成绩</td>
                        <td>实验成绩</td>
                        <td>期末成绩</td>
                        <td>总评成绩</td>
                    </tr>
                    <c:forEach items="${requestScope.studentgrades}" var="studentList" varStatus="status">
                        <tr>
                            <td><input type="text" name="userId" value="${studentList.user_acount}" size="12" readonly="readonly" style="border: 0px"></td>
                            <c:set var="index" value="${status.index}"></c:set>
                            <td>${studentList.nickname}</td>
                            <td><input class="input-style" type="number" name="psscore" value="${studentList.psscore}" size="8"></td>
                            <td><input class="input-style" type="number" name="syscore" value="${studentList.syscore}" size="8"></td>
                            <td><input class="input-style" type="number" name="ksscore" value="${studentList.ksscore}" size="8"></td>
                            <td>${studentList.totalscores}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <div class="form-group">
                <div class="col-sm-1 col-sm-offset-4">
                	<c:if test="${requestScope.studentgrades[0].state=='0'}">
                    	<button type="submit" id="submit-btn" class="btn default-btn submit-btn">保存</button>
                    </c:if>
                </div>
            </div>
        </form>
        <c:if test="${ requestScope.studentgrades[0].state=='0'}">
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