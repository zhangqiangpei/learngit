//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.treeInit("im","imJudgeCatalogApi/getTree",this.treeDataCallBack);
    	//请求后台数据数据
    	this.treeSearch(null);
    }
});

//获取tree属性
var treeAttr = getTreeMergeAttr({
	el: '#ImJudgeSpSingleTree',
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
			if(ak.isNotNullOrEmpty(data.id) && (data.atttibute.type == 1 || data.atttibute.type == 'judge')){	//非根节点且为目录
				//先回调
				commonVue.ImJudgeSpSingleTreeCallBack(data);
	            //再关闭窗口
				ImJudgeSpSingleTreeVue.dialogClose();
			}
        },
        //请求数据后回调函数
        treeDataCallBack : function(data){
        	//处理根路径
        	data.push({
        		id : "",
	    		name : "=================================目录===============================",
	    		pid : ""
        	});
        }
	}	
});
//生成vue对象
var ImJudgeSpSingleTreeVue = ak.getMergeVue(dialogAttr,treeAttr);