//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	ctObjectName : null
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
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length < 1){
        		ak.warning("请选择记录进行配置");
        	}else{
        		ak.dialog("update","business/dm/DmCatalogObject/DmCatalogObjectUpdate.html");
        	}
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("pm","DmCatalogObjectApi/list");
    	//默认排序
    	this.tableInitSort("ct_object_name","asc");
    	//请求后台
    	this.searchClick();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);