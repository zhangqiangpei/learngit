//获取dialog属性
var dialogAttr = getDialogMergeAttr({});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        //查询对象
        tableSearchModel: {
            fieldName: ""
        },
        countryInfo: null,
        displomaticSituationInfo : null,
        flag: null,
        situation: null,
        activeName: 'basic'
    },
    methods: {
        loadCountryInfo : function () {
            var result = ak.msService("mm", "IisCountryInfoApi/get", {id: mainVue.id});
            if (result.code === 0) {
                this.countryInfo = result.data;
            }
        },
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
                    var result = ak.msService("mm","IisDiplomaticSituationApi/list",{englishName :  mainVue.id});
                    if (result.code === 0){
                        this.displomaticSituationInfo = result.data.data;
                    }
                    break;
            }
        },
        updateCountry :function () {
            ak.dialog("update","business/iis/IisCountryInfo/IisCountryInfoUpdate.html");
        },
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.page = 1;
            //查询后台
            this.tableSearch(param);
        },
        resetClick: function () {
            this.tableResetCondition();
        },
        //删除按钮
        delClick: function () {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            if (ids.length === 0) {
                ak.warning("请选择需要删除的概况字段");
            } else {
                ak.confirm("确定要删除选中的" + ids.length + "个概况字段吗？", this.delCallback);
            }
        },
        //确认删除的回调函数
        delCallback: function (instance) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            var result = ak.msService("mm","IisCountrySurveyApi/delete",{ids : ids});
            if (result.code === 0){
                ak.success(result.msg);
                detailVue.tableRefresh();
            }
        },
        saveSurvey : function () {
            this.countryEngName =  mainVue.id;
            ak.dialog("save","business/iis/IisCountrySurvey/IisCountrySurveySave.html");
        },
        updateSurvey : function (row) {
            this.updateSurveyId =  row.id;
            ak.dialog("update","business/iis/IisCountrySurvey/IisCountrySurveyUpdate.html");
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

        this.loadCountryInfo();
        var result = ak.msService("mm", "IisCountryNationalFlagApi/get", {engName: mainVue.id});
        if (result.code === 0) {
            if (ak.isNotNullOrEmpty(result.data))
                this.flag = result.data;
        }
    }
};

//生成vue对象
var detailVue = ak.getMergeVue(mainAttr, tableAttr, dialogAttr);