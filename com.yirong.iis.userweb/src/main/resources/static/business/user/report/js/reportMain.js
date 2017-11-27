var mainAttr={
    el:"#main",
    data:{
        insiderReportList:[],
        externalReportList:[],
        categoryReports:[],
        hasInsiderReport:false,
        hasExternalReport:false,
        search:""
    },
    methods:{
        // 搜索报告
        searchReport:function () {
            z.msServiceAsync("user","IisReportTypeApi/listThreeRecord",{reportName:mainVue.search},function(result){
                if (result.code === 0){
                    mainVue.insiderReportList = [];
                    mainVue.externalReportList=[];
                    for (var i = 0; i<result.data.length; i++){
                        if (result.data[i].IS_OUTSIDE === 0){
                            mainVue.hasInsiderReport = true;
                            mainVue.insiderReportList.push(result.data[i]);
                        } else {
                            mainVue.hasExternalReport = true;
                            mainVue.externalReportList.push(result.data[i]);
                        }
                    }
                }
            });
        }
    },
    mounted:function () {
        z.msServiceAsync("user","IisReportTypeApi/listThreeRecord",{typeId:""},function (result) {
            if (result.code === 0){
                mainVue.list = result.data;
                for (var i = 0; i<result.data.length; i++){
                    if (result.data[i].IS_OUTSIDE === 0){
                        mainVue.hasInsiderReport = true;
                        mainVue.insiderReportList.push(result.data[i]);
                    } else {
                        mainVue.hasExternalReport = true;
                        mainVue.externalReportList.push(result.data[i]);
                    }
                }
            }
        });
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