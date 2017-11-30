var mainAttr={
	el:"#main",
	data:{
	    //查询对象
	    tableSearchModel: {
	    	companyId:''
    	} 
    },
    methods: {
    	searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            param.pageSize=10;

            //查询后台
            this.tableSearch(param);
    	} 
    },
    mounted: function() {
    	this.tableSearchModel.companyId= parent.mainVue.$data.companyId;
        //默认排序

        this.tableInit("user", "IisCompanyOfficerApi/list");
        //默认排序
        this.tableInitSort("createTime", "desc");
        this.searchClick();
    }
};
//添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);

