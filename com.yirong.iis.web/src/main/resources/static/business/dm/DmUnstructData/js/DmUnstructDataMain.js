//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            fileName : null
        },
        //自定义参数
        catalogName : null
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
        	this.tableSearchModel.fileName = null;
        	this.catalogName = null;
        },
        //目录选择器点击事件
        changeClick : function(){
        	commonVue.DmCatalogTreeCallBack = this.changeClickCallback;
        	commonVue.DmCatalogTreeParam = {
        			catalogAttr : "013002"
        	};
			ak.dialog("DmCatalogTree","common/DmCatalogTree.html");
        },
        //目录选择器回调事件
        changeClickCallback : function(data){
        	//处理参数
        	this.catalogName = data.name;
        	this.tableSearchModel.catalogId = data.id;
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化动态table
    	this.tableInit("pm","DmUnStructDataApi/list");
    	//默认排序
    	this.tableInitSort("file_name","asc");
    	//默认加载数据
    	this.tableSearch(this.tableSearchModel);
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);