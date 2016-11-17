
// 班级成绩中的点评成绩表单验证（未完）
function text_formula(){
	var text_formula_1=document.getElementById('text_formula_1').value;
	var text_formula_2=document.getElementById('text_formula_1').value;
	var text_formula_3=document.getElementById('text_formula_1').value;
	var text_formula_4=document.getElementById('text_formula_1').value;

	// 输入框不能为空
	if(text_formula_1 == "" || text_formula_2 =="" || text_formula_3 =="" || text_formula_4 =="" ) {
        alert("四个框都不能为空!");
        return false;
    }

    // 百分比必须等于100%
    // var tf1=Number(text_formula_1);
    // var tf2=Number(text_formula_2);
    // var tf3=Number(text_formula_3);
    // var tf4=Number(text_formula_4);
    // var sum=tf1+tf2+tf3+tf4;
    // if (sum>100) {
    // 	alert("成绩百分比已达"+sum+"%"+"，请不要超过100%!");
    //     return false;
    // }
    // if (sum<100) {
    // 	alert("成绩百分比才"+sum+"%"+"，不足100%");
    //     return false;
    // }
}