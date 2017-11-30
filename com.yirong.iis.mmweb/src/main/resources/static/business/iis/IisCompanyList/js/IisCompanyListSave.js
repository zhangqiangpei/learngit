var utilAttr = getUtilMergeAttr();
//获取table属性
var dialogAttr = getDialogMergeAttr({
    data: {
    	dialogVisible: true,
		companyList:[],
    	selectedCompanyList:[],
    	typeList: [],
        type: ''
    },
    methods: {
    	/*filterMethod:function (query,item){
    		return item.search.indexOf(query)>-1;
    	},*/
    	handleChange:function(value) {
    		var typeId = value;
    		//alert(typeId);
    		//企业列表
    		var result = ak.msService("mm", "IisCompanyListApi/selectByTypeId", {"typeId" : typeId});
    		if (result.code === 0) {
                saveVue.tableRefresh();
            }
    		//var cList = result.data;
    		this.companyList = result.data;
    	}
    }
});

//主属性
var mainAttr = {
    el: '#save',
    //页面初始化事件
    mounted: function () {
        this.selectInit("mm");
        this.typeList = this.selectSearch("025");
        //给company赋值
        /*var array = [];
        var cList = ['上海', '北京', '广州', '深圳', '南京', '西安', '成都'];
        var pinyin = cList;
        	$.each(cList,function(index,company){
        		//alert(index);	
        	    array.push({
        	        label: company,
        	        key: index,
        	        pinyin: pinyin[index]
        	      });
        	  });*/
        //this.companyList = array;
    }
};

//生成vue对象
var saveVue = ak.getMergeVue(mainAttr, dialogAttr, utilAttr);