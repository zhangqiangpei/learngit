//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			fileTypeName: null,
        },
        //前端验证
        saveRules: {
            /*standardCode: [
                { required: true, message: '请输入标准编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
            ],*/
        	fileTypeName: [
                { required: true, message: '请输入文件分类名称名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        }
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("ct","CtManualDataApi/save",saveVue.saveModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	//刷新树
                    	mainVue.treeRefresh();
                    }else{
                    	//提示
                    	ak.warning(result.msg);
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
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//ID作为传递参数，所以放在model中
    	this.saveModel.standardTypeId = mainVue.tableSearchModel.standardTypeId;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);