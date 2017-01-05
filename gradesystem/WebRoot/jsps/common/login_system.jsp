<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<title>登录界面</title>
<base target="_parent"/>



<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginCSS.css">
<script type="text/javascript">
	//图片刷新函数
	function _change(){
		var imgEle=document.getElementById("vCode");
		imgEle.src="${pageContext.request.contextPath}/VerificationServlet?"+new Date().getTime();
	}
	
	//若登录页面不是主窗口变为主窗口
	if(window!=top){
		top.location.href=location.href;
	}
</script>
	</script>
</head>

<body>
<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h2>继续教育学院成绩管理系统</h2>
			</div>
			<div class="login-content ">
			<div class="form">
			<form action="${pageContext.servletContext.contextPath}/BaseLoginServlet" method="post">
				<input type="hidden" name="method" value="login"/>
            	<!-- 以下描述的是用户输入框 -->
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" id="username" name="userId" class="form-control" value="用户名" onclick="if(this.value==defaultValue) this.value=''" onblur="if(!value) this.value=defaultValue;">
						</div>
					</div>
				</div>
                
                <!-- 以下是密码输入框 -->
				<div class="form-group ">
					<div class="col-xs-12">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input type="text" id="password" name="password" class="form-control" value="密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password';}" onblur="if(!value) {this.value=defaultValue; this.type='text';}">
						</div>
					</div>
				</div>
 
 				<!-- 以下是验证码输入框 -->
                <div class="form-group">
					<div class="col-xs-6  ">
						<div class="input-group" style="position:relative;">
							<input type="text" id="pin" name="verification" class="form-control" value="请输入验证码" onfocus="this.value=''" onblur="if(!value) this.value=defaultValue;"/>
                           <img id="vCode" src="${pageContext.request.contextPath}/VerificationServlet" width="115px" height="35px" class="img" onclick="javascript:_change()";>
                        <span class="sp">&nbsp;单击图片刷新</span> 
						</div>
					</div>
				</div>
                
                
                <!-- 以下是登陆按钮 -->
				
                
                <!-- 以下是选择学生、教师、管理员和忘记密码 -->
					<div class="col-xs-6 link" style="width:100%; clear:both;">
                        <div class="text-center remove-margin"">
                            <label class="radio-inline">
                                <input type="radio" name="loginType" id="inlineRadio1" value="1" checked="checked"> 学生
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="loginType" id="inlineRadio2" value="2"> 教师
                            </label>
                            <label class="radio-inline">
                                 <input type="radio" name="loginType" id="inlineRadio3" value="3"> 管理员
                            </label>
                            
                           <div class="form-group form-actions">
								<div class="col-xs-4 col-xs-offset-4" style="padding:15px;">
									<button type="submit" class="btn-color btn btn-sm btn-info">登录</button>
								</div>
							</div>
                            
                            <div style="float:right;  padding-top:20px;">
                            <p ><small>忘记密码?</small> <a href="forget_password_01.html" ><small>找回</small></a></p>
                            </div>
                        </div>
                     </div>
			</form>
			</div>
		</div>
	</div>
</div>

<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</body>

</html>