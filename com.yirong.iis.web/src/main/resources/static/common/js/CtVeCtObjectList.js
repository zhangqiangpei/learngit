//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#CtVeCtObjectList',
	//页面初始化事件
    mounted: function () {
    	//初始化树
    	this.tableInit("ct","VeCtObjectApi/list");
    	//获取外面传入的参数
    	var param = commonVue.PmStandardListParam;
    	//与本选择器合并
    	param = $.extend(true,this.tableSearchModel,param);
    	//请求后台数据数据
    	this.tableSearch(this.tableSearchModel);
    	this.loadData();
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	ctObjectName : null,
        	ctChannel: null
        },
        ctChannels:null
    },
    methods: {
    	loadData : function(){
    		this.selectInit("ct");
        	this.ctChannels = this.selectSearch("010");
        },
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
			commonVue.CtVeCtObjectListCallBack(currentRow);
	        //再关闭窗口
			CtVeCtObjectListVue.dialogClose();
        }
    }
});

//生成vue对象
var CtVeCtObjectListVue = ak.getMergeVue(dialogAttr,selectAttr,tableAttr);