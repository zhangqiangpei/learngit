document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if (e && e.keyCode == 13) {
    	fnLogin();
    }
};


function fnLogin() {
	
	if(valid()){
		var param = {
			userName : $('#userName').val(),
	        userPassword: $('#userPassword').val()
		};
		
		if(ak.isNotNullOrEmpty(param)){
			param = JSON.stringify(param);
		}
		var data = {
			param : param
		};
		
		/*var data = ak.msService('sys','loginApi/login',para); 
		if(null == data){
			ak.error("系统异常");
		}else if(data.code == 0){
			window.location.href = "forward.do?viewPath=potole.html" ;
		}else{
			ak.warning(data.msg);
		}*/
		/*if(ak.isNotNullOrEmpty(param)){
			param = JSON.stringify(param);
		}*/
 
		 ak.ajax({
     		url : "/baseRest/loginSubmit",
     		async : false,
     		data:data,
     		success : function(res, textStatus) {
     			if(ak.isNullOrEmpty(res)){
     				ak.error("登陆失败.");
     			}else if(res.code == 0){	
     				//ak.info(res.msg);
     				window.location.href = "forward.do?viewPath=potole.html" ;
     			}else {
     				ak.warning(res.msg);
     			}
     		}
         });
	}
}

function valid(){
	var userName = $('#userName').val();
	var userPassword = $('#userPassword').val();
	
	if(ak.isNullOrEmpty(userName)){
		ak.info("请输入用户名");
		return false;
	}
	
	if(ak.isNullOrEmpty(userPassword)){
		ak.info("请输入密码");
		return false;
	}
	
	return true;
}