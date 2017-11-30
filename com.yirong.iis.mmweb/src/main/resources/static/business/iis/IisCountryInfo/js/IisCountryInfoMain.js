var utilAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        //查询对象
        tableSearchModel: {
            iso2code: "",
            englishName: "",
            chineseName: "",
            continentCode: ""
        },
        continentCodeList: [],
        id : null
    },
    methods: {
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
        //新增按钮
        addClick: function () {
            ak.dialog("save", "business/iis/IisCountryInfo/IisCountryInfoSave.html");
        },
        //删除按钮
        delClick: function () {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            if (ids.length === 0) {
                ak.warning("请选择需要删除的国家");
            } else {
                ak.confirm("确定要删除选中的" + ids.length + "个国家吗？", this.delCallback);
            }
        },
        //确认删除的回调函数
        delCallback: function (instance) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            var result = ak.msService("mm","IisCountryInfoApi/delete",{ids : ids});
            if (result.code === 0){
                ak.success(result.msg);
            }
        },
        detailClick : function (row) {
            this.id = row.ENGLISH_NAME;
            ak.dialog("detail", "business/iis/IisCountryInfo/IisCountryInfoDetail.html");
        }
    }
});

//主属性
var mainAttr = {
    el: '#main',
    //页面初始化事件
    mounted: function () {
        //初始化table
        this.tableInit("mm", "IisCountryInfoApi/list");
        //默认排序
        this.tableInitSort("create_time", "desc");
        this.searchClick();
        this.selectInit("mm");
        this.continentCodeList = this.selectSearch("021");
    }
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);


$(function () {
    $('#tableId').height($(window).height()-$('form').height()-75)
});