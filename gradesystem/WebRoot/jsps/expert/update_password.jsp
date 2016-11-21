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
<title>个人中心</title>
<base target="body"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/css/teacherCSS.css" rel="stylesheet" style="text/css">
    
</head>
<body>

<!-- 以下是mainbody部分 -->
<!--<div class="mainbody" >-->
<div>
 <form  action="${pageContext.request.contextPath}/ExpertServlet" name="xgmm" id="form_xgmm" method="post"   >
          <input type="hidden" name="method" value="updateExpertPassword">
          <h4 class="text-center">修改密码</h4>
          <table id="t3" align="center">
              <p align="center" colspan="2" ><font id="error_message" color="red">${requestScope.passwordError}</font></p>
            <tr>
              <td> 登陆账号： </td>
             <td><input type="text" name="userId" readonly="readonly" style="border: 0px;background: 0000"  value="${sessionScope.session_expert.expacount}"  size=20 maxlength="20">
                </input></td>
            </tr>
              
            </tr>
            <tr>
              <td> 旧密码： </td>
              <td><input type="password" name="password" maxlength="20" id="password" value="" size=20>
                </input>
                <font color="red">*</font></td>
            </tr>
            <tr>
              <td> 新密码： </td>
              <td><input type="password" name="newPassword" maxlength="20" id="newPassword" value="" onchange="clear_error()" size=20 >
                </input>
                <font color="red">*</font></td>
            </tr>
            <tr>
              <td> 确认新密码： </td>
              <td><input type="password" name="password2" maxlength="20" id="password2" value=""  onchange="javascript:doupdate()" size=20>
                </input>
                <font color="red"> *</font></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" class="button info" value="保存"  id="button1"  name="button1">
                </input>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" class="button info" value="重 置" id="button2"   name=button2>
                </input></td>
            </tr>
            <tr>
              <td align="center" colspan="2"><font color="red">密码至少6位且必须包含数字、字母</font></td>
            </tr>
          </table>
        </form>
      </div>
	<script>
		
		function clear_error(){
			 document.getElementById("error_message").innerHTML=(" ");
		};
		function doupdate()
		{
		  var op = document.getElementById('password').value;
		  var p1=document.getElementById('newPassword').value;
		  var p2=document.getElementById('password2').value;
		  
		 
		  
		  if(op == " " || p1 ==" " ) {
			  document.getElementById("error_message").innerHTML=("请输入完整信息!");
		                             return false;
		                            }
		
		  if (op == p1){
			  document.getElementById("error_message").innerHTML=("您输入的新密码和原密码一致，请重新输入!");
		                  document.getElementById('newPassword').value = "";
		                  document.getElementById('password2').value = "";
		                  document.getElementById('newPassword').focus();
		                  return false;
		                }
		
		   if(p1!=p2){
			   document.getElementById("error_message").innerHTML=("两次密码不一样，请重新输入!");
		                document.getElementById('newPassword').value = "";
		                document.getElementById('password2').value = "";
		                document.getElementById('newPassword').focus();
		                return false;
		              }
		
		  
		
		   if(p1.length < 6){
			   document.getElementById("error_message").innerHTML=("您输入的密码过短，至少6位字符或数字!");
                document.getElementById('password1').value = "";
                document.getElementById('password2').value = "";
                document.getElementById('password1').focus();
                return false;
              }
		
		     var pp1= (p1.search(/[A-Za-z]/)!=-1) ? 1 : 0;   // 存在 为真 取1
		     var pp2= (p1.search(/[0-9]/)!=-1) ? 1 : 0;
		
		     if(pp1 == 0 || pp2 == 0 ){
		    	 document.getElementById("error_message").innerHTML=("密码必须包含数字、字母!");
		                                  return false;
		                                }
		
		     try {
		            if(!checkStringLen(document.getElementById('password'),'旧密码',20)){return false; }
		            if(!checkStringLen(document.getElementById('newPassword'),'新密码',20)){ return false; }
		            if(!checkStringLen(document.getElementById('password2'),'确认密码',20)){return false;}
		         }
		      catch(e) {  window.alert(e.message); }
		      return true;
		};
		
		// 计算字符串长度(英文占1个字符，中文汉字占2个字符)
		function checkStringLen(obj,msg,len)
		{
		  var str=obj.value;
		  var len1 =0;
		  for (var i=0; i<str.length; i++) {   
		     if (str.charCodeAt(i)>127 || str.charCodeAt(i)==94) {   
		   len1 += 2;   }
		  else { 
		      len1 ++; 
		         }   
		   }
		
		  if(len1>len)
		           {
			  document.getElementById("error_message").innerHTML=(msg+"长度过长，请重新输入");
		             obj.focus();   
		             return false;      
		            }
		  else
		    {
		      return true;
		    }
		};
</script> 
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>