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
        displomaticSituationInfo: [],
        flag: null,
        situation: null,
        activeName: 'basic'
    },
    methods: {
        loadCountryInfo: function () {
            var result = ak.msService("mm", "IisCountryInfoApi/get", {id: mainVue.id});
            if (result.code === 0) {
                this.countryInfo = result.data;
            }
        },
        tabClick: function (tab) {
            // 初始化查询条件
            var param = this.tableSearchModel;
            param.fieldName = "";

            switch (tab.name) {
                case 'basic'://基本信息
                    break;
                case 'survey'://概况信息
                    this.tableInit("mm", "IisCountrySurveyApi/list");
                    // 当前页切换成第一页
                    param.currentPage = 1;
                    param.countryEngName = this.countryInfo.ENGLISH_NAME;
                    //查询后台
                    this.tableSearch(param);
                    break;
                case 'political'://概况信息
                    this.tableInit("mm", "IisPoliticalEnvironmentApi/list");
                    // 当前页切换成第一页
                    param.currentPage = 1;
                    param.countryEngName = this.countryInfo.ENGLISH_NAME;
                    //查询后台
                    this.tableSearch(param);
                    break;
                case 'situation'://外交信息
                    var result = ak.msService("mm", "IisDiplomaticSituationApi/list", {englishName: mainVue.id});
                    if (result.code === 0) {
                        this.displomaticSituationInfo = result.data.data;
                    }
                    break;
            }
        },
        updateCountry: function () {
            ak.dialog("update", "business/iis/IisCountryInfo/IisCountryInfoUpdate.html");
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
        delClick: function (obj) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            if (ids.length === 0) {
                if (obj === 'survey') {
                    ak.warning("请选择需要删除的概况字段");
                } else if (obj === 'political') {
                    ak.warning("请选择需要删除的政治字段");
                }
            } else {
                if (obj === 'survey') {
                    ak.confirm("确定要删除选中的" + ids.length + "个概况字段吗？", this.delSurveyCallback);
                } else if (obj === 'political') {
                    ak.confirm("确定要删除选中的" + ids.length + "个政治字段吗？", this.delPoliticalCallback);
                }
            }
        },
        //确认删除的回调函数
        delSurveyCallback: function (instance) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            var result = ak.msService("mm", "IisCountrySurveyApi/delete", {ids: ids});
            if (result.code === 0) {
                ak.success(result.msg);
                detailVue.tableRefresh();
            }
        },
        //确认删除的回调函数
        delPoliticalCallback: function (instance) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            var result = ak.msService("mm", "IisPoliticalEnvironmentApi/delete", {ids: ids});
            if (result.code === 0) {
                ak.success(result.msg);
                detailVue.tableRefresh();
            }
        },
        saveSurvey: function (obj) {
            this.countryEngName = mainVue.id;
            if (obj === 'survey') {
                ak.dialog("save", "business/iis/IisCountrySurvey/IisCountrySurveySave.html");
            } else if (obj === 'political') {
                ak.dialog("save", "business/iis/IisPoliticalEnvironment/IisPoliticalEnvironmentSave.html");
            }

        },
        updateSurvey: function (row, obj) {
            this.updateSurveyId = row.id;
            if (obj === 'survey') {
                ak.dialog("update", "business/iis/IisCountrySurvey/IisCountrySurveyUpdate.html");
            } else if (obj === 'political') {
                ak.dialog("update", "business/iis/IisPoliticalEnvironment/IisPoliticalEnvironmentUpdate.html");
            }
        },
        editSituation: function (oper) {
            if (oper === 'save')
                top.vm.url = "/forward.do?viewPath=business/iis/IisDiplomaticSituation/IisDiplomaticSituationSave.html&engName=" + mainVue.id + "&chnName=" + this.countryInfo.chineseName;
            else if (oper === 'update')
                top.vm.url = "/forward.do?viewPath=business/iis/IisDiplomaticSituation/IisDiplomaticSituationUpdate.html&engName=" + mainVue.id + "&chnName=" + this.countryInfo.chineseName;
            else
                ak.confirm("确定要删除外交信息吗？", this.delSurveyCallback);
        },
        delSituationCallback : function (instance) {
            var result = ak.msService("mm", "IisDiplomaticSituationApi/delete", {ids: this.displomaticSituationInfo[0].id});
            if (result.code === 0) {
                this.displomaticSituationInfo = [];
            }
        }
    }
});

//主属性
var mainAttr = {
    el: '#detail',
    //页面初始化事件
    mounted: function () {
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