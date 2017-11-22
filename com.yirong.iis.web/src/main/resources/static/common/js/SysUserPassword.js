//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			oldPwd : null,
			newPwd : null,
			affirmPwd : null
        },
        //前端验证
        updateRules: {
        	oldPwd : [
                { required: true, message: '请输入旧密码', trigger: 'blur' },
                { min: 8, max: 32, message: '长度在8到32个字符,必须包含字母、数字以及符号！', trigger: 'blur' }
            ],
        	newPwd : [
               { required: true, message: '请输入新密码', trigger: 'blur' },
               { min: 8, max: 32, message: '长度在8到32个字符,必须包含字母、数字以及符号！', trigger: 'blur' }
           ],
           affirmPwd : [
               { required: true, message: '请再次输入新密码', trigger: 'blur' },
               { min: 8, max: 32, message: '长度在8到32个字符，必须包含字母、数字以及符号！', trigger: 'blur' }
           ]
        },
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                	if(updateVue.updateModel.newPwd == updateVue.updateModel.affirmPwd){
                		var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
                    	if(regex.test(updateVue.updateModel.newPwd)){
                    		var param = {
                    				oldPwd:	updateVue.updateModel.oldPwd,
                    				newPwd: updateVue.updateModel.newPwd
                    		}
                    		var result = ak.msService("sys","userManageApi/updatePwd",param);                    
                            if(null == result){
                	    		ak.error("系统异常");
                	    	}else if(result.code == 0){//请求成功
                	    		//提示
                            	ak.success(result.msg);
                            	//关闭窗口
                            	updateVue.dialogClose();
                            	//刷新表格
                            	mainVue.tableRefresh();
                	    	}else {
                	    		ak.error(result.msg);
                	    	}
                    	}else{
                    		ak.warning('密码中必须包含字母、数字、符号，至少8个字符，最多32个字符!');
                    	}
                	}else{
                		ak.warning('两次输入的新密码不相同，请确认后重新输入！');
                	}
                } 
            });
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		}	
	},
	//页面初始化事件
    mounted: function () {
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);