//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			parentName: null,
			code: null,
			parentId: null,
			name: null,
			priority: null,
			zipCode: null,
			address:null,
			master:null,
			fax:null,
			email: null,
			description: null
        },
        //前端验证
        updateRules: {
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
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","organizationApi/update",updateVue.updateModel);
                    if(null != result && result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	//刷新树
                    	mainVue.treeRefresh();
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
    	//修改方法,加载修改信息
    	var result = ak.msService("sys","organizationApi/getById",{ id :  mainVue.handleEditId});
    	if(null != result && result.code == 0){//操作成功
    		this.updateModel = result.data;
    	}
    	this.typeoptions = mainVue.typeoptions;
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);