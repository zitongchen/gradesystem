<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GsonDemo.jsp' starting page</title>
  
    <script src="js/jquery-3.1.1.min.js"></script>
    <script  type="text/javascript" language="javascript">
	function saveData(){
		var student=[{"name":"tony","age":21},{"name":"chenzitong","age":22}];
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/GsonServlet",
			data:{"datalist":JSON.stringify(student)},
			dataType:"json",
			success:function(data){
			alert("success");},
			error:function(){
				alert("insert error");
			}
		});
	}
	</script>
  </head>
    <body>
    <button id="demo" onclick="saveData()">Click</button><br>
  </body>
</html>
