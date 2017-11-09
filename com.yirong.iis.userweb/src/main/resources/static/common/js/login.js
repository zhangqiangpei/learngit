$(function(){

    $('#btn').click(function(){
        fnLogin();
    });

    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            fnLogin();
        }
    };

    fnLogin = function() {
        if(valid()){
            var param = {
                userName : $('#uname').val(),
                userPassword: $('#pwd').val()
            };
            
            if(z.isNotNullOrEmpty(param)){
                param = JSON.stringify(param);
            }
          
            var data = {
                param : param
            };
            window.location.href = "index.html";
            z.ajax({
                url : "/baseRest/loginSubmit",
                async : false,
                data:data,
                success : function(res, textStatus) {
                    if(z.isNullOrEmpty(res)){
                        z.error("登陆失败.");
                    }else if(res.code == 0){	
                        window.location.href = "/forward.do?viewPath=business/mm/index/index.html";
                    }else {
                        z.warning(res.msg);
                    }
                }
             });
            
        }
    }
    
    valid = function(){
        var userName = $('#uname').val();
        var userPassword = $('#pwd').val();
        
        if(z.isNullOrEmpty(userName)){
            z.info("请输入用户名");
            return false;
        }
        
        if(z.isNullOrEmpty(userPassword)){
            z.info("请输入密码");
            return false;
        }
        
        return true;
    }
    
});

