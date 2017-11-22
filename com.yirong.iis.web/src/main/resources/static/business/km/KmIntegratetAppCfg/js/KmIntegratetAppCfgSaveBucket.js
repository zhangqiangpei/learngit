//获取dialog属性
  
var dialogAttr = getDialogMergeAttr({
	el: '#saveBucket',
	data : {
		//与后台及元素交互的model
		saveBucketModel: {
			id : null,
			bucket : "" 
		},
 
        //前端验证
		saveBucketRules: {
		 
        } 
 
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveBucketModel"].validate(function (valid) {
                if (valid) {//验证通过
                	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/createBucket",　saveBucketVue.saveBucketModel);
	            	if(result.code == 0){//操作成功
	                	//提示
	                	ak.info(result.msg);
	                	saveBucketVue.dialogClose();
	                	//刷新表格
	                	mainVue.tableRefresh();
	                }else{
	                	//提示
	                	ak.warning(result.msg);
	                }
                } 
            });
		},
		 
		//重置按钮
		saveReset : function(){
			this.$refs["saveBucketModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	 this.saveBucketModel.id = mainVue.handleEditId;
    }
});
//生成vue对象
var saveBucketVue = new Vue(dialogAttr);