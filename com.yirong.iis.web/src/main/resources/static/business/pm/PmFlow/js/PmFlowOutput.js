//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#output',
	//页面初始化事件
    mounted: function () {
    	//获取输入信息
    	var inField = objectField[checkId].inField;
    	var dataTemp = new Array;
    	//循环装入列表对象
    	$.each(inField,function(i,n){
    		dataTemp.push(n);
    	});
    	this.tableData = dataTemp;
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
});

//生成vue对象
var outputVue = ak.getMergeVue(dialogAttr,tableAttr);