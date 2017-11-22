//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
            flowName: null,
            flowRemark: null
        },
        //前端验证
        updateRules: {
            flowName: [
                { required: true, message: '请输入流程名称', trigger: 'blur' },
                { min: 1, max: 40, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ],
            flowRemark: [
                { min: 1, max: 100, message: '长度在1 到 100 个字符', trigger: 'blur' }
            ]
        }
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmFlowApi/update",updateVue.updateModel);
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
		//关闭按钮
		updateCancel : function(){
			updateVue.dialogClose();
		}
	},
	//页面初始化事件
    mounted: function () {
    	//处理参数
    	var ids = mainVue.tableGetCheckIds();
    	var result = ak.msService("pm","PmFlowApi/get",{ id : ids[0]});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.updateModel.id = ids[0];
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);