/**
 * Created by Administrator on 2017/1/12.
 */
$(function () {
    	var isFirst=true;
    	$(function () {
    		if(isFirst){
	            $.ajax({
	                type:"post",
	                url:root+"AdminServlet",
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
        })
})
