//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#PmStandardTypeTree',
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("pm","PmStandardApi/treeType");
    	//请求后台数据数据
    	this.treeSearch(null);
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
			if(ak.isNotNullOrEmpty(data.id)){	//非根节点
				//先回调
				commonVue.PmStandardTypeTreeCallBack(data);
	            //再关闭窗口
				PmStandardTypeTreeVue.dialogClose();
			}
        }
	}	
});
//生成vue对象
var PmStandardTypeTreeVue = ak.getMergeVue(dialogAttr,treeAttr);