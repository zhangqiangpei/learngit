//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            flowName : null
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
        //重置按钮
        psdTableResetCondition : function(){
        	this.tableSearchModel.catalogId = null;
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length < 1){
        		ak.warning("请选择记录进行配置");
        	}else{
        		ak.dialog("update","business/dm/DmCatalogFlow/DmCatalogFlowUpdate.html");
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
    	this.tableInit("pm","DmCatalogFlowApi/list");
    	//默认排序
    	this.tableInitSort("flow_name","asc");
    	//默认加载数据
    	this.tableSearch(this.tableSearchModel);
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);