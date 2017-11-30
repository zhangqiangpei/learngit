//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	taskName : null,
        	ctChannel:null,
        	ctWay:null,
        	ctObjectName:null,
        	orders : [{
    			orderField : "tl.create_time",
    			orderType : "desc"
    		}],
        },
        catalogNameMain:null,
		ctChannels:null,
		ctWays:null
    },
    methods: {
    	loadData : function(){
    		this.selectInit("ct");
        	this.ctChannels = this.selectSearch("010");
        	this.ctWays=this.selectSearch("011");
        },
    	//查询按钮
    	searchClick: function () {
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
        	ak.dialog("queryData","business/ct/CtTaskLog/html/CtTaskLogQueryData.html");
        },
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("ct","CtTaskLogApi/list");
    	//默认排序
    	/*this.tableInitSort("create_time","desc");*/
    	//请求后台
    	this.searchClick();
    	this.loadData();
    }
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);