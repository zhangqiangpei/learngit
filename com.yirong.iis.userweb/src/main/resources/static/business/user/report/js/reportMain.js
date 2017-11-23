var mainAttr={
    el:"#main",
    data:{
        insiderReportList:[],
        externalReportList:[],
        categoryReports:[],
        hasInsiderReport:false,
        hasExternalReport:false
    },
    methods:{
        // 搜索报告
        searchReport:function () {
            window.open("forward.do?viewPath=business/user/report/searchResult.html&search=" +this.searchData);
        }
    },
    mounted:function () {
        var result = z.msService("user","IisReportTypeApi/listThreeRecord",{typeId:""});
        if (result.code === 0){
            this.list = result.data;
            for (var i = 0; i<result.data.length; i++){
                if (result.data[i].IS_OUTSIDE === 0){
                    this.hasInsiderReport = true;
                    this.insiderReportList.push(result.data[i]);
                } else {
                    this.hasExternalReport = true;
                    this.externalReportList.push(result.data[i]);
                }
            }
        }
        //外部链接传参
        try {
            if (z.isNotNullOrEmpty(z.url().typeId)) {
                this.tableSearchModel.typeId = z.url().typeId;
            }
        } catch (e) {
        }
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);