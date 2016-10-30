<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PageBean.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 	<h3 align="center">学生成绩表</h3>
  	<table border="1" width="70%" align="center">
  		<tr>
  			<th>学号</th>
  			<th>科目编号</th>
  			<th>科目名称</th>
  			<th>分数</th>
  			<th>老师</th>
  		</tr>
  		<c:forEach items="${pb.beanList}" var="grade">
  			<tr>
  				<td>${grade.user_acount}</td>
  				<td>${grade.docourse.visit_count}</td>
  				<td>${grade.docourse.title}</td>
  				<td>${grade.psgrade}</td>
  				<td>${grade.expert.name}</td>
  			</tr>
  		</c:forEach>
  	</table>
    <center>
    	第${pb.pc}页/共${pb.tp}页
    	<a href="${pb.url}&pc=1">首页</a>
    	<c:if test="${pb.pc>1}">
    		<a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
    	</c:if>
    	
    	<%--计算begin,end --%>
    	<c:choose>
    	<%--如果总页数不足10页，那么把所有页数显示出来 --%>
    		<c:when test="${pb.tp<=10 }">
    			<c:set var="begin" value="1"></c:set>
    			<c:set var="end" value="${pb.tp }"></c:set>
    		</c:when>
    		<c:otherwise>
    		<%--当前页数>10时，通过公式计算出begin，和end --%>
    			<c:set var="begin" value="${pb.pc-5 }"></c:set>
    			<c:set var="end" value="${pb.pc+4 }"></c:set>
    			<%--头溢出 --%>
    			<c:if test="${begin<1}">
    				<c:set var="begin" value="1"></c:set>
    				<c:set var="end" value="10"></c:set>
    			</c:if>
    			<%--尾溢出 --%>
    			<c:if test="${end>pb.tp}">
    				<c:set var="begin" value="${pb.tp-9}"></c:set>
    				<c:set var="end" value="${pb.tp}"></c:set>
    			</c:if>
    		</c:otherwise>
    	</c:choose>
    	<%--循环遍历页码列表 --%>
    	<c:forEach var="i" begin="${begin}" end="${end }">
    		<c:choose>
    			<c:when test="${i eq pb.pc }">
    				[${i}]
    			</c:when>
    			<c:otherwise>
    				<a href="${pb.url}&pc=${i}">[${i}]</a>
    			</c:otherwise>
    		</c:choose>
    	</c:forEach>
    	
    	<c:if test="${pb.pc<pb.tp }">
    		<a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
    	</c:if>
    	<a href="${pb.url}&pc=${pb.tp}">尾页</a>
    </center>
    
  </body>
</html>
