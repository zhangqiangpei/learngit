var mainAttr = {
    el:"#main",
    data:{
        tableSearchModel:{
            keywords:null,
        },
    },
    methods:{
        // 搜索报告
        searchReport:function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        }
    },
    mounted:function () {
        //外部链接传参
        try {
            var search = z.url().search;
            if (z.isNotNullOrEmpty(search)) {
                this.search.keywords = search;
            }
        } catch (e) {
        }
        this.tableInit("user","IisUserSearchApi/searchReports");
        this.tableInitSort("createTime", "desc");
        this.searchReport();
    }
};

// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);