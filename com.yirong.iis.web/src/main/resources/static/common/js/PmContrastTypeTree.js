//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#PmContrastTypeTree',
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("pm","PmContrastApi/treeType");
    	//请求后台数据数据
    	this.treeSearch(commonVue.PmContrastTypeTreeParam);
    	//处理根路径
    	this.treeData = [{
    		id : "",
    		name : "===================标准分类=====================",
    		pid : "",
    		children : this.treeData
    	}];
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
			if(ak.isNotNullOrEmpty(data.id)){	//非根节点
				//先回调
				commonVue.PmContrastTypeTreeCallBack(data);
	            //再关闭窗口
				PmContrastTypeTreeVue.dialogClose();
			}
        }
	}	
});
//生成vue对象
var PmContrastTypeTreeVue = ak.getMergeVue(dialogAttr,treeAttr);