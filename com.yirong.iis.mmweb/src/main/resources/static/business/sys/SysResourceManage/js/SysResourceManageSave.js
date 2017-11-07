//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			applicationId: null,
			parentId: null,
			name: null,
			sn: null,
			priority: 1,
			url: null,
			className: null,
			iconName:null 
        },
        //前端验证
        saveRules: {
        	name: [
                 { required: true, message: '请输入资源名称', trigger: 'blur' },
                 { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
            ],
        	sn: [
                { required: true, message: '请输入授权编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
            ],
            url: [
                { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ],
            className: [
                 { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ],
            iconName: [
                 { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ] 
        },
        //自定义参数
        parentName :''
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","resourceApi/create",saveVue.saveModel);
                    if(null != result && result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	if(null != saveVue.saveModel.parentId){
                    		//刷新表格
                        	mainVue.tableRefresh();
                    	}
                    	//刷新树
                    	mainVue.treeRefresh();
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
    	//新增保存传入后台
		this.saveModel.applicationId = mainVue.tableSearchModel.applicationId;
		this.saveModel.parentId = mainVue.tableSearchModel.resourceId;
		this.parentName = mainVue.resourceName;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);