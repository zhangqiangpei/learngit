//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	tableData : null
    },
    methods: {
    	//更新内存
    	updateCache: function () {
    		//获取选定的事项
    		var ids = this.tableGetCheckIds();
    		var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length-1){
        			idsStr += ",";
        		}
        	});
        	var result = ak.msService("sys","cacheApi/update",{ids : idsStr});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
    	}
    }
});
 
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	this.tableData = [{
    		id:"sysResource",
    		name:"菜单"
    	},{
    		id:"sysOrganization",
    		name:"机构"
    	}]
    }
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);