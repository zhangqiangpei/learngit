//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	var param = commonVue.SysOrgUserTreeParam;
    	this.treeInit("sys", "organizationApi/getOrgUserTree",this.treeDataCallBack);
    	this.treeSearch(param);
    	
    	setTimeout(function(){
    		$("#userTree .el-tree").height($("#userTree .el-dialog--small").height() - $("#userTree .el-dialog__header").height() - $("#userTree .dialog-footer").height() -80);
    	},100);
    	
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#SysOrgUserTree',
	methods : {
		treeSubmit : function(){
			var checkedNodes = this.$refs.tree.getCheckedNodes();
			var ids = this.$refs.tree.getCheckedKeys();
			
			//先回调
			commonVue.SysOrgUserTreeCallBack(checkedNodes,ids);
            //再关闭窗口
			SysOrgUserTreeVue.dialogClose();
		} 
	}	
});
//生成vue对象
var SysOrgUserTreeVue = ak.getMergeVue(dialogAttr,treeAttr);