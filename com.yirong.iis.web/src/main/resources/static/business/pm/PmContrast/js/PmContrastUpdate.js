//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
            sourceCode: null,
            sourceName: null,
            contrastTypeId : null,
            standardId : null,
            standardCode : null,
        	standardName : null
        },
        //前端验证
        updateRules: {
            sourceCode: [
                { required: true, message: '请输入源编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
            ],
            sourceName: [
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],
            standardCode: [
                { required: true, message: '请选择标准编码', trigger: 'blur' }
            ],
            standardName: [
                { required: true, message: '请选择标准名称', trigger: 'blur' }
            ]
        },
        //自定义参数
        standardTypeName : null
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmContrastApi/update",updateVue.updateModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.info(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }
                } 
            });
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		},
		//标准数据选择器
		changeClick : function(){
			//处理参数
			commonVue.PmStandardListParam = {
				standardTypeId  : mainVue.standardTypeId,
				orders : [{
					orderField : "standard_code",
					orderType : "asc"
				}]
			};
			//指定回调函数
			commonVue.PmStandardListCallBack = this.changeClickCallback;
			//打开页面
			ak.dialog("PmStandardList","common/PmStandardList.html");
		},
		//标准数据选择器回调事件
		changeClickCallback : function(row){
			this.updateModel.standardCode = row.standard_code;
			this.updateModel.standardName = row.standard_name;
			this.updateModel.standardId = row.id;
		}
	},
	//页面初始化事件
    mounted: function () {
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("pm","PmContrastApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.standardTypeName = data.standardTypeName;
    		this.updateModel.id = checkObj.id;
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);