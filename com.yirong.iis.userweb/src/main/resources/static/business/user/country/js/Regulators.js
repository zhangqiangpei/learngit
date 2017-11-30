var peVue = new Vue({
    el: '#info',
    data: {
    	continentName:'',
    	englishName:'',
    	chineseName:'',
    	createTime:'',
    	languaged:'',
    	source:'',
    	RegulatorsArray:[],
    	tabPosition:'right'
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
        getRegulators:function(){
        	debugger;
        	var param = {
        			"countryEngName":this.englishName
            	};
            	var result = ak.msService("user","IisRegulatorsApi/queryList",param).data;
            	this.RegulatorsArray = result;
        },
        GetQueryString: function(name){
             var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if(r!=null)return  unescape(r[2]); return null;
        }
    },
    mounted: function() {
    	this.englishName = this.GetQueryString("eName");
    	this.getCountryInfo();
    	this.getRegulators();
    	
    }
})

