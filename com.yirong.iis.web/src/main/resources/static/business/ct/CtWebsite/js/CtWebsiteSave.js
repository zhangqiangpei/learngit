//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			websiteAddress: null,
			websiteRemark:null,
			websiteGroupId:null,
			websiteName:null
        },
        //前端验证
        saveRules: {
            /*standardCode: [
                { required: true, message: '请输入标准编码', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
            ],*/
        	websiteName: [
                { required: true, message: '请输入网址中文名称', trigger: 'blur' },
                { min: 1, max: 40, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ],
        	websiteAddress: [
                { required: true, message: '请输入网址', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
        parentName : $("#parentName").val()
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                	var reg=/(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
                	if(reg.test(saveVue.saveModel.websiteAddress)){
                		var result = ak.msService("ct","CtWebsiteApi/save",saveVue.saveModel);
                        if(result.code == 0){//操作成功
                        	//提示
                        	ak.success(result.msg);
                        	//关闭窗口
                        	saveVue.dialogClose();
                        	//刷新表格
                        	CtWebsiteMainVue.tableRefresh();
                        }else{
                        	ak.warning(result.msg);
                        }
                	}else{
                		ak.warning("请输入http开头的符合规范的完整地址！");
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
    	//ID作为传递参数，所以放在model中
    	this.saveModel.websiteGroupId = CtWebsiteMainVue.tableSearchModel.websiteGroupId;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);