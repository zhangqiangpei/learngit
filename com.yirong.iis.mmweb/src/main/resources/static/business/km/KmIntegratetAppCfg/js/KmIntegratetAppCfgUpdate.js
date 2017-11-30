//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			appName : null,
			account : null,
			secretKey : null,
    		state: null　
        },
        //前端验证
        updateRules: {
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
        },
        //编辑操作id
        handleEditId : mainVue.handleEditId
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("kmdm","kmIntegratetAppCfgApi/update",updateVue.updateModel);                    
                    if(null == result){
        	    		ak.error("系统异常");
        	    	}else if(result.code == 0){//请求成功
        	    		//提示
                    	ak.info(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
        	    	}else {
        	    		ak.error(result.msg);
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
    	//初始化修改数据
    	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/getById",{ id : mainVue.handleEditId});
    	if(null == result){
    		ak.error("系统异常");
    	}else if(result.code == 0){//请求成功
    		//复制属性
    		//ak.copyObjValue(data,this.updateModel);
    		this.updateModel = result.data;
    	}else {
    		ak.warning(result.msg);
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);