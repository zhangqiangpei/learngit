//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	data: {
		isIndeterminate:true,
		checkAll:true,
		checkedMode:[],
		modeOptions:['显示', '新增', '修改', '删除'],
		checkedList:[]
	},
    methods: {
        handleCheckAllChange:function(event) {
          this.checkedMode = event.target.checked ? this.modeOptions : [];
          this.isIndeterminate = false;
          
          if(event.target.checked){//全选
        	  var nodes = this.$refs.tree.data;//获取全部的第一个节点
        	  var checkList_arr = [];
        	  for(var i=0;i<nodes.length;i++){
        		  var node = nodes[i];
        		  checkList_arr.push(node.id);
        	  }
        	  this.$refs.tree.setCheckedKeys(checkList_arr);
          }else{//取消
        	  this.$refs.tree.setCheckedKeys([]);
          }
        },
        handleCheckedModeChange:function(value) {
          var checkedCount = value.length;
          this.checkAll = checkedCount === this.modeOptions.length;
          this.isIndeterminate = checkedCount > 0 && checkedCount < this.modeOptions.length;
        },
        modeChange:function(event){
        	debugger;
        	var key = event.currentTarget.defaultValue;
        	var checked = event.currentTarget.checked;
        	var code = "";
        	switch(key){
        		case '显示':
        		  code = "007003";
	        	  break;
        		case '新增':
        		  code = "007001";
	        	  break;
        		case '修改':
        	      code = "007002";
  	        	  break;
        		case '删除':
        		  code = "007004";  
        		  break;
        		default:
        		  code = "007003";
        	}
        	
        	if(checked){//选中
        		//var checkList_arr = [];
        		for(var i=0;i<SysPermissionTreeVue.treeDataBF.length;i++){
        			var node = SysPermissionTreeVue.treeDataBF[i];
        			if(node.code == code){
        				this.$refs.tree.setChecked(node.id,true,false);
        			}
        		}
        		//this.$refs.tree.setCheckedKeys(checkList_arr);
        	}else{
        		for(var i=0;i<SysPermissionTreeVue.treeDataBF.length;i++){
        			var node = SysPermissionTreeVue.treeDataBF[i];
        			if(node.code == code){
        				this.$refs.tree.setChecked(node.id,false,false);
        			}
        		}
        	}
        	
        }
     },
     created:function (){
    	//初始化树
     	var param =  {applicationId : mainVue.applicationId};
     	this.treeInit("sys", "sysPermissionApi/getAllPer",this.treeDataCallBack);
     	this.treeSearch(param);
    },
	//页面初始化事件
    mounted: function () {
    	//获取角色初始权限
    	var rolePerData = ak.msService('sys','sysPermissionApi/getByRole',{id : mainVue.handleEditId});
    	if(null != rolePerData && rolePerData.code == 0){//操作成功
    		this.checkedList = [];
			var rolePerList = rolePerData.data;
			for(var i=0;i<rolePerList.length;i++){
				this.checkedList.push(rolePerList[i].permissionId,true,false);//设置节点选中
	    	}
    	}
    	
    	setTimeout(function(){
    		$("#permissionTree .el-tree").height($("#permissionTree .el-dialog--small").height() 
    				- $("#permissionTree .el-dialog__header").height()
    				- $("#permissionTree .dialog-footer").height() 
    				- $("#permissionTree .el-checkbox").height()
    				- $("#permissionTree .el-checkbox-group").height() -80);
    	},100);
    	
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#SysPermissionTree',
	methods : {
		treeSubmit : function(){
			var checkedNodes = this.$refs.tree.getCheckedNodes();
			var ids = [];
			for(var i=0;i<checkedNodes.length;i++){
				var node = checkedNodes[i];
				if('0' != node.code){
					ids.push(node.id);
				}
			}
			
			if(ids.length < 1){
				ak.warning("请选择权限节点！");
				return;
			}
			
			//先回调
			mainVue.SysPermissionTreeCallBack(ids);
            //再关闭窗口
			SysPermissionTreeVue.dialogClose();
		} 
	}
});

//生成vue对象
var SysPermissionTreeVue = ak.getMergeVue(dialogAttr,treeAttr);