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

//个人信息修改页面
$(function() {
//获取class为caname的元素
    $(".change-txt").click(function() {
        var td = $(this);
        var txt = td.text();
        var input = $("<input class='form-control' type='text'value='" + txt + "'/>");
        td.html(input);
        input.click(function() { return false; });
//获取焦点
        input.trigger("focus");
//文本框失去焦点后提交内容，重新变为文本
        input.blur(function() {
            var newtxt = $(this).val();
//判断文本有没有修改
            if (newtxt != txt) {
                var name=td.attr("name");
                var id=$("#id").text();
                $.ajax({
                    type:"post",
                    url:"root#servlet",
                    data:"method=updateSystemMessage&type=student&id="+id+"&tex="+newtxt+"&date="+new Date(),
                    success:function () {
                        td.html(newtxt);
                        alert("请求成功！");
                    },
                    error:function () {
                        td.html(txt);
                        alert('修改信息有错！');
                    }
                });
            }
            else
            {
                td.html(newtxt);
            }
        });
    });

    //图片上传
    $(".photo").click(function() {
        //弹出窗口
        var box= $(".container");
        var show=box.css("display")
        if(show=="none"){
            $(".beijin").css("display","block");
            box.css("display","block");
        }
        var options =
            {
                thumbBox: '.thumbBox',
                spinner: '.spinner',
                imgSrc: root+'resource/images/ni.jpg'
            }
        var cropper = $('.imageBox').cropbox(options);
        $('#upload-file').on('change', function(){
            var reader = new FileReader();
            reader.onload = function(e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        });
        var first=true;
        $('#btnCrop').on('click', function(){
            var img = cropper.getDataURL();
            var type=$(".picture").attr("name");
            var userid=$("#id").text();
            //上传截取后的图片，数据为base64
            if(first){
            	$.ajax({
                    type:"post",
                    url:root+"UploadPhoto",
                    data:"method=uploadPhoto&imgData="+img+"&type="+type+"&userId="+userid,
                    success:function (data) {
                    	alert(data);
                        //上传成功
                        var show=box.css("display");
                        if(show=="block"){
                            $(".beijin").css("display","none");
                            box.css("display","none");
                        }
                        $('.picture').attr("src",img);
                    },
                    error:function () {
                    	alert("上传失败！")
                    }
                })
                first=false;
            }
            
        });
        //取消弹出的窗口
        $('#btnCancel').click(function () {
            var show=box.css("display");
            if(show=="block"){
                $(".beijin").css("display","none");
                box.css("display","none");
            }
        });
    });
});