//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			paramKey : null,
			paramName : null,
			paramValue : null,
			descp: null 
		},
 
        //前端验证
		saveRules: {
			paramKey: [
                { required: true, message: '请输入参数代码', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ],
            paramName: [
                       { required: true, message: '请输入参数名称', trigger: 'blur' },
                       { min: 1, max: 128, message: '长度在1到128个字符', trigger: 'blur' }
            ],
            paramValue: [
                       { required: true, message: '请输入参数值', trigger: 'blur' },
                       { min: 1, max: 1024, message: '长度在1到1024个字符', trigger: 'blur' }
                   ],
        }
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","sysParameterApi/save",saveVue.saveModel);
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
		}
	},
	//页面初始化事件
    mounted: function () {
    	
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);