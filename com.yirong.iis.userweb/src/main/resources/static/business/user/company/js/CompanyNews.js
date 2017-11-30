var mainAttr={
	el:"#main",
	data:{
	    //查询对象
	    tableSearchModel: {
    		keywords:'',
            type:''
    	} 
    },
    methods: {
    	searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            param.pageSize=5;

            //查询后台
            this.tableSearch(param);
    	},
    	detailClick : function (row){
    		var url = "/forward.do?viewPath=business/user/news/NewsDetail.html&esId=" + row.id
    		window.open(url);
    	}
    },
    mounted: function() {
    	this.tableSearchModel.companyId= parent.mainVue.$data.companyId;
        //默认排序

        this.tableInit("user", "IisEsSearchApi/searchNews");
        //默认排序
        this.tableInitSort("RELEASE_TIME", "desc");
        this.searchClick();
    }
};
//添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);

