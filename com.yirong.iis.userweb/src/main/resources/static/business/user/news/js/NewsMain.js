var mainAttr={
    el:"#main",
    data:{
        //查询对象
        tableSearchModel:{
            keywords:'',
            type:'',
            continentCode:"",
            countryChnName:"",
            countryEngName:""
        },
        continents:[],
        countries:[],
        countriesParam:{
            continentCode:"",
            pageSize: 300
        },
        currentNewsTypeName:""
    },
    methods:{
        setContinentsCode:function (code) {
            this.tableSearchModel.continentCode = code;
        },
        setCountryCode:function (id) {
            for (var i = 0; i< this.countries.length; i++){
                if (this.countries[i].id === id){
                    this.tableSearchModel.countryChnName = this.countries[i].chineseName;
                    this.tableSearchModel.countryEngName = this.countries[i].englishName;
                }
            }
        },
        // 获取搜索结果
        getSearchResult:function () {
            console.log(vm_toolbarVue.searchNewsResult);
            console.log("11233");
        },
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        },
    },
    mounted:function () {
        // 初始化洲
        this.selectInit("user");
        this.continents = this.selectSearch("021");
        // 初始化国家选项
        var countryResult = z.msService("user", "IisCountryInfoApi/queryList", this.countriesParam);
        if (countryResult.code === 0){
            this.countries = countryResult.data.data;
        }
        //初始化table
        this.tableInit("user", "IisEsSearchApi/searchNews");
        //默认排序
        this.tableInitSort("release_time", "desc");
        this.tableSearch(this.tableSearchModel);
        currentUseToolbarVue = this;
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var searchNewsVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);