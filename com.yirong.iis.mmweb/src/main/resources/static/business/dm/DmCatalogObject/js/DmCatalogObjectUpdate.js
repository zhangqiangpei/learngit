//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			catalogName: null,
			ids:null
        },
        //前端验证
        updateRules: {
        	catalogName: [
                { required: true, message: '请选择目录名称', trigger: 'blur' }
            ]
        }
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","DmCatalogObjectApi/update",updateVue.updateModel);
                    if(result.code == 0){//操作成功
                    	//处理etl任务
                    	var result = ak.msService("ct","CtTaskApi/addLoad",result.data);
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
		},
		//目录选择器点击事件
        changeClick : function(){
        	commonVue.DmCatalogTreeCallBack = this.changeClickCallback;
			ak.dialog("DmCatalogTree","common/DmCatalogTree.html");
        },
        //目录选择器回调事件
        changeClickCallback : function(data){
        	//处理参数
        	this.updateModel.catalogName = data.name;
        	this.updateModel.catalogId = data.id;
        	
        }
	},
	//页面初始化事件
    mounted: function () {
    	//处理参数
    	var ids = mainVue.tableGetCheckIds();
    	this.updateModel.count = ids.length;
    	this.updateModel.ids = ids;
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);