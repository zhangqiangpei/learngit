//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("sys","sysDictionaryApi/tree");
		//请求后台数据数据		this.treeSearch(null);
    	setTimeout(function (){
        	$("#dictionTree .el-tree").height($("#dictionTree .el-dialog--small").height() - $("#dictionTree .el-dialog__header").height()  - 50);
    	},100);
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#SysDictionaryTree',
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
			if(node.level != 1){
				ak.warning("只能选择一级字典节点！");
				return;
			}
			
			//先回调
			commonVue.SysDictionaryTreeCallBack(data);
            //再关闭窗口
			SysDictionaryTreeVue.dialogClose();
        }
	}	
});
//生成vue对象
var SysDictionaryTreeVue = ak.getMergeVue(dialogAttr,treeAttr);