<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GsonDemo.jsp' starting page</title>
  
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script  type="text/javascript" language="javascript">
    $(function(){
    	var student=[{"name":"陈子通","age":21},{"name":"姚明","age":22}];
    	$("#demo").click(function(){
    		$.ajax({
    			type:"post",
    			//dataType:"json",
    			url:"<%=basePath%>JsonServlet",
    			data:{"datalist":JSON.stringify(student)},
    			success:function(data){
    				alert("success");
    				var studentList=JSON.parse(data);
    				alert(studentList[0].name+studentList[0].age);
    			},
    			error:function(){
    				alert("error");
    			}
    		})
    	})
    })
    $(function(){
    	var student=[{"name":"陈子通","age":21},{"name":"姚明","age":22}];
    	$("#zydm").mousedown(function(){
    		$.ajax({
    			type:"post",
    			//dataType:"json",
    			url:"<%=basePath%>JsonServlet",
    			data:{"datalist":JSON.stringify(student)},
    			success:function(data){
    				var studentList=JSON.parse(data);
    				$(".studentList").append("<li>"+studentList[0].name+"</li>")
    			},
    			error:function(){
    				alert("error");
    			}
    		})
    	})
    })
	function test(){
		var student=[{"name":"陈子通","age":21},{"name":"姚明","age":22}];
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/JsonServlet",
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
    <button id="demo">Click</button><br>
    <form>
    	<lable>专业：</lable><input type="text" id="zydm" value="陈姿彤">
    </form>
    <div>
    	<ol class="studentList">
    	</ol>
    </div>
  </body>
</html>
