
$(function () {
        var isZydm=true;
    	$(function () {
    		if(isZydm){
	            $.ajax({
	                type:"post",
	                url:root+"AdminServletOne",
	                data:"method=findMaijor",
	                success:function (data) {
	                    	var zy=JSON.parse(data);
	                    	for(var index in zy){
	                    		$(".maijor-select").append("<option value="+zy[index].zydm+">"+zy[index].zydm+" "+zy[index].zymc+"</option>");
	                    	}
	                        isZydm=false;
	                },
	                error:function(){
	                	$(".maijor-select").append("<option >"+"获取数据出错！"+"</option>");
	                }
	            })
    		}
        })
      
    	$(".maijor-select").focusout(function(){
    		var zydm=$(".maijor-select").val();
    		$(".bj-select").empty();
    		$.ajax({
    			type:"post",
    			url:root+"AdminServletOne",
    			data:"method=searchGraduateClassByZydm&zydm="+zydm,
    			success:function(data){
    				$(".bj-select").empty();
    				var bj=JSON.parse(data);
    				if(bj===null){
    					$(".bj-select").append("<option >"+"不存在毕业班级"+"</option>");
    				}
    				for(var index in bj){
    					$(".bj-select").append("<option value="+bj[index]+">"+bj[index]+"</option>");
    				}
    			},
    			error:function(){
    				$(".bj-select").append("<option >"+"获取数据出错！"+"</option>");
    			}
    		})
    	})
})
