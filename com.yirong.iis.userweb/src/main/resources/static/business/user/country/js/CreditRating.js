var peVue = new Vue({
    el: '#info',
    data: {
    	continentName:'',
    	englishName:'',
    	chineseName:'',
    	createTime:'',
    	languaged:'',
    	source:'',
    	CountryCredit:new Object(),
    	CountryDebt:new Object(),
    	CountryViolationArray:[]
    },
    methods: {
    	//根据国家英文名查询
    	getCountryInfo:function(){
        	var param = {
        		"englishName":this.englishName
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
        getCountryCredit:function(){
        	var param = {
        			"englishName":this.englishName
            	};
            	var result = ak.msService("user","IisCountryCreditApi/getByName",param).data;
            	this.CountryCredit = result;
        },
        getCountryDebt:function(){
        	var param = {
        			"englishName":this.englishName
            	};
            	var result = ak.msService("user","IisCountryDebtApi/getByName",param).data;
            	this.CountryDebt = result;
        },
        getCountryViolation:function(){
        	var param = {
        			"englishName":this.englishName
            	};
            	var result = ak.msService("user","IisCountryViolationApi/querylist",param).data;
            	this.CountryViolationArray = result;
        },
        GetQueryString: function(name){
             var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if(r!=null)return  unescape(r[2]); return null;
        }
    },
    mounted: function() {
    	debugger;
    	this.englishName = this.GetQueryString("eName");
    	this.getCountryInfo();
    	this.getCountryCredit();
    	this.getCountryDebt();
    	this.getCountryViolation();
    }
})

