//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			catalogCode: null,
			catalogName: null,
			catalogRemark:null,
			catalogTypeId : null
        },
        //前端验证
        updateRules: {
        	catalogCode: [
                { required: true, message: '请输入目录编码', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到20 个字符', trigger: 'blur' }
            ],
        	catalogName: [
                { required: true, message: '请输入目录名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ],
            catalogCode: [
              { required: true, message: '请输入目录编码', trigger: 'blur' },
              { min: 1, max: 20, message: '长度在1 到 20 个字符', trigger: 'blur' }
          ],
            catalogRemark: [
                { min: 1, max: 100, message: '长度在1 到 100 个字符', trigger: 'blur' }
            ]
        },
        //目录分类名称
        catalogTypeName : null,
        //目录属性名称：
        catalogAttrName : null,
        //下拉框数组
        catalogAttrList : []
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("pm","DmCatalogApi/update",updateVue.updateModel);
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
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		}
	},
	//页面初始化事件
    mounted: function () {
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("pm","DmCatalogApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data,this.updateModel);
    		//处理界面显示属性
    		this.catalogTypeName = data.catalogTypeName;
    		this.catalogAttrName = data.catalogAttrName;
    		this.updateModel.id = checkObj.id;
    	}
    }
});

//生成vue对象
var updateVue = new Vue(dialogAttr);