<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link href="css/teacherCSS.css" rel="stylesheet" style="text/css">
</head>
<body>
<!-- 以下是头部header -->
<div class="header">
  <div class="logo">继续教育学院成绩管理系统</div>
  <div class="right_nav">
    <div class="text_right">
            <ul class="nav_return">
                <li> [<a href="${pageContext.request.contextPath}/ExpertServlet?method=exit" target="_top">退出</a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
    </div>
  </div>
<div class="under_header"> 
<span>${sessionScope.session_expert.name}</span>,您好！职工号：<span>${sessonScope.session_expert.expacount}</span>&nbsp;&nbsp;学院：<span>医药信息工程学院</span>&nbsp;&nbsp;职称：<span>讲师</span> </div>
</div>

<!-- 以下是菜单menu以及左侧的二级菜单 -->
<div id="tabs">
    <ul id="nav0">
        <li><a href="homepage.html">首页</a></li>
        <li class="on"><a href="${pageContext.request.contextPath}/ExpertServlet?method=findClassNameByExpert&expacount=${sessionScope.session_expert.expacount}">成绩录入</a></li>
        <li><a href="personal.html">个人中心</a></li>
    </ul>
    <div>  
         <p class="left_title">相关操作</p>
         <ul>
         	 <c:if test="${not empty requestScope.class_id}">
	    		<c:forEach items="${requestScope.class_id}" var="className">
	    			<li>
		    			<a href='<c:url value="/ExpertServlet?method=findClassStudentByClass&classId=${className}&expacount=${sessionScope.session_expert.expacount}"/>'>
		    				<c:out value="${className}"></c:out>
		    			</a>
	    			</li>
	    			
	    		</c:forEach>
	    	</c:if>
         </ul>
    </div>
</div>

<!-- 以下是内容主体部分mainbody -->  
<div class="mainbody">
    <p>当前位置：教师-->教师成绩录入</p>
    <div>
    
            <!-- 第一个表格 -->
            <table class="table table-bordered table-condensed">
                <option>教师基本信息：</option>
                <tr>
                    <td>教师姓名：</td>
                    <td><span>${sessionScope.session_expert.name}</span></td>
                    <td>课程名称：</td>
                    <td><span>${requestScope.studentgrades[0].docourse.title}</span></td>
                </tr>
                <tr>
                    <td>班级构成：</td>
                    <td><span>计算机科学与技术15（1）</span></td>
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
            
		<form  name="" id="" method="post" action="${pageContext.request.contextPath}/ExpertServlet">
			<input type="hidden"  name="method" value="saveClassStudentGrade"/>
            <!-- 第二个表格 -->
            <table class="table table-bordered table-condensed"> 
                <option>成绩输入前期处理：</option>         
                <tr>
                    <td style="width: 80%">
                        <label>平时(%):</label>
                        <input type="text" name="paecetime" id="" size="4">    
                        
                        <label>期末(%):</label>
                        <input type="text" name="terminal" id="" size="4">
                        <font color="red">折算总评成绩之前请清空总评成绩</font>
                        <input type="reset" name="" value="清空总评成绩" class="input_bgc">
                    </td>
                </tr>
            </table>

		 
        <!-- 第三个表格 -->
        <table class="table table-striped table-condensed">
        <option>成绩输入：</option>
        <tbody>
            <c:if test="${not empty requestScope.studentgrades }">
            	<tr>
                <th>序号</th>
                <th>班级名称</th>
                <th>学号</th>
                <th>姓名</th>
                <th>平时成绩</th>
                <th>期末成绩</th>
                <th>总评成绩</th>
            </tr>
			    <c:forEach items="${requestScope.studentgrades}" var="studentList" varStatus="status">
			    	<tr>
			    		<td>${status.count}</td>
		                <td style="width: 20%">计算机科学与技术15(1)</td>
			    		<td><input type="text" name="userId" value="${studentList.user_acount}" size="8"></td>
			    		<c:set var="index" value="${status.index}"></c:set>
		                <td>${studentNameList[index]}</td>
			    		<td><input type="text" name="psgrade" value="${studentList.psgrade}" size="8"></td>
			    		<td><input type="text" name="ksgrade" value="${studentList.ksgrade}" size="8"></td>
			    		<td><input type="text" name="grade" value="${studentList.grade}" size="8"></td>
    				</tr>
			    </c:forEach>
    		</c:if>
        </tbody>
        </table>
			<input type="submit"  value="保存"/>
    </form>
    <form action="${pageContext.request.contextPath}/ExpertServlet" method="post">
    	<input type="hidden" name="method" value="printStudentGrade"/>
    	<input type="submit" value="打印"/>
    </form>
</div>

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
