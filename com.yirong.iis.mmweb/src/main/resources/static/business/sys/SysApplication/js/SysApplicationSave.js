//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			name : null,
			title : null,
			sn : null,
			logo: null,
			domain: null,
			customIndexView: null
		},
 
        //前端验证
		saveRules: {
			name: [
                { required: true, message: '请输入应用名称', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到100个字符', trigger: 'blur' }
            ],
            sn: [
                {required: true ,message: '请输入应用SN', trigger: 'blur' },
                {min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ]
        } 
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","applicationManageApi/save",saveVue.saveModel);
                    if(null != result && result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
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