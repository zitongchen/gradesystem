/**
 * Created by Administrator on 2017/1/12.
 */
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
	                    		$("#zydm").append("<option value="+zy[index].zydm+">"+zy[index].zydm+" "+zy[index].zymc+"</option>");
	                    	}
	                        isZydm=false;
	                },
	                error:function(){
	                	$("#zydm").append("<option >"+"获取数据出错！"+"</option>");
	                }
	            })
    		}
        })
    	$("#zydm").focusout(function(){
    			var zydm=$("#zydm").val();
    			$.ajax({
    				type:"post",
    				url:root+"AdminServletOne",
    				data:"method=findObjByZydm&zydm="+zydm,
    				success:function(data){
    					$("#kc-select").empty();
    					var kc=JSON.parse(data);
    					if(kc===null){
        					$("#kc-select").append("<option>"+"不存在课程！"+"</option>");
        					return ;
        				}
    					for(var index in kc){
    						$("#kc-select").append("<option value="+kc[index].visit_count+">"+kc[index].visit_count+" "+kc[index].title+"</option>");
    					}
    					
    				},
    				error:function(){
    					$("#kc-select").append("<option >"+"获取数据出错！"+"</option>");
    				}
    			})
    	})
    	$(function(){
    		$.ajax({
    			type:"post",
    			url:root+"AdminServletOne",
    			data:"method=findExpertId",
    			success:function(data){
    				var js=JSON.parse(data);
    				if(js===null){
    					$("#js-select").append("<option value="+"不存在教师！"+"</option>");
    					return;
    				}
    				for(var index in js){
    					$("#js-select").append("<option value="+js[index].expacount+">"+js[index].expacount+" "+js[index].name+"</option>");
    				}
    			},
    			error:function(){
    				$("#js-select").append("<option >"+"获取数据出错！"+"</option>");
    			}
    		})
    	})
    	$("#kc-select").focusout(function(){
    		var zydm=$("#zydm").val();
    		$("#bj-select").empty();
    		$.ajax({
    			type:"post",
    			url:root+"AdminServletOne",
    			data:"method=findClassByZydm&zydm="+zydm,
    			success:function(data){
    				$("#bj-select").empty();
    				var bj=JSON.parse(data);
    				if(bj===null){
    					$("#bj-select").append("<option>"+"不存在班级！"+"</option>");
    					return ;
    				}
    				for(var index in bj){
    					$("#bj-select").append("<option value="+bj[index]+">"+bj[index]+"</option>");
    				}
    			},
    			error:function(){
    				$("#bj-select").append("<option >"+"获取数据出错！"+"</option>");
    			}
    		})
    	})
})
