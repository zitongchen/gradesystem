/**
 * Created by Administrator on 2017/1/21.
 */
$(function () {
	
    $.ajax({
        type:"post",
        url:root+"AdminServletOne",
        data:"method=searchXuexidi",
        success:function (data) {
        	var jxd=JSON.parse(data);
        	for(var index in jxd){
        		$(".jxd-select").append("<option value="+jxd[index].xxdd+">"+jxd[index].departid+"  "+jxd[index].xxdd+"</option>");
        	}
        },
        error:function () {
            alert("获取数据出错！");
        }
    })
})