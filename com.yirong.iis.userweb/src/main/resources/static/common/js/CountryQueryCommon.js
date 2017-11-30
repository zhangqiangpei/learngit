var cnVue = new Vue({
    el: '#CountryNavigation',
    data: {
    	countryName:'',
       countryList:[],
//       {name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}
       defContinent:'021001',//默认亚洲
       continentList:[
		{name:'亚洲',id:'021001'},
		{name:'欧洲',id:'021002'},
		{name:'非洲',id:'021003'},
		{name:'北美洲',id:'021004'},
		{name:'南美洲',id:'021005'},
		{name:'大洋洲',id:'021006'}
        ]//大洲列表
    },
    methods: {
    	searchClick:function(){
        	this.countryList=[];
    		//通过州+中文名/英文名模糊查询
    		var param={
    			"englishName":this.countryName,
    			"chineseName":this.countryName,
    			"continentCode":this.defContinent
    		}
    		var result = ak.msService("user","IisCountryInfoApi/queryList",param).data.data;
    		//循环判断国家所属的英文字母的排序
    		for (var i = 0; i < result.length; i++) {
				this.countryList.push({"chineseName":result[i].chineseName,"englishName":result[i].englishName});
			}
    	}
    },
    mounted: function() {
    	this.searchClick();
    }
})

