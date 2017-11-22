//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#ImCatalogExImList',
	//页面初始化事件
    mounted: function () {
    	//初始化表格
    	this.tableInit("im","imCatalogApi/queryExImList");
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
        	catalogName : null,
        	catalogAttr :"1"
        }
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
			commonVue.ImCatalogListCallBack(currentRow);
	        //再关闭窗口
			ImCatalogListVue.dialogClose();
        },
        formatApp:function(row, column){
        	 if (row[column.property] != null) {
                return  AppTpeObj[row[column.property]];
            }else{
            	return "无";
            }
        }
    }
});

//生成vue对象
var ImCatalogListVue = ak.getMergeVue(dialogAttr,tableAttr);