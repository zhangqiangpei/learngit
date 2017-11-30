//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
            standardCode: null,
            standardName: null,
            standardTypeId : null
        },
        //前端验证
        updateRules: {
            standardCode: [
                { required: true, message: '请输入标准编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
            ],
            standardName: [
                { required: true, message: '请输入标准名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
        standardTypeName : null
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmStandardApi/update",updateVue.updateModel);
                    if(result.code == 0){//操作成功
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
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("pm","PmStandardApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.standardTypeName = data.standardTypeName;
    		this.updateModel.id = checkObj.id;
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);