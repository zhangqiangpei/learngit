//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			parentName: null,
			pcode: null,
			parentId: null,
			name: null,
			priority: 1,
			type: null,
			zipCode: null,
			address:null,
			master:null,
			fax:null,
			email: null,
			description: null
        },
        //前端验证
        saveRules: {
        	/*parentName: [
                { required: true, message: '请选择机构', trigger: 'blur' }
            ],*/
            name: [
                { required: true ,  message: '请输入机构名称', trigger: 'blur' },
                { min: 1, max: 100, message: '长度在1到 100个字符', trigger: 'blur' }
            ],
            type: [
                       { required: true ,  message: '请选择机构类型', trigger: 'blur' }
            ],
            email: [
                {type:'email', message: '请输入正确的Email', trigger: 'blur' }
            ] 
        },
        //自定义参数
        typeoptions :[]
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","organizationApi/create",saveVue.saveModel);
                    if(null != result && result.code == 0){//操作成功s
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	//刷新树
                    	mainVue.treeRefresh();
                    }
                } 
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
		},
		clearParent : function(){
			saveVue.saveModel.parentName = '';
			saveVue.saveModel.pcode = '';
			saveVue.saveModel.parentId = '';
		}
	},
	//页面初始化事件
    mounted: function () {
    	//新增保存传入后台
		this.saveModel.parentName = mainVue.orgName;
		this.saveModel.pcode = mainVue.orgCode;
		this.saveModel.parentId = mainVue.orgId;
		this.typeoptions = mainVue.typeoptions;
     
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);