//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	elementName : null
        },
        //选中id
        checkId : null
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
        //修改按钮
        updateClick : function(index,row){
        	this.checkId = row.id;
        	ak.dialog("update","business/pm/PmElement/PmElementUpdate.html");
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("pm","PmElementApi/list");
    	//默认排序
    	this.tableInitSort("element_name","asc");
    	//请求后台
    	this.searchClick();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);