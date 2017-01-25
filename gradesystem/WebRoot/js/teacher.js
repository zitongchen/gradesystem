
// 班级成绩中的点评成绩表单验证（未完）
function text_formula(){
	var ps=document.getElementById("ps").value;
    var sy=document.getElementById("sy").value;
    var qm=document.getElementById("qm").value;
	// 输入框不能为空
	if(ps===" "||sy===" "||qm===""){
		alert("成绩占比不能为空，请输入成绩占比（%）！")
		return false;
	}
    var num1=Number(ps);
    var num2=Number(sy);
    var num3=Number(qm);
    if(num1<1||num2<1||num3<1){
    	alert("成绩占比不应小于1%！")
		return false;
	}
    var sum=num1+num2+num3;
    if (sum>100) {
    	alert("现输入的成绩总和为"+sum+"%"+"，成绩占比总和应为100%!");
       return false;
     }
    if (sum<100) {
    	alert("现输入的成绩总和为"+sum+"%"+"，成绩占比总和应为100%!");
       	return false;
    }
}

//利用ajax提交成绩！
$(function(){
	$("#loadGrade").click(function(){
		
		$.ajax({
			type:"post",
			url:root+"ExpertServlet",
			data:"method=changeGradeState&bh="+bh+"&kcId="+kcId,
			success:function(data){
				alert(data);
				$("#submit-btn").css("display","none");
			},
			error:function(){
				alert("提交成绩失败！");
			}
		})
	})
})

//利用ajax保存补考成绩
$(function(){
	$("#submit-fail-btn").click(function(){
		var user_id=document.getElementsByName('userId');
    	var bk=document.getElementsByName('bkscore');
    	var user_acount="";
    	var bkscore="";
    	for(i=0;i<user_id.length;i++){
    		user_acount+=user_id[i].value+" ";
    		bkscore+=bk[i].value+" ";
    	}
		$.ajax({
			type:"post",
			url:root+"StudentGradeServlet",
			data:"method=saveBkScore&bh="+bh+"&kcId="+kcId+"&user_acount="+user_acount+"&bkscore="+bkscore,
			success:function(data){
				alert(data);
			},
			error:function(){
				alert("保存补考成绩失败！");
			}
		})
	})
})