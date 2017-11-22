//获取dialog属性
var validateEsIndex  = function(rule, value, callback) {
	var Regx = /^[a-z0-9_]*$/;
    if (Regx.test(value)) {
    	 callback();
    }
    else {
    	callback(new Error('只能由小写字母数字和下划线组成!'));
 	}
	  
};
      
var dialogAttr = getDialogMergeAttr({
	el: '#saveEs',
	data : {
		//与后台及元素交互的model
		saveEsModel: {
			id : null,
			esIndex : null,
			esType : null,
			esSetting  : null,
			esMapping: null
		},
 
        //前端验证
		saveEsRules: {
			esIndex: [
                { required: true, message: '请输入es索引名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1到100个字符', trigger: 'blur' } ,
                { validator: validateEsIndex, trigger: 'blur' }
            ],
            esType: [
                {required: true ,message: '请输入es类型', trigger: 'blur' },
                {min: 1, max: 64, message: '长度在1到100个字符', trigger: 'blur' }
            ] 
        } 
 
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveEsModel"].validate(function (valid) {
                if (valid) {//验证通过
 
                    var result = ak.msService("kmdm","kmIntegratetAppCfgApi/createEsIndex",saveEsVue.saveEsModel);
                    if(null != result && result.code == 0){//操作成功
                    	//提示
                    	ak.info(result.msg);
                    	//关闭窗口
                    	saveEsVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    } 
                } 
            });
		},
		 
		//重置按钮
		saveReset : function(){
			this.$refs["saveEsModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	 this.saveEsModel.id = mainVue.handleEditId;
    }
});
//生成vue对象
var saveEsVue = new Vue(dialogAttr);