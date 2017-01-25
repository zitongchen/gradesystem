/**
 * Created by Administrator on 2017/1/12.
 */
$(function () {
		//页面加载的时候显示课程
		$(function () {
			if(isFirst){
				$.ajax({
					type:"post",
					url:root+"AdminServletOne",
					data:"method=findObjcenter",
					success:function (data) {
                    		var kc=JSON.parse(data);
                    		for(var index in kc){
                    			$(".show-course").before("<a><button class='message-btn'>"+"所属专业代码："+kc[index].zydm+" 课程："+kc[index].title+"</button><a/>");
                    		}
                    		
					},
					error:function(){
						alert("获取数据出错！");
					}
				})
			}
		});
		
		//添加专业是用到
    	var isFirst=true;
    	$(".add-btn").click(function () {
    		if(isFirst){
	            $.ajax({
	                type:"post",
	                url:root+"AdminServletOne",
	                data:"method=findMaijor",
	                success:function (data) {
	                    	var zy=JSON.parse(data);
	                    	for(var index in zy){
	                    		$(".zydm-select").append("<option value="+zy[index].zydm+">"+zy[index].zydm+" "+zy[index].zymc+"</option>");
	                    	}
	                        isFirst=false;
	                },
	                error:function(){
	                	alert("获取数据出错！");
	                }
	            })
    		}
        });
    	$(".add-btn").click(function () {
            var display=$(".add-course").css("display");
            if(display=="block"){
                $(".add-course").css("display","none");
            }else{
                $(".add-course").css("display","block");
            }
        });
})
