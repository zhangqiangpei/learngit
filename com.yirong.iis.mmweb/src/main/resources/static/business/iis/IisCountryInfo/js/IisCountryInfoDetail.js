//获取dialog属性
var dialogAttr = getDialogMergeAttr({});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        countryInfo: null,
        flag: null,
        situation: null,
        activeName: 'basic'
    },
    methods: {
        tabClick: function (tab) {
            switch (tab.index) {
                case '0'://基本信息
                    break;
                case '1'://概况信息
                    var param = this.tableSearchModel;
                    // 当前页切换成第一页
                    param.currentPage = 1;
                    param.countryEngName = this.countryInfo.ENGLISH_NAME;
                    //查询后台
                    this.tableSearch(param);
                    break;
                case '2'://外交信息
                    break;
            }
        }
    }
});

//主属性
var mainAttr = {
    el: '#detail',
    //页面初始化事件
    mounted: function () {
        //初始化table
        this.tableInit("mm", "IisCountrySurveyApi/list");
        //默认排序
        this.tableInitSort("create_time", "desc");

        var result = ak.msService("mm", "IisCountryInfoApi/get", {id: mainVue.id});
        if (result.code === 0) {
            this.countryInfo = result.data;
        }
    }
};

//生成vue对象
var detailVue = ak.getMergeVue(mainAttr, tableAttr, dialogAttr);