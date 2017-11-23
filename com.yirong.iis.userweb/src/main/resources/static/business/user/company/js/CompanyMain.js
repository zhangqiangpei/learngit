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
          } ,
          
        //数据
        inFoData :[],
        infoSearchModel : {
			currentPage : 1,
			pageSize : 20,
			orders : []
		} 
    },
    methods:{
        //查询按钮
        searchClick: function () {
        	if(null != this.startAndEnd){
        		this.tableSearchModel.startDt = this.startAndEnd[0];
        		this.tableSearchModel.endDt = this.startAndEnd[1];
            }
        	
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            param.pageSize=10;

            //查询后台
            this.tableSearch(param);
        },
        
        //选择洲，改变事件
        continentChange:function(code){
        	var res = ak.msService("user", "IisCountryInfoApi/queryCountrys",{continentCode: code});
        	if(null != res && res.code == 0){
        		this.countrys = res.data;
        	}
        },
        //跳转到详情页面
        handleClick:function(row){
        	var idx = z.getUrlParam('idx');
        	window.location.href = "/forward.do?viewPath=business/user/company/CompanyDetail.html&idx="+idx;
        },
        detalClick:function(row){
        	
        },
        //动态资讯
        getInfoData:function(){
        	//查询后台数据
        	this.infoSearchModel.orders = [];
        	this.infoSearchModel.orders.push({
    			orderField : "createTime",
    			orderType : "desc"
    		});
        	this.infoSearchModel.pageSize=6;
        	var param = this.infoSearchModel;
            var result = ak.msService("user", "IisCompanyInformationApi/list",param);
            
            //两条合并一条
            function entity(title1,createTime1,title2,createTime2) //声明对象
        	{
        	    this.title1 = title1;
        	    this.createTime1= createTime1;
        	    this.title2= title2;
        	    this.createTime2 = createTime2;
        	}
        	if(null != result && result.code == 0){
        		var data = result.data.data;
        		var title1,tiitle2,createTime1,createTime2;
        		for(var i = 0;i<data.length;i++){
        			var title = data[i].title;
        			var createTime = data[i].createTime;
        			if(i % 2 == 0){
        				title1 = title;
        				createTime1 = createTime;
        			}else{
        				title2 = title;
        				createTime2 = createTime;
        				this.inFoData.push(new entity(title1,createTime1,title2,createTime2));
        				title1 = null; tiitle2 = null; createTime1 = null; createTime2 = null;
        			}
        		}
        	}
        }
    },
    mounted:function () {
    	//初始化下拉框微服务名
    	this.selectInit("user");
    	//角色类型下拉框数据
    	this.continents = this.selectSearch("021");
    	this.industrys = this.selectSearch("022");
    	this.frequencys = this.selectSearch("023");
    	
        //初始化table
        this.tableInit("user", "IisFinancialOverviewApi/list");
        //默认排序
        this.tableInitSort("O.CREATE_TIME", "desc");
        this.searchClick();
        
        //动态资讯
        this.getInfoData();
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);