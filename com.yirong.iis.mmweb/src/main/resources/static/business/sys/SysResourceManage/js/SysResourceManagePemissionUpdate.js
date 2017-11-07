//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#pmsupdate',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			resourceId: null,
			name: null,
			sn: null,
			description:null,
			code:null,
			url:null
        },
        //前端验证
        updateRules: {
        	name: [
                   { required: true, message: '请输入权限名称', trigger: 'blur' },
                   { min: 1, max: 64, message: '长度在1到 64', trigger: 'blur' }
              ],
          	sn: [
                  { required: true, message: '请输入授权编码', trigger: 'blur' },
                  { min: 1, max: 32, message: '长度在1到 32', trigger: 'blur' }
              ],
              url: [
                    { min: 1, max: 100, message: '长度在1到 100', trigger: 'blur' }
               ],
               code: [
                     { required: true, message: '请选择事件类型', trigger: 'blur' }
               ],      
              description: [
                   { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
              ] 
        },
        //自定义参数
        parentName :'',
        //自定义参数
        codeoptions :[]
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","resourceApi/upPermission",updateVue.updateModel);
                    if(null != result && result.code == 0){//操作成功
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
    	this.updateModel.id  = mainVue.handleEditId;
		//this.updateModel.resourceId = mainVue.tableSearchModel.resourceId;
    	this.codeoptions = mainVue.codeoptions;
    	//修改方法,加载修改信息
    	var result = ak.msService("sys","resourceApi/getPermissionById",{ id :  this.updateModel.id });
    	if(result.code == 0){//请求成功
    		this.updateModel = result.data;
    	}
 
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);