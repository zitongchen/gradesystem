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
<title>班级成绩录入</title>
<base target="body"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/css/teacherCSS2.css" rel="stylesheet" style="text/css">
</head>
<body>

<!-- 以下是内容主体部分mainbody -->  
<div class="mainbody">
    <div>
    <form target="body" name="" id="" metdod="post" action="${pageContext.request.contextPath}/ExpertServlet">
			<input type="hidden"  name="method" value="saveClassStudentGrade"/>
			<input type="hidden" name="classId" value="${requestScope.studentgrades[0].bh}"/>
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>课程基本信息：</option>
                <tr>
                    <td>教师姓名：</td>
                    <td><span>${sessionScope.session_expert.name}</span></td>
                    <td>课程名称：</td>
                    <td><span>${requestScope.studentgrades[0].title}</span></td>
                </tr>
                <tr>
                    <td>班级：</td>
                    <td><span>${requestScope.studentgrades[0].bh}</span></td>
                    <td>学年学期：</td>
                    <td><span>2016-2017-1(静态数据)</span></td>
                </tr>
            </table>
            
		
            <!-- 第二个表格 -->
            <table class="table table-bordered table-condensed"> 
                <option>成绩输入前期处理：</option>         
                <tr>
                    <!-- bootstrap在ie8的宽度根据内容自适应，十分不好看，增加空的td标签可以解决这个问题，原理不明 -->
                    <td style="width: 100%">
                    	<label>请输入成绩占比：</label>
                        <label>平时(%):</label>
                        <input type="text" name="paecetime" id="text_formula_1" size="4"><label>%</label>
                         <!-- 在需求中不存在期中，实验的成绩字段，所以不显示 
                        <label>期中(%):</label>
                        <input type="text" name="" id="text_formula_2" size="4">
                        <label>实验(%):</label>
                        <input type="text" name="" id="text_formula_3" size="4">
                        -->
                        <label>期末(%):</label>
                        <input type="text" name="terminal" id="text_formula_4" size="4"><label>%</label>
                        <!-- 
                        <input type="submit" name="" value="确定" class="input_bgc" style="width: 50px;margin:0 10px;" onclick="text_formula()">
                        <font color="red">折算总评成绩之前请清空总评成绩</font>
                        <input type="reset" name="" value="清空总评成绩" class="input_bgc">
                         -->
                    </td>
                </tr>
            </table>


        <!-- 第三个表格 -->
        <table class="table table-bordered table-condensed">
        <option>成绩输入：</option>
        <tbody>
             <c:if test="${not empty requestScope.studentgrades }">
            <tr>
                <!--  序号-->
                <td>学号</td>
                <td>姓名</td>
                <td>平时成绩</td>
                <td>期末成绩</td>
                <td>总评成绩</td>
            </tr>
			    <c:forEach items="${requestScope.studentgrades}" var="studentList" varStatus="status">
			    	<tr>
			    		<!--  td>${status.count}</td-->
			    		<td><input type="text" name="userId" value="${studentList.user_acount}" size="12" readonly="readonly" style="border: 0px"></td>
			    		<c:set var="index" value="${status.index}"></c:set>
		                <td>${studentList.nickname}</td>
			    		<td><input type="text" name="psgrade" value="${studentList.psscore}" size="8"></td>
			    		<td><input type="text" name="ksgrade" value="${studentList.ksscore}" size="8"></td>
			    		<td>${studentList.totalscores}</td>
    				</tr>
			    </c:forEach>
    		</c:if>
            
        </tbody>
        </table>
        <table class="table table-bordered table-condensed">
        	<tbody style="background-color:#F9F9F9 ">
            <tr>
                <td style="width: 100%">
                    <input type="submit" value="保存" class="input_bgc">
        			<input type="submit" value="提交" class="input_bgc" disabled="disabled">
        			
                </td>
            </tr>
            </tbody>
        </table>
        </form>
        
	<form action="${pageContext.request.contextPath}/StudentGradeSheetServlet">
	<input type="hidden" name="classId" value="${requestScope.classes.bh}"/>
	<input type="hidden" name="expacount" value="${sessionScope.session_expert.expacount}"/>
        <table class="table table-bordered table-condensed">
            <tr>
                <td style="width: 100%">
                    <input type="submit" name="" value="成绩输出打印" class="input_bgc">
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</body>
</html>