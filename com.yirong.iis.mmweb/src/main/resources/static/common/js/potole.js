
$(function(){
	loadData();
	getUserCount();
});

var roleIds;
var userdn ;
var orgn ;

function loadData(){
	ak.ajax({
		url : "/baseRest/getLoginUser",
		async : false,
		type : "post",
		dataType : 'json',
		success : function(data, textStatus) {
			if(ak.isNullOrEmpty(data)){
				ak.error("获取用户信息失败.");
			}else if(data.code == 0){
				var data = data.data;
				if(ak.isNotNullOrEmpty(data)){
					$("#userDisplayName").text(data.userDisplayName);
					$("#organizationName").text(data.organizationName);
					userdn = data.userDisplayName;
					orgn = data.organizationName
					roleIds = data.userRoleIds ;
					//应用ids;
					var appIds = data.applicationIds;
					//加载应用数据
					var appData = ak.msService('sys','loginApi/getApp',{appIds : appIds}); 
					if(null == appData){
						ak.error("系统异常");
					}else if(appData.code != 0){
						ak.warning(data.msg);
					}else{
						var html = '';
						$.each(appData.data, function(idx, bean) {
							var url = bean.domain + 'forward.do?viewPath=' + bean.custom_index_view + '&' + bean.id;
							html += '<a class="item"   href="'+url+'"  target="_blank" >';
							html += '<div class="bg">';
							html += '<img src="'+bean.logo+'" />';
							html += '</div>';
							html += '<div class="title">'+bean.name+'</div>';
							html += '</a>';
						});	
						$("#list").html(html);	 
					}
				}	
			}else{
				ak.warning(data.msg);
			}
		}
	});
	
}

//退出
function loginOut() {
	ak.confirm('确认退出系统吗?',function(){
		/*var res = ak.msService('sys','loginApi/loginout',null); 
		debugger;
     	if(null == res){
				ak.error("系统异常");
			}else if(res.code != 0){
				ak.warning(data.msg);
			}else{
				ak.success('销毁缓存成功');
				window.location.href = "login.do" ;
			}*/
     	
     	
     	ak.ajax({
    		url : "/baseRest/loginout",
    		async : false,
    		type : "post",
    		dataType : 'json',
    		success : function(data, textStatus) {
    			if(ak.isNullOrEmpty(data)){
    				ak.error("系统异常");
    			}else if(data.code == 0){
    				ak.success('销毁缓存成功');
    				window.location.href = "login.do" ;
    			}else{
    				ak.warning(data.msg);
    			}
    		}
    	});
	});
}
//跳转url 
function jump(item){
	window.open($(item).attr("url")+"&"+$(item).attr("id"));
}


function getSessionId(){
	var c_name = 'JSESSIONID';
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
}


function getUserCount(){
 	ak.ajax({
		url : "/baseRest/getSessionCount",
		async : false,
		type : "post",
		dataType : 'json',
		success : function(data, textStatus) {
			if(ak.isNullOrEmpty(data)){
				ak.error("获取在线人数失败");
			}else if(data.code == 0){
				$("#userCount").attr("title","在线人数"+data.data+"人");
			}else{
				ak.warning(data.msg);
			}
		}
	});
}
