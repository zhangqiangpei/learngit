//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			ctObjectId: null,
			taskName:null,
			firstRunTime:null,
			runCycle:''
        },
        //前端验证
        updateRules: {
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
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                	var firstRunTime = updateVue.updateModel.firstRunTime;
            		if(ak.isNotNullOrEmpty(firstRunTime)){
            			updateVue.updateModel.firstRunTime = ak.date.format('yyyy-MM-dd', firstRunTime);
            		}
                    var result = ak.msService("ct","CtTaskApi/update",updateVue.updateModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
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
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		},
		//标准分类选择器点击事件
	    changeClick : function(){
			//定义回调函数
			commonVue.CtVeCtObjectListCallBack = this.changeClickCallback;
			ak.dialog("CtCatalogTree","common/CtVeCtObjectList.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(row){
			this.updateModel.ctObjectId = row.id;
			this.ctObjectName =  row.ct_object_name;
			if(row.ct_channel_code=='010001'){
				//说明是爬虫类型---禁用首次运行时间以及运行间隔
				updateVue.runCycleShow=false;
			}else{
				updateVue.runCycleShow=true;
			}
		}
	},
	//页面初始化事件
    mounted: function () {
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("ct","CtTaskApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var data = result.data.CtTask;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.updateModel.id = checkObj.id;
    		this.ctObjectName=result.data.ctObjectName;
    		if(data.channel=='010001'){
    			this.runCycleShow=false;
    		}
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);