//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#pmssave',
	data : {
		//与后台及元素交互的model
		saveModel: {
			resourceId: null,
			name: null,
			sn: null,
			description:null,
			code:null,
			url:null
        },
        //前端验证
        saveRules: {
        	name: [
                 { required: true, message: '请输入权限名称', trigger: 'blur' },
                 { min: 1, max: 64, message: '长度在1到 64', trigger: 'blur' }
            ],
        	sn: [
                { required: true, message: '请输入授权编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
            ],
            url: [
                 { min: 1, max: 100, message: '长度在1到 100', trigger: 'blur' }
            ],
            code: [
                  { required: true, message: '请选择事件类型', trigger: 'blur' }
            ],
            description: [
                 { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ] 
        },
        //自定义参数
        parentName :'',
        //自定义参数
        codeoptions :[]
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","resourceApi/addPermission",saveVue.saveModel);
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
    	//新增保存传入后台
		this.saveModel.resourceId = mainVue.tableSearchModel.resourceId;
		
		this.codeoptions = mainVue.codeoptions;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);