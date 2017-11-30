var mainAttr={
    el:"#main",
    data:{
        typeId:null,
        reportTypeTreeData:[
            {ID:"123",TYPENAME:"内部报告", ISOUTSIDE:0,children:[]},
            {ID:"456",TYPENAME:"外部报告", ISOUTSIDE:1,children:[]}
        ],
        defaultProps: {
            label: 'TYPENAME',
            children: 'children'
        },
        reportData:[],
        //查询对象
        tableSearchModel: {
            isOutside:"",
            reportName:null
        },
        searchData:null
    },
    methods:{
        // 点击报告分类树
        reportTypeNodeClick:function(data) {
            if (typeof (data.children) === 'undefined'){
                this.tableSearchModel.isOutside = "";
                this.tableSearchModel.typeId = data.ID;
                this.searchClick();
            } else {
                this.tableSearchModel.typeId = "";
                this.tableSearchModel.isOutside = data.ISOUTSIDE;
                this.searchClick();
            }
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
            window.open("forward.do?viewPath=business/user/report/onlineMain.html&kmId=" + row.KMID+"&id=" + row.ID+"&reportName="+row.REPORTNAME);
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
        result = z.msService("user","IisReportTypeApi/list",{typeName: ""});
        if(result.code === 0){
            // this.reportTypeTreeData = result.data;
            var interior=[];
            var external=[];
            for (var i = 0; i < result.data.length; i++){
                if (result.data[i].ISOUTSIDE === 0){
                    interior.push(result.data[i]);
                } else {
                    external.push(result.data[i]);
                }
            }
            this.reportTypeTreeData[0].children = interior;
            this.reportTypeTreeData[1].children = external;
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