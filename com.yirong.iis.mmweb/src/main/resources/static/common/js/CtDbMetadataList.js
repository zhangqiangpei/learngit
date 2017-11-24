//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#CtDbMetadataList',
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.tableInit("ct","CtDbMetadataApi/list");
    	//获取外面传入的参数
    	var param = commonVue.PmStandardListParam;
    	//与本选择器合并
    	param = $.extend(true,this.tableSearchModel,param);
    	//请求后台数据数据
    	this.tableSearch(this.tableSearchModel);
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	dbMetadataName : null
        },
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
    		// 当前页切换成第一页
            param.page = 1;
           	//查询后台
            this.tableSearch(param);
        },
        //单行选中按钮
        cellDblclick : function(currentRow, oldCurrentRow){
        	//先回调
			commonVue.CtDbMetadataListCallBack(currentRow);
	        //再关闭窗口
			CtDbMetadataListVue.dialogClose();
        }
    }
});

//生成vue对象
var CtDbMetadataListVue = ak.getMergeVue(dialogAttr,tableAttr);