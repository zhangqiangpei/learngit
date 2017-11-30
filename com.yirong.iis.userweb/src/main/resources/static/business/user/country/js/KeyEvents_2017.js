//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	countryEngName : null,
        	queryYear:'2017'
        },
        continentName:'',
    	englishName:'',
    	chineseName:'',
    	showId:''
    },
    methods: {
    	getCountryInfo:function(){
        	var param = {
        		"englishName":this.tableSearchModel.countryEngName
        	};
        	var result = ak.msService("user","IisCountryInfoApi/get",param).data;
        	this.chineseName = result.chineseName;
        	this.englishName = result.englishName;
        	this.continentName = result.continentName;
        	if(result.modifyTime==null){
        		this.modifyTime = '无';
        	}else{
        		this.modifyTime = result.modifyTime;
        	}
        },
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        GetQueryString: function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
       },
       detailClick:function(value){
    	 this.showId = value;
    	 ak.dialog("info_div","/business/user/country/KeyEvenInfo.html");
       }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	this.tableSearchModel.countryEngName = this.GetQueryString("eName");
    	this.getCountryInfo();
    	//初始化table
    	this.tableInit("user","IisKeyEventsApi/list");
    	this.tableInitSort("RELEASE_TIME","desc");
    	//请求后台
    	this.searchClick();
    } 
};
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);