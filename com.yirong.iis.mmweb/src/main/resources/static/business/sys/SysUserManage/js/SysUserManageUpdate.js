//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
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
        //前端验证
        updateRules: {
           userName: [
               { required: true, message: '请输入用户名', trigger: 'blur' },
               { min: 1, max: 32, message: '长度在1到32个字符', trigger: 'blur' }
           ],
           userDisplayName: [
               {required: true ,message: '请输入中文名', trigger: 'blur' },
               {min: 1, max: 64, message: '长度在1到128个字符', trigger: 'blur' }
           ],
           /*organizationName: [
               {required: true ,message: '请选择所属机构', trigger: 'blur' }
           ],*/
           email: [
               {type: 'email' ,message: '请输入正确的格式', trigger: 'blur' }
           ]
        },
        //编辑操作id
        handleEditId : mainVue.handleEditId,
        post:null,
        organizationName: null
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("sys","userManageApi/update",updateVue.updateModel);                    
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
			this.updateModel.postIds = ids;
			this.post = post;
			
        }	
	},
	//页面初始化事件
    mounted: function () {
    	//初始化修改数据
    	var result = ak.msService("sys","userManageApi/getById",{ id : mainVue.handleEditId});
    	if(null != result && result.code == 0){//操作成功
    		//复制属性
    		this.updateModel = result.data;
    		
    	}
    	
    	//显示岗位信息
    	var postResult = ak.msService("sys","sysUserPostApi/getUserPost",{ userId : mainVue.handleEditId});
    	if(null != postResult && postResult.code == 0){//操作成功
    		var ids = [];
        	var post ='';
        	var dataList= postResult.data;
        	var j=0;
    		for(var i=0;i<dataList.length;i++){
    			var data = dataList[i];
				ids[j] = data.postId;
				post = post + data.name+",";
	            j++;
    		}
    		
    		if(post.length > 0){
    			post = post.substring(0,post.length-1);
			}
    			
    		this.updateModel.postIds = ids;
			this.post = post;
    	}
    	
    	//显示岗位信息
    	var orgResult = ak.msService("sys","sysUserOrgApi/getUserOrg",{ userId : mainVue.handleEditId});
    	if(null != orgResult && orgResult.code == 0){//操作成功
    		var organizationIds = [];
        	var organizationName ='';
        	var dataList= orgResult.data;
        	var j=0;
    		for(var i=0;i<dataList.length;i++){
    			var data = dataList[i];
				organizationName = organizationName + data.name+",";
				
				organizationIds[j] = data.orgId;
				j++;
    		}
    		
    		if(organizationName.length > 0){
				organizationName = organizationName.substring(0,organizationName.length-1);
			}
    		
			this.organizationName = organizationName;
			this.updateModel.organizationIds = organizationIds;
    	}
    	
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);