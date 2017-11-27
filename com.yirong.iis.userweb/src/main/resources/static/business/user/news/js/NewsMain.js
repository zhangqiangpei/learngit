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
        currentNewsTypeName:"",
        newsTypes:[]
    },
    methods:{
        renderHeader:function (createElement, { column }) {
            return createElement('a', ['新闻 > ' + searchNewsVue.currentNewsTypeName], {});
        },
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
            for (var i = 0; i < this.tableData.length; i++){
                for (var j = 0; j<this.newsTypes.length; j++){
                    if (this.tableData[i].TYPE === this.newsTypes[j].value){
                        this.tableData[i].TYPE = this.newsTypes[j].label;
                        break;
                    }
                }
            }
        },
    },
    mounted:function () {
        // 初始化洲
        this.selectInit("user");
        this.continents = this.selectSearch("021");
        this.newsTypes = this.selectSearch("024");
        // 初始化国家选项
        z.msServiceAsync("user","IisCountryInfoApi/queryList", this.countriesParam, function (result) {
            if (result.code === 0){
                searchNewsVue.countries = result.data.data;
            }
        });
        //初始化table
        this.tableInit("user", "IisEsSearchApi/searchNews");
        //默认排序
        this.tableInitSort("RELEASE_TIME", "desc");
        this.tableSearch(this.tableSearchModel);
        this.searchClick();
        currentUseToolbarVue = this;
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var searchNewsVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);