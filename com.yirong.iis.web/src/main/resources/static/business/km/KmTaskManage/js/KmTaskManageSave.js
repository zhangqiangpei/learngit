//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			jobName : null,
			jobClassName : null,
			jobGroupName : null,
			cronExpression : null,
			jobPara : null 
		},
 
        //前端验证
		saveRules: {
			 
        } 
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("kmdm","kmDocJobApi/addjob",saveVue.saveModel);
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
    	 
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);