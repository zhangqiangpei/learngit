//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            taskName : null,
            startDateStart : null,
            startDateEnd : null
        },
        //选择ID
        checkId : null
    },
    methods: {
    	//查询按钮
    	searchClick : function () {
    		//特殊处理日期参数
    		var dateStart = this.tableSearchModel.startDateStart;
    		if(ak.isNotNullOrEmpty(dateStart)){
    			this.tableSearchModel.startDateStart = ak.date.format('yyyy-MM-dd', dateStart);
    		}
    		var endStart = this.tableSearchModel.startDateEnd;
    		if(ak.isNotNullOrEmpty(endStart)){
    			this.tableSearchModel.startDateEnd = ak.date.format('yyyy-MM-dd', endStart);
    		}
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        //查看数据
        queryDataClick : function(index,row){
        	this.checkId = row.id;
        	//请求后台
        	ak.dialog("queryData","business/pm/PmTaskLog/PmTaskLogQueryData.html");
        },
        //运行结果
        resultFormatter : function(row, column){
        	if(row.is_success == 1){
        		return "成功";
        	}else if(row.is_success == 0 && ak.isNotNullOrEmpty(row.end_time)){
        		return "失败";
        	}else{
        		return "";
        	}
        	
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化动态table
    	this.tableInit("pm","PmTaskLogApi/list");
    	//默认排序
    	this.tableInitSort("start_time","desc");
    	//默认加载数据
    	this.searchClick();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);