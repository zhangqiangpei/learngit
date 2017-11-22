//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		integratetAppId : null,
    		keyword : null,
    		uploadStartTm : null,
    		uploadEndTm : null,
			catalog : null,
			knowledge:  null
        },
        myScroll:'',
        appsOptions : []
    },
    methods: {
    	//查询按钮
    	onSearch: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        	setTimeout(function(){mainVue.myScroll.refresh();},200);
        },
        //高级搜索
        advancedSearch : function (){
        	ak.dialog("advanced","business/km/KmTextSearch/KmTextSearchAdvanced.html");
        }
        
    }
});
 
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	this.tableInit("kmdm","kmTextSearchApi/textSearch");
    	//查询后台
    	//this.onSearch();
    	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/getAllApps",null);
    	if(null != result && result.code == 0){//操作成功
    		this.appsOptions = result.data;
        } 
    	
        // 左侧菜单加上滚动条
    	this.myScroll   =  new IScroll('#divcontent', {
        	scrollbars: true,
            mouseWheel: true,
            interactiveScrollbars: true,        // 是否可拖动滚动条，配合fadeScrollbars使用
            fadeScrollbars: true,              // 是否自动隐藏显示滚动条
            shrinkScrollbars: 'scale',
            resizeScrollbars:true
        });
    	 
       
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);