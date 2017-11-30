//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			websiteGroupName: null,
			websiteGroupRemark:null,
			catalogId:$("#catalogId").val()
        },
        //前端验证
        saveRules: {
            /*standardCode: [
                { required: true, message: '请输入标准编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
            ],*/
        	websiteGroupName: [
                { required: true, message: '请输入目录名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
        appList:null
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("ct","CtWebsiteGroupApi/save",saveVue.saveModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	/*//刷新树
                    	mainVue.treeRefresh();*/
                    }else{
                    	ak.warning(result.msg);
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
    	var result = ak.msService("ct","CtWebsiteGroupApi/getSpidperapp",null);
    	if(result.code==0){
    		var data = [];
    		data=JSON.parse(result.data); 
    		this.appList = data;
    	}
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//ID作为传递参数，所以放在model中
    	this.saveModel.standardTypeId = mainVue.tableSearchModel.standardTypeId;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);