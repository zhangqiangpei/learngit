//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#saveType',
	data : {
		//与后台及元素交互的model
		saveTypeModel: {
			standardTypeName : null
        },
        //前端验证
        saveTypeRules: {
            standardTypeName: [
                { required: true, message: '请选择标准分类', trigger: 'blur' }
            ]
        },
        //自定义参数
        ctObjectName : null,
        ctChannelName : null
	},
	methods : {
		//确定按钮
		saveTypeSubmit : function(){
			this.$refs["saveTypeModel"].validate(function (valid) {
                if (valid) {//验证通过
                	var result = ak.msService("pm","PmContrastApi/saveType",saveTypeVue.saveTypeModel);
		            if(result.code == 0){//操作成功
		            	//提示
		            	ak.success(result.msg);
		            	//关闭窗口
		            	saveTypeVue.dialogClose();
		            	//刷新树
		            	mainVue.treeRefresh();
		            }
                }
			});
		},
		//重置按钮
		saveTypeReset : function(){
			this.$refs["saveTypeModel"].resetFields();
		},
		//标准分类选择器点击事件
		changeClick : function(){
			//定义回调函数
			commonVue.PmStandardTypeTreeCallBack = this.changeClickCallback;
			ak.dialog("PmStandardTypeTree","common/PmStandardTypeTree.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(data){
			this.saveTypeModel.standardTypeName = data.name;
			this.saveTypeModel.standardTypeId = data.id;
		}
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.ctObjectName = mainVue.ctObjectName;
    	this.ctChannelName = mainVue.ctChannelName;
    	//ID作为传递参数，所以放在model中
    	this.saveTypeModel.standardTypeId = mainVue.tableSearchModel.standardTypeId;
    	this.saveTypeModel.ctObjectId = mainVue.treeSearchModel.ctObjectId;
    	this.saveTypeModel.ctChannel = mainVue.treeSearchModel.ctChannel;
    }
});
//生成vue对象
var saveTypeVue = new Vue(dialogAttr);