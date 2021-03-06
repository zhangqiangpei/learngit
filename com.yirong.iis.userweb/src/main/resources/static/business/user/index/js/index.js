var mainVue = new Vue({
    el :"#main",
    data :{
        typeData:null,
        search:null,
    },
    methods:{
        // 更多
        moreTypeData: function (item) {
            window.open("forward.do?viewPath=business/user/report/MoreReport.html&typeId=" + item[0].TYPE_ID);
        },
        // 搜索报告
        searchReport:function () {
            window.open("forward.do?viewPath=business/user/report/searchResult.html&search=" +this.search)
        }
    },
    mounted:function () {
        var result = z.msService("user","IisReportTypeApi/listFiveRecord",{typeId:""});
        if (result.code === 0){
            this.typeData = result.data;
        }
    }
});