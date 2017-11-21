var mainAttr={
    el:"#main",
    data:{
        typeId:null,
        reportTypeTreeData:null,
        defaultProps: {
            label: 'TYPENAME'
        },
        reportData:[],
        //查询对象
        tableSearchModel: {},
        searchData:null
    },
    methods:{
        // 点击报告分类树
        reportTypeNodeClick:function(data) {
            this.tableSearchModel.typeId = data.ID;
            this.searchClick();
        },
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        },
        // 在线浏览
        onLineView:function (row) {
            window.open("forward.do?viewPath=business/user/online/onlineMain.html&kmId=" + row.KMID+"&id=" + row.ID);

        }
    },
    mounted:function () {
        //外部链接传参
        try {
            if (z.isNotNullOrEmpty(z.url().typeId)) {
                this.tableSearchModel.typeId = z.url().typeId;
            }
        } catch (e) {
        }
        var result;
        // 初始化报告分类
        result = z.msService("user","IisReportTypeApi/list",{typeName: "", isOutside: 0});
        if(result.code === 0){
            this.reportTypeTreeData = result.data;
        }
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