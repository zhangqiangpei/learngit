var peVue = new Vue({
    el: '#info',
    data: {
    	continentName:'',
    	englishName:'',
    	chineseName:'',
    	createTime:'',
    	languaged:'',
    	source:'',
    	LawsRegulationsArray:[],
    	tabPosition:'right',
    	lawArray:[]
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
        getLawsRegulations:function(){
        	var param = {
        			"countryEngName":this.englishName
            	};
        	var result = ak.msService("user","IisLawsRegulationsCyApi/queryList",param).data;
        	var laws = result.laws;
        	this.LawsRegulationsArray = result.cy;
            for (var j = 0; j < laws.length; j++) {
				for (var i = 0; i < this.LawsRegulationsArray.length; i++) {
					if(!this.LawsRegulationsArray[i]["children"]){
						this.LawsRegulationsArray[i]["children"] = [];
					}
					if(laws[j].classifyId == this.LawsRegulationsArray[i].id ){
						this.LawsRegulationsArray[i]["children"].push(laws[j]);
					}
				}
				this.lawArray[laws[j].id]=laws.content;
			}
        },
        GetQueryString: function(name){
             var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if(r!=null)return  unescape(r[2]); return null;
        },
        showContent:function(){
        	alert("aaa");
        }
    },
    mounted: function() {
    	this.englishName = this.GetQueryString("eName");
    	this.getCountryInfo();
    	this.getLawsRegulations();
    	
    }
})

