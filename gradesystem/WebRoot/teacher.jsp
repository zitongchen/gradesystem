<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta charset="gb2313">
<title>教师注册</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teacher_register.css">
	
</head>
<body>

	<div class="register_top">
			<p>教师注册</p>
	</div>
	<div class="register_wrap">
		
		<form name="register_form" id="register" onsubmit="return doupdate();" method="post" action="${pageContext.request.contextPath}/ExpertRegisterServlet" enctype="multipart/form-data">
			<div class="ipt_box_before"></div>
			<div class="ipt_box">
				<label for="name" class="item" class="required">姓名</label>
				<input type="text" name="name" id="name"  class="txt" maxlength="24" autocomplete="off" required>
			</div>
			<div class="ipt_box">
				<label for="pwd" class="item" class="required">密码</label>
				<input type="password" id="password1"  name="passwordone" class="txt" maxlength="16" placeholder="输入大于6位的密码" required>
			</div>

			<div class="ipt_box">
				<label for="pwd_y" class="item" class="required">确认密码</label>
				<input type="password" id="password2" name="password" id="pwd_y" class="txt" maxlength="16" onfocusout="doupdate()" required>
			</div>

			<div class="ipt_box">
					<label class="item" for="num" class="required">手机号码</label>
					<div class="txt">
						<input type="text" name="telephone" id="telephone" class="txt" maxlength="11" required>
					</div>
			</div>
			<div class="ipt_box">
				<label for="email" class="item" class="required">email</label>
				<input type="email" name="email" class="txt" maxlength="24" autocomplete="off" required>
			</div>
			<div class="ipt_box">
				<label for="weixin" class="item" >微信</label>
				<input type="text" id="weixin" name="weixin" class="txt" maxlength="16" autocomplete="off" >
			</div>
			<div class="ipt_box">
				<label for="qq" class="item" >QQ</label>
				<input type="text" name="qq" class="txt" maxlength="16" autocomplete="off" >
			</div>
			<div class="ipt_box">
				<label  class="item" >专家类型编码</label>
				<input type="text" name="expcode" class="txt" maxlength="16" autocomplete="off" >
			</div>
			<div class="ipt_box">
				<label  class="item" >所属单位</label>
				<input type="text" name="departid" class="txt" maxlength="16" autocomplete="off" >
			</div>
			
			<div class="ipt_box">
				<label class="item">性别</label>
				<div class="txt">
					<label for="rdo1" class="px70">
						<input type="radio" name="sex" id="rdo1" value="男" checked>男
					</label>
					<label for="rdo2" class="px70">
						<input type="radio" name="sex" id="rdo2" value="女" >女
					</label>
				</div>
			</div>

			<div class="ipt_box">
				<label class="item" >学历</label>
				<div class="txt">
					<select name="education" size="1">
						<option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="硕士">硕士</option>
						<option value="博士">博士</option>
						<option value="博士后">博士后</option>
					</select>
				</div>
			</div>
			<div class="ipt_box">
				<label class="item">职称</label>
				<div class="txt">
					<select name="title" size="1">
						<option value="助教">助教</option>
						<option value="讲师">讲师</option>
						<option value="副教授">副教授</option>
						<option value="教授">教授</option>
					</select>
				</div>
			</div>
			<div class="ipt_box">
				<label class="item">城市</label>
				<div class="txt">
					<SELECT  name="sheng" id="to_cn" onchange="set_city(this, document.getElementById('city')); WYL();" class=login_text_input >

						<option value=0>请选择</option>

						<option value=北京>北京</option>

						<option value=上海>上海</option>

						<option value=天津>天津</option>

						<option value=重庆>重庆</option>

						<option value=河北省>河北省</option>

						<option value=山西省>山西省</option>

						<option value=辽宁省>辽宁省</option>

						<option value=吉林省>吉林省</option>

						<option value=黑龙江省>黑龙江省</option>

						<option value=江苏省>江苏省</option>

						<option value=浙江省>浙江省</option>

						<option value=安徽省>安徽省</option>

						<option value=福建省>福建省</option>

						<option value=江西省>江西省</option>

						<option value=山东省>山东省</option>

						<option value=河南省>河南省</option>

						<option value=湖北省>湖北省</option>

						<option value=湖南省>湖南省</option>

						<option value=广东省>广东省</option>

						<option value=海南省>海南省</option>

						<option value=四川省>四川省</option>

						<option value=贵州省>贵州省</option>

						<option value=云南省>云南省</option>

						<option value=陕西省>陕西省</option>

						<option value=甘肃省>甘肃省</option>

						<option value=青海省>青海省</option>

						<option value=内蒙古>内蒙古</option>

						<option value=广西>广西</option>

						<option value=西藏>西藏</option>

						<option value=宁夏>宁夏</option>

						<option value=新疆>新疆</option>

						<option value=香港>香港特别行政区</option>

						<option value=澳门>澳门特别行政区</option>

						<option value=台湾>台湾</option>
					</SELECT> -
					<select id="city" name="shi" class=login_text_input name="shi">
						<option value=0>请选择</option>
					</select>
				</div>
			</div>
			<div class="upload_photo">
				<label class="item"> 上传照片</label>
				<div class="photo">
					<div class="preview" id="preview">
						<img src='<%=request.getContextPath()%>/resource/images/he.png'><!--获得项目路径 -->
					</div>
					<input type="file" id="file" name="picture" onchange="previewImage(this)" required="required"/>     <!-- 改变时触发 -->
				</div>
			</div>
			<div class="ipt_box description_box">
					<label for="description" class="item">简介</label>
					<textarea name="description" class="txt description_txt"></textarea>
			</div>
			<div class="ipt_box" >
				<label for="code" class="item">验证码</label>
				<div class="txt">
					<input type="text" name="code" class="code" maxlength="8">
					<img id="test_code" src="${pageContext.request.contextPath}/VerificationServlet" onclick="code_change()" class="code_img">
				</div>
			</div>
			<div class="ipt_box register_box">
				<input type="submit"  class="submit" value="注册">
			</div>
		</form>
	</div>
	<div id ="foot">
		Copyright All Rights Reserved 成绩查询系统 版权所有®
	</div>
	<c:if test="${not empty requestScope.errorMessage}">
		<script>alert('${requestScope.errorMessage}')</script>
	</c:if>
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script rel="script" src="${pageContext.request.contextPath}/js/teacher_register.js"></script>
	<script type="text/javascript">
		function code_change(){
			var code=document.getElementById("test_code");
			code.src="${pageContext.request.contextPath}/VerificationServlet?"+new Date().getTime();
		}
	</script>
</body>
</html>