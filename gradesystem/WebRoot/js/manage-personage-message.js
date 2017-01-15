/**
 * Created by Administrator on 2017/1/6.
 */
function doupdate()
{
    var op = document.getElementById('oldpassword').value;
    var p1=document.getElementById('password1').value;
    var p2=document.getElementById('password2').value;

    if(op == "" || p1 =="" ) {
        alert("请输入完整信息!");
        return false;
    }

    if (op == p1){
        alert("您输入的新密码和原密码一致，请重新输入!");
        document.getElementById('password1').value = "";
        document.getElementById('password2').value = "";
        document.getElementById('password1').focus();
        return false;
    }

    if(p1!=p2){
        alert("两次密码不一样，请重新输入!");
        return false;
    }

    if (p1.length < 6){
        alert("您输入的密码过短，至少6位字符或数字!");
        document.getElementById('password1').value = "";
        document.getElementById('password2').value = "";
        document.getElementById('password1').focus();
        return false;
    }

    var pp1= (p1.search(/[A-Za-z]/)!=-1) ? 1 : 0;   // 存在 为真 取1
    var pp2= (p1.search(/[0-9]/)!=-1) ? 1 : 0;

    if(pp1 == 0 || pp2 == 0 ){
        alert("密码必须包含数字、字母!");
        return false;
    }

    try {
        if(!checkStringLen(document.getElementById('oldpassword'),'旧密码',20)){return false; }
        if(!checkStringLen(document.getElementById('password1'),'新密码',20)){ return false; }
        if(!checkStringLen(document.getElementById('password2'),'确认密码',20)){return false;}
    }
    catch(e) {  window.alert(e.message); }
    return true;
}

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
        alert(msg+"长度过长，请重新输入");
        obj.focus();
        return false;
    }
    else
    {
        return true;
    }
}
