//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:'',
			name : '',
			type : '',
			description : '',
			applicationId: null
        },
        //前端验证
        updateRules: {
        	applicationId: [
                { required: true, message: '请选择应用名称', trigger: 'blur' }
            ],
			name: [
                { required: true, message: '请输入角色名称', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到32个字符', trigger: 'blur' }
            ],
            type: [
                { required: true, message: '请选择角色类型', trigger: 'blur' }
            ],
	        description: [
                { max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ]
        },
        //自定义参数
		typeoptions : [],
		appoptions: [],
		rolePers:[]
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                	var param = updateVue.updateModel;
                	/*var permissionIds = '';
    	        	$(":checkbox:checked").each(function(){
    	        		var permissionId = $(this).val();
    				    permissionIds += permissionId + ',';
    				});
    	        	permissionIds = permissionIds.substring(0,permissionIds.length-1);
    	        	param.permissionIds = permissionIds;*/
    	        	
                	
                    var result = ak.msService("sys", "roleManageApi/update", param);                    
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
		ischeck : function(id,rolePers){
			if( $.inArray(id, rolePers) >-1){
				return true;
			}else{
				return false;
			}
		},
		//角色编辑-权限对象转id数组
		permissionToArray : function (list){
			/*var permissionIds = [];
			if(null != list){
				for(var i=0,len=list.length; i<len; i++){
					permissionIds.push(list[i].permissionId);
				} 
			}
			return permissionIds;*/
		},
		//应用名称下拉框变化
		appchange : function(applicationId){
			this.loadRes(applicationId,null);
		},
		//加载权限
		loadRes : function(applicationId,rolePers){
			//加载菜单
			/*var para = {
				applicationId:applicationId,
				loadPermission:'0'	
			}
			var resources = ak.msService("sys","resourceApi/getAllRes",para);
			
			if(null != resources){//操作失败
				var html = "";
				var data = resources.data;
				debugger;
				for(var k=0;k<data.length;k++){
					var bean = data[k];
					html += "<tr data-tt-id='"+bean.id+"' data-tt-parent-id='"+bean.parentId+"'>";
					html += "<td>"+bean.name+"</td>";
					
					var permissions = bean.permissions;
					for(var j=0;j<permissions.length;j++){
						var pms = permissions[j];
						if(this.ischeck(pms.id,rolePers)){
							html += "<td><input  type='checkbox' value='"+pms.id+"' name='"+pms.sn+"' checked='checked'>"+pms.name+"</input></td>";
						}else{
							html += "<td><input  type='checkbox' value='"+pms.id+"' name='"+pms.sn+"'>"+pms.name+"</input></td>";
						}
					}
				
					//补空td
					var len = 5 - permissions.length; 
					for(var i=0;i<len;i++){
						html += "<td></td>";
					}
					html += "</tr>";
				}

				$("#permissionTbup tbody").html(html);
				$('#permissionTbup').removeClass('hidden');
				$("#permissionTbup").treetable({  
			        expandable : true  
			    }); 
			} */
		}
	},
	//页面初始化事件
    mounted:function () {
    	//下拉框
    	this.typeoptions = mainVue.typeoptions;
    	this.appoptions =  mainVue.appoptions;
    	//获取信息
    	var result = ak.msService("sys","roleManageApi/getById",{ id : mainVue.handleEditId});
    	if(null != result && result.code == 0){//操作成功
    		var data = result.data;
    		this.updateModel = data;
    		//this.rolePers = this.permissionToArray(data.permissions);
    		
    		
    	}
    },
    updated : function () {
    	//updateVue.loadRes(updateVue.updateModel.applicationId,updateVue.rolePers);	
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);
 