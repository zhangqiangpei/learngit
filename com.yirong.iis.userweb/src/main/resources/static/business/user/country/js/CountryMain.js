var AE = /^[A-E]/;
var FK = /^[F-K]/;
var LN = /^[L-N]/;
var OT = /^[O-T]/;
var UZ = /^[U-Z]/;
var vm_country = new Vue({
    el: '#main',
    data: {
    	countryName:'',//国家名称
        continentName:'',//大洲名称
        continentList:[
           {name:'亚洲',id:'021001'},
           {name:'欧洲',id:'021002'},
           {name:'非洲',id:'021003'},
           {name:'北美洲',id:'021004'},
           {name:'南美洲',id:'021005'},
           {name:'大洋洲',id:'021006'}
           ],//大洲列表
        activeName:'first',
        labelName:'全部国家',
        countryList:[
		{name:'A~E',id:'222',children:[]},
		{name:'F~K',id:'222',children:[]},
		{name:'L~N',id:'222',children:[]},
		{name:'O~T',id:'222',children:[]},
		{name:'U~Z',id:'222',children:[]}]//国家列表
    },
    methods: {
        showCountryDetail:function(id){
        	alert(id);
            var sURL = 'forward.do?viewPath=business/user/country/CountryDetail.html&idx='+z.getUrlParam('idx')+'&countryId='+id;
            window.location.href = sURL;
        },
        searchClick:function(){
        	this.countryList[0].children=[];
        	this.countryList[1].children=[];
        	this.countryList[2].children=[];
        	this.countryList[3].children=[];
        	this.countryList[4].children=[];
    		//通过州+中文名/英文名模糊查询
    		var param={
    			"englishName":this.countryName,
    			"chineseName":this.countryName,
    			"continentCode":this.continentName
    		}
    		var result = ak.msService("user","IisCountryInfoApi/queryList",param).data.data;
    		//循环判断国家所属的英文字母的排序
    		for (var i = 0; i < result.length; i++) {
				if(AE.test(result[i].iso2code)){
					this.countryList[0].children.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
				}else if(FK.test(result[i].iso2code)){
					this.countryList[1].children.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
				}else if(LN.test(result[i].iso2code)){
					this.countryList[2].children.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
				}else if(OT.test(result[i].iso2code)){
					this.countryList[3].children.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
				}else if(UZ.test(result[i].iso2code)){
					this.countryList[4].children.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
				}
			}
    		
    	}
    },
    mounted: function() {
    	this.searchClick();
    }
})