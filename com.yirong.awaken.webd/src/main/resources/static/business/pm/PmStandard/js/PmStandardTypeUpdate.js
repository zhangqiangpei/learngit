//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#updateType',
	data : {
		//与后台及元素交互的model
		updateTypeModel: {
            standardTypeName: null,
            parentId : null
        },
        //前端验证
        updateTypeRules: {
            standardTypeName: [
                { required: true, message: '请输入标准分类名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
        pname : null
	},
	methods : {
		//确定按钮
		updateTypeSubmit : function(){
			this.$refs["updateTypeModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmStandardApi/updateType",updateTypeVue.updateTypeModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateTypeVue.dialogClose();
                    	//刷新树
                    	mainVue.treeRefresh();
                    }
                } 
            });
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateTypeVue.dialogClose();
		}
	},
	//页面初始化事件
    mounted: function () {
    	var idParam = mainVue.tableSearchModel.standardTypeId;
    	//处理参数
    	var result = ak.msService("pm","PmStandardApi/getType",{ id : idParam});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateTypeModel);
    		//处理界面显示属性
    		this.pname = data.pname;
    		this.updateTypeModel.id = idParam;
    	}
    }
});
//生成vue对象
var updateTypeVue = new Vue(dialogAttr);