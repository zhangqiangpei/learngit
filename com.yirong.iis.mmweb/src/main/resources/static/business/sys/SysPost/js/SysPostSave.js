//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			name : null
		},
 
        //前端验证
		saveRules: {
			name: [
                { required: true, message: '请输入岗位名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1到64个字符', trigger: 'blur' }
            ]   
        }
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","sysPostApi/save",saveVue.saveModel);
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