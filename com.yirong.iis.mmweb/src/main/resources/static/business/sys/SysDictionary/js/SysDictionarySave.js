//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			name : null,
			parentId : null,
			parentName : null,
			code: null ,
			priority: null,
			applicationId: null
		},
 
        //前端验证
		saveRules: {
			name: [
                { required: true, message: '请输入字典名称', trigger: 'blur' },
                { min: 1, max: 256, message: '长度在1到256个字符', trigger: 'blur' }
            ],
            applicationId: [
                {required: true ,message: '请选择应用名称', trigger: 'blur' }
            ]  
        },
      //自定义参数
      appoptions : [],
      defprops:{
      	value: 'id',
      	label: 'name',
      	children:'children'		
      },
      options: []
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","sysDictionaryApi/save",saveVue.saveModel);
                    if(null == result){
                    	ak.error("系统错误,请联系管理员");
                    }else  if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }else{
                    	//提示
                    	ak.error(result.msg);
                    }
                } 
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
		},
		appschg : function(applicationId){
			//请求后台数据数据
	    	var result = ak.msService("sys","sysDictionaryApi/getTree",{"applicationId" : applicationId});
	    	if(null != result && result.code == 0){//操作成功
	    		this.options = ak.ArrayToTreeData(result.data);
	    	}
		},
		handleChange : function(value){
			this.saveModel.parentId = value[0];
	    },
		//树选择器点击事件
        changeClick : function(){
        	commonVue.SysDictionaryTreeCallBack = this.changeClickCallback;
			ak.dialog("SysDictionaryTree","common/SysDictionaryTree.html");
        },
        //树选择器回调事件
        changeClickCallback : function(data){
        	this.saveModel.parentId = data.id;
        	this.saveModel.parentName = data.name;
        	this.saveModel.code = data.atttibute.code;
        }
	},
	//页面初始化事件
    mounted: function () {
    	this.appoptions = mainVue.appoptions;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);