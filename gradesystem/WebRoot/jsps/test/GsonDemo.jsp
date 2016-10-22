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
    <!--script src="js/jquery-3.1.1.min.js"></script>  -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <script  type="text/javascript" language="javascript">
	function postMessage(){
       var student ={"name":"CZT","age":22};
        $.ajax(
                {
                    url:"<%=basePath%>GsonServlet", //访问路径
                    type:"POST",    //访问方式
                   //传入服务端的数据
                    data:{name:'Tony'},
                    dataType:"json",
                    cache : false,
                    contentType:"application/json;charset=utf-8",
                    success : function(data){
                          alert("success");
                    },
                    error:function(e){
                    	alert("error")
                    }
                     
                }       
                );
    }
	</script>
  </head>
  
  <body>
    
    <button id="demo" onclick="postMessage()">Click</button><br>
    <form action="${pageContext.request.contextPath}/GsonServlet" method="post">
    	<input type="text" name="student" value="Tony"/><br>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
