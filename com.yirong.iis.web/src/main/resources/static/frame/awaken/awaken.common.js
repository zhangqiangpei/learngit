var perArr = "";
var commonVue = new Vue({});
var awakenCommonVue = new Vue({
 
	methods : {
	 
		initHeight : function(objId){
        	var allHeight = $(window).height();
			var otherHeigth = 0;
			$.each($("#" + objId).siblings(),function(i,n){
				otherHeigth = otherHeigth + $(n).height();
			});
			$("#" + objId).height(allHeight - otherHeigth - 35);
        },
        initMenu: function(){
        	if(perArr == ""){
        		var result = ak.msService("sys","sysPermissionApi/getByUser",{});
                if(null == result){
                	ak.error("系统错误,请联系管理员");
                }else  if(result.code == 0){//操作成功
                	perArr = result.data.perStr;
                }else{
                	//提示
                	ak.error(result.msg);
                }
        	}
        	
        	$.each($("button[name^='per_']"), function(index,obj){
        		$(obj).css("visibility","visible");
        		if(perArr.indexOf($(obj).attr("name")) != -1){
        			$(obj).show();
        		}else{
        			$(obj).hide();
        		}
        	});
        	
        	$.each($("i[name^='per_']"), function(index,obj){
        		$(obj).css("visibility","visible");
        		if(perArr.indexOf($(obj).attr("name")) != -1){
        			$(obj).show();
        		}else{
        			$(obj).hide();
        		}
        	});
        }
        
	}
});