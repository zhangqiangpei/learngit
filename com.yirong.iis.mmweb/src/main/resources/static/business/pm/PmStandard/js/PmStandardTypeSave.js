//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#saveType',
	data : {
		//与后台及元素交互的model
		saveTypeModel: {
            standardTypeName: null
        },
        //前端验证
        saveTypeRules: {
            standardTypeName: [
                { required: true, message: '请输入标准分类名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
        standardTypePname : null
	},
	methods : {
		//确定按钮
		saveTypeSubmit : function(){
			this.$refs["saveTypeModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmStandardApi/saveType",saveTypeVue.saveTypeModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveTypeVue.dialogClose();
                    	//刷新树
                    	mainVue.treeRefresh();
                    }
                } 
            });
		},
		//重置按钮
		saveTypeReset : function(){
			this.$refs["saveTypeModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.standardTypePname = mainVue.standardTypeName;
    	//ID作为传递参数，所以放在model中
    	this.saveTypeModel.parentId = mainVue.tableSearchModel.standardTypeId;
    }
});
//生成vue对象
var saveTypeVue = new Vue(dialogAttr);