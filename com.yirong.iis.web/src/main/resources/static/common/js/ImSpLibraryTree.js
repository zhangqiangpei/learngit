//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("im","imSpLibraryApi/getSpLibray",this.treeDataCallBack);
    	//请求后台数据数据
    	this.treeSearch({ type : "collection" });
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#ImSpLibraryTree',
	methods : {
		treeSubmit : function(){
			 
			var checkedNodes = this.$refs.tree.getCheckedNodes();
			//先回调
			commonVue.ImSpLibraryTreeCallBack(checkedNodes);
            //再关闭窗口
			ImSpLibraryTreeVue.dialogClose();
		} 
		/*//树节点单击事件
		treeNodeClick: function (data, node, el) {
			if(ak.isNotNullOrEmpty(data.id) && data.atttibute.type == 1){	//非根节点且为目录
				//先回调
				commonVue.ImSpLibraryTreeCallBack(data);
	            //再关闭窗口
				ImSpLibraryTreeVue.dialogClose();
			}
        },*/
        /*//请求数据后回调函数
        treeDataCallBack : function(data){
        	//处理根路径
        	data.push({
        		id : "",
	    		name : "=================================目录===============================",
	    		pid : ""
        	});
        }*/
	}	
});
//生成vue对象
var ImSpLibraryTreeVue = ak.getMergeVue(dialogAttr,treeAttr);