//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			ctObjectId: null,
			taskName:null,
			firstRunTime:null,
			runCycle:''
        },
        //前端验证
        saveRules: {
        	ctObjectId: [
                { required: true, message: '请选择对象', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],taskName: [
                 { required: true, message: '请输入任务名称', trigger: 'blur' },
             ]
        },
        ctObjectName:null,
        runCycleShow:true
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                	if(saveVue.saveModel.firstRunTime){
                		var firstRunTime = saveVue.saveModel.firstRunTime;
                		if(ak.isNotNullOrEmpty(firstRunTime)){
                			saveVue.saveModel.firstRunTime = ak.date.format('yyyy-MM-dd', firstRunTime);
                		}
                	}
                    var result = ak.msService("ct","CtTaskApi/save",saveVue.saveModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	//刷新树
                    	mainVue.treeRefresh();
                    }else{
                    	//提示
                    	ak.warning(result.msg);
                    }
                } 
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
		},
		//标准分类选择器点击事件
	    changeClick : function(){
			//定义回调函数
			commonVue.CtVeCtObjectListCallBack = this.changeClickCallback;
			ak.dialog("CtCatalogTree","common/CtVeCtObjectList.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(row){
			this.saveModel.ctObjectId = row.id;
			this.ctObjectName =  row.ct_object_name;
			if(row.ct_channel_code=='010001'){
				//说明是爬虫类型---禁用首次运行时间以及运行间隔
				saveVue.runCycleShow=false;
			}else{
				saveVue.runCycleShow=true;
			}
		}
		
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//ID作为传递参数，所以放在model中
    	this.saveModel.standardTypeId = mainVue.tableSearchModel.standardTypeId;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);