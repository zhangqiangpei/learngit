//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#queryTable',
	//页面初始化事件
    mounted: function () {
    	//默认排序
    	var order = this.tableSearchModel.orders[0];
    	this.tableInitSort("output_name","asc");
    	//处理参数
    	this.tableSearchModel.taskId = mainVue.checkId;
    	//默认加载数据
    	this.tableInit("pm","PmTaskLogApi/listTable");
    	this.tableSearch(this.tableSearchModel);
    } 
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	pageSize : 99999//不分页
        },
        //选中id
        checkId : null
    },
    methods: {
        //单行双击事件
        queryDataClick : function(index, row){
        	this.checkId = row.id;
        	//先打开查看数据窗口
			ak.dialog("queryData","business/pm/PmTaskLog/PmTaskLogQueryData.html");
	        //本关闭窗口
			queryTableVue.dialogClose();
        }
    }
});

//生成vue对象
var queryTableVue = ak.getMergeVue(tableAttr,dialogAttr);