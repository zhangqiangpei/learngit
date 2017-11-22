//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#inputDb',
	//页面初始化事件
    mounted: function () {
    	this.tableSearchModel.ctObjectId = checkObjectId;
    	this.tableInit("ct","CtEsFieldApi/list");
    	this.tableSearch(this.tableSearchModel);
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	pageSize : 99999 //较大值，不分页
        }
    }  
});

//生成vue对象
var inputDbVue = ak.getMergeVue(dialogAttr,tableAttr);