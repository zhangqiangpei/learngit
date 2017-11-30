var tableAttr = getTableMergeAttr({
    el: '#info',
    data: {
    	//查询对象
        tableSearchModel: {
        	englishName : null,
        	type:""
        },
    	continentName:'',
    	englishName:'',
    	chineseName:'',
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
    	//根据国家英文名查询
    	getCountryInfo:function(){
        	var param = {
        		"englishName":this.tableSearchModel.englishName
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
        GetQueryString: function(name){
             var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if(r!=null)return  unescape(r[2]); return null;
        }
    }
})
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	this.tableSearchModel.englishName = this.GetQueryString("eName");
    	this.getCountryInfo();
    	//初始化table
    	this.tableInit("user","IisCompanyApi/list");
    	//请求后台
    	this.searchClick();
    } 
};
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);
