//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
            flowName: null,
            flowRemark: null
        },
        //前端验证
        saveRules: {
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
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmFlowApi/save",saveVue.saveModel);
                    if(result.code == 0){//操作成功
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
	}
});
//生成vue对象
var saveVue = new Vue(dialogAttr);