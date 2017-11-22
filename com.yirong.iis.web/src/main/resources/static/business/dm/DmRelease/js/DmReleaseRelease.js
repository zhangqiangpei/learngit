//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#release',
	data : {
		//与后台及元素交互的model
		releaseModel: {
            isInfoManage: "1"
        }
	},
	methods : {
		//确定按钮
		releaseSubmit : function(){
			if(this.releaseModel.isInfoManage == 0 ){
				//提示
	            ak.warning("至少勾选一个发布内容");
			}else{//验证通过
	        	//处理参数
	        	releaseVue.releaseModel.isRelease = true;
	        	//处理参数
	        	var ids = mainVue.tableGetCheckIds();
	        	var idsStr = "";
	        	$.each(ids,function(i,n){
	        		idsStr += n;
	        		if(i != ids.length){
	        			idsStr += ",";
	        		}
	        	});
	        	releaseVue.releaseModel.idStrs = idsStr;
	        	//请求后台--请求新的方法
	            var result = ak.msService("pm","DmReleaseApi/publish",releaseVue.releaseModel);
	            if(result.code == 0){//操作成功
	            	//提示
	            	ak.success(result.msg);
	            	//关闭窗口
	            	releaseVue.dialogClose();
	            	//刷新表格
	            	mainVue.searchClick();
	            }
             } 
		},
		//重置按钮
		releaseReset : function(){
			this.$refs["releaseModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    }
});
//生成vue对象
var releaseVue = new Vue(dialogAttr);