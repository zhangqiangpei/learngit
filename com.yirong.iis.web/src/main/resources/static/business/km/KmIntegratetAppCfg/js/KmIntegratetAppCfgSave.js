//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			appName : null,
			account : null,
			secretKey : null,
    		state: "000000"
		},
 
        //前端验证
		saveRules: {
			appName: [
                { required: true, message: '请输入应用名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ],
            account: [
                {required: true ,message: '请输入帐号', trigger: 'blur' },
                {min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ],
            secretKey: [
                {required: true ,message: '请输入密钥', trigger: 'blur' },
                {min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ]
        } 
	},
	methods : {
		 
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("kmdm","kmIntegratetAppCfgApi/save",saveVue.saveModel);
                    if(null == result){
                    	ak.error("系统错误,请联系管理员");
                    }else  if(result.code == 0){//操作成功
                    	//提示
                    	ak.info(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }else{
                    	//提示
                    	ak.error(result.msg);
                    }
                } 
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	 
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);