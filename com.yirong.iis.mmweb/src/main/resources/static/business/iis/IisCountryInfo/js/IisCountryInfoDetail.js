//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        countryInfo : null
    },
    methods: {

    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("mm","IisCountrySurveyApi/list");
    	//默认排序
		this.tableInitSort("create_time","desc");

		var result = ak.msService("mm","IisCountryInfoApi/get",{id : "1"});
		if (result === 0){
		    this.countryInfo = result.data;
        }
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);