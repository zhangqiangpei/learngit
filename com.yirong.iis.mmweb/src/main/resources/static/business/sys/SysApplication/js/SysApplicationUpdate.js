//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			name : null,
			title : null,
			sn : null,
			logo: null,
			domain: null,
			customIndexView: null
		},
 
        //前端验证
		updateRules: {
			name: [
                { required: true, message: '请输入应用名称', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到100个字符', trigger: 'blur' }
            ],
            sn: [
                {required: true ,message: '请输入应用SN', trigger: 'blur' },
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
                    var result = ak.msService("sys","applicationManageApi/update",updateVue.updateModel);                    
                    if(null != result && result.code == 0){//请求成功
        	    		//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
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
    	var result = ak.msService("sys","applicationManageApi/getById",{ id : mainVue.handleEditId});
    	if(null != result &&  result.code == 0){//请求成功
    		//复制属性
    		this.updateModel = result.data;
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);