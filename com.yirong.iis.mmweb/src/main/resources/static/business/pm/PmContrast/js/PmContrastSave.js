//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
            sourceCode: null,
            sourceName: null,
            standardCode : null,
        	standardName : null
        },
        //前端验证
        saveRules: {
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
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","PmContrastApi/save",saveVue.saveModel);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }
                } 
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
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
			this.saveModel.standardCode = row.standard_code;
			this.saveModel.standardName = row.standard_name;
			this.saveModel.standardId = row.id;
		}
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//ID作为传递参数，所以放在model中
    	this.saveModel.contrastTypeId = mainVue.tableSearchModel.contrastTypeId;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);