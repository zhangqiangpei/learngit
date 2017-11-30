//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			applicationId: null,
			parentId: null,
			name: null,
			sn: null,
			priority: 1,
			url: null,
			className: null,
			iconName:null 
        },
        //前端验证
        updateRules: {
        	name: [
                   { required: true, message: '请输入资源名称', trigger: 'blur' },
                   { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
              ],
        	sn: [
                 { required: true, message: '请输入授权编码', trigger: 'blur' },
                 { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
             ],
             url: [
                 { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
             ],
             className: [
                  { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
             ],
             iconName: [
                  { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
             ] 
        },
        //自定义参数
        parentName :''
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","resourceApi/update",updateVue.updateModel);
                    if(null != result && result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    	//刷新树
                    	mainVue.treeRefresh();
                    	//刷新菜单信息
                    	mainVue.loadMenu();
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
		this.updateModel.applicationId = mainVue.tableSearchModel.applicationId;
		this.updateModel.parentId = mainVue.tableSearchModel.resourceId;
		this.parentName = mainVue.resourceName;
	
    	//修改方法,加载修改信息
    	var result = ak.msService("sys","resourceApi/getById",{ id :  this.updateModel.parentId});
    	if(null != result && result.code == 0){//操作成功
    		this.updateModel = result.data;
    	}
 
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);