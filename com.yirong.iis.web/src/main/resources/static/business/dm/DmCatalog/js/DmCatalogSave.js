//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			catalogName: null,
			catalogAttr:'013001',
			catalogRemark:null,
			catalogCode:null
        },
        //前端验证
        saveRules: {
        	catalogName: [
                { required: true, message: '请输入目录名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ]/*,
            catalogCode: [
              { required: true, message: '请输入目录编码', trigger: 'blur' },
              { min: 1, max: 20, message: '长度在1 到 20 个字符', trigger: 'blur' }
          ]*/,
            catalogRemark: [
                { min: 1, max: 100, message: '长度在1 到 100 个字符', trigger: 'blur' }
            ]
        },
        //目录分类名称
        catalogTypeName : null,
        //下拉框数组
        catalogAttrList : []
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","DmCatalogApi/save",saveVue.saveModel);
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
		}
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.catalogTypeName = mainVue.pname;
    	//ID作为传递参数，所以放在model中
    	this.saveModel.catalogTypeId = mainVue.tableSearchModel.catalogTypeId;
    	//初始化发布状态下拉框
    	this.selectInit("pm");
    	this.catalogAttrList = this.selectSearch("013");
    }
});

//工具属性
var utilttr = getUtilMergeAttr({});

//生成vue对象
var saveVue = ak.getMergeVue(dialogAttr,utilttr);