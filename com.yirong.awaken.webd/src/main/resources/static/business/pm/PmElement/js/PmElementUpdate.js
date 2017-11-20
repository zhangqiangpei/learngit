//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			elementRemark: null,
			name: null,
			elementName: null
        },
        //前端验证
        updateRules: {
            
        }
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmElementApi/update",updateVue.updateModel);
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
    	var result = ak.msService("pm","PmElementApi/get",{ id : mainVue.checkId});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.updateModel.id = mainVue.checkId;
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);