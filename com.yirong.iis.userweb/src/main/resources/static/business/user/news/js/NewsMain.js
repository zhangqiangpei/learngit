var mainAttr={
    el:"#main",
    data:{
        //查询对象
        tableSearchModel: {},
        searchCondition:{
            continent:null,
            country:null
        },
        continents:[],
        countries:[]
    },
    methods:{
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        }
    },
    mounted:function () {
        //初始化table
        this.tableInit("user", "IisReportApi/list");
        //默认排序
        this.tableInitSort("createTime", "desc");
        this.searchClick();
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);