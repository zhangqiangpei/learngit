//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("sys","sysPostApi/getPostTree",this.treeDataCallBack);
    	//请求后台数据数据
    	this.treeSearch({code:''});
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#SysPostTree',
	methods : {
		treeSubmit : function(){
			var checkedNodes = this.$refs.tree.getCheckedNodes();
			//先回调
			commonVue.SysPostTreeCallBack(checkedNodes);
            //再关闭窗口
			SysPostTreeVue.dialogClose();
		} 
	}	
});
//生成vue对象
var SysPostTreeVue = ak.getMergeVue(dialogAttr,treeAttr);