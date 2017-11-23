//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#updateType',
	data : {
		//与后台及元素交互的model
		updateTypeModel: {
			ctChannel : null,
			ctObjectId : null,
			standardTypeId : null
        },
        //自定义参数
        standardTypeName : null,
        ctObjectName : null,
        ctChannelName : null
	},
	methods : {
		//确定按钮
		updateTypeSubmit : function(){
			if(ak.isNullOrEmpty(this.standardTypeName)){
            	ak.warning("请先选择标准分类名称");
            	return;
			}
            var result = ak.msService("pm","PmContrastApi/updateType",updateTypeVue.updateTypeModel);
            if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//关闭窗口
            	updateTypeVue.dialogClose();
            	//刷新树
            	mainVue.treeRefresh();
            }
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateTypeVue.dialogClose();
		},
		//标准分类选择器点击事件
		changeClick : function(){
			//定义回调函数
			commonVue.PmStandardTypeTreeCallBack = this.changeClickCallback;
			ak.dialog("PmStandardTypeTree","common/PmStandardTypeTree.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(data){
			this.standardTypeName = data.name;
			this.updateTypeModel.standardTypeId = data.id;
		}
	},
	//页面初始化事件
    mounted: function () {
    	var idParam = mainVue.tableSearchModel.contrastTypeId;
    	//处理参数
    	var result = ak.msService("pm","PmContrastApi/getType",{ id : idParam});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateTypeModel);
    		//处理界面显示属性
    		this.standardTypeName = data.standardTypeName;
    		this.ctObjectName = data.ctObjectName;
    		this.ctChannelName = data.ctChannelName;
    		this.updateTypeModel.id = idParam;
    	}
    }
});
//生成vue对象
var updateTypeVue = new Vue(dialogAttr);