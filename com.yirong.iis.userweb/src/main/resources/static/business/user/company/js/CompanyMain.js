var mainAttr={
    el:"#main",
    data:{
        //查询对象
        tableSearchModel: {
        	continentCode:null,
        	countryEngName:null,
        	industryCode:null,
        	startDt:null,
        	endDt:null,
        	frequency:null,
        	keyWord:null
        },
 
        continents:[],
        countrys:[],
        industrys:[],
        frequencys:[],
        
        startAndEnd:null,
        
        pickerOptions2: {
            shortcuts: [{
              text: '最近一周',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', [start, end]);
              }
            }, {
              text: '最近一个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                picker.$emit('pick', [start, end]);
              }
            }, {
              text: '最近三个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                picker.$emit('pick', [start, end]);
              }
            }]
          } 
    },
    methods:{
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        }
    },
    mounted:function () {
    	//初始化下拉框微服务名
    	this.selectInit("user");
    	//角色类型下拉框数据
    	this.continents = this.selectSearch("021");
    	
    	
        //初始化table
        this.tableInit("user", "IisFinancialOverviewApi/list");
        //默认排序
        this.tableInitSort("O.CREATE_TIME", "desc");
        this.searchClick();
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);