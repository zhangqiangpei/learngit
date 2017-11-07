//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModel: {
			userName : null,
			userDisplayName : null,
			organizationIds : null,
			
			phone: null,
			email: null,
			userAddress: null,
			userStatus: null,
			duties:null,
			
			officePhone:null,
			postIds:null
		},
		organizationName: null,
		post:null,
        //前端验证
		saveRules: {
			userName: [
                { required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到32个字符', trigger: 'blur' }
            ],
            userDisplayName: [
                {required: true ,message: '请输入中文名', trigger: 'blur' },
                {min: 1, max: 64, message: '长度在1到128个字符', trigger: 'blur' }
            ],
            organizationIds: [
                {required: true ,message: '请选择所属机构', trigger: 'blur' }
            ] ,
            email: [
                {type: 'email' ,message: '请输入正确的格式', trigger: 'blur' }
            ]
        } 
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","userManageApi/create",saveVue.saveModel);
                    if(null != result && result.code == 0){//操作成功
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
		},
		//树选择器点击事件
        changeClick : function(){
        	commonVue.SysOrgTreeCallBack = this.changeClickCallback;
			ak.dialog("SysOrgTree","common/SysOrgTree.html");
        },
        //树选择器回调事件
        changeClickCallback : function(checkedNodes){
        	var ids = [];
        	var organizationName ='';
			var j = 0;
			for(var i=0;i<checkedNodes.length;i++){
				var data = checkedNodes[i];
				ids[j] = data.id;
				organizationName = organizationName + data.name+",";
	            j++;
			}
        	
			if(organizationName.length > 0){
				organizationName = organizationName.substring(0,organizationName.length-1);
			}
			
        	this.saveModel.organizationIds = ids;
        	this.organizationName = organizationName;
        },
        
      //树选择器点击事件
        changePostClick : function(){
        	commonVue.SysPostTreeCallBack = this.changePostClickCallback;
			ak.dialog("SysPostTree","common/SysPostTree.html");
        },
        //树选择器回调事件
        changePostClickCallback : function(checkedNodes){
        	var ids = [];
        	var post ='';
			var j = 0;
			for(var i=0;i<checkedNodes.length;i++){
				var data = checkedNodes[i];
				ids[j] = data.id;
				post = post + data.name+",";
	            j++;
			}
			
			if(post.length > 0){
				post = post.substring(0,post.length-1);
			}
			
        	//处理参数
        	this.saveModel.postIds = ids;
        	this.post = post;
        }
	},
	//页面初始化事件
    mounted: function () {
    	 
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);