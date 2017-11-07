//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			name : null,
			parentId : null,
			parentName : null,
			code: null ,
			priority: null
        },
        //前端验证
        updateRules: {
        	name: [
               { required: true, message: '请输入字典名称', trigger: 'blur' },
               { min: 1, max: 256, message: '长度在1到256个字符', trigger: 'blur' }
           ]
        },
        //编辑操作id
        handleEditId : mainVue.handleEditId
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","sysDictionaryApi/update",updateVue.updateModel);                    
                    if(null == result){
        	    		ak.error("系统异常");
        	    	}else if(result.code == 0){//请求成功
        	    		//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
        	    	}else {
        	    		ak.error(result.msg);
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
    	//初始化修改数据
    	var result = ak.msService("sys","sysDictionaryApi/getById",{ id : mainVue.handleEditId});
    	if(null == result){
    		ak.error("系统异常");
    	}else if(result.code == 0){//请求成功
    		//复制属性
    		this.updateModel = result.data;
    	}else {
    		ak.warning(result.msg);
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);