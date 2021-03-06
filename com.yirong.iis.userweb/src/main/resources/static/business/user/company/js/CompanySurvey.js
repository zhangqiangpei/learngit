var mainAttr={
	el:"#main",
	data:{
	    //查询对象
	    tableSearchModel: {
    		companyId:null
    	},
		surveyEntiy:null
    },
    methods: {
    	searchClick: function () {
    		var param = this.tableSearchModel;
    		// 当前页切换成第一页
            param.currentPage = 1;
            param.pageSize=1;
    		var res = z.msService("user", "IisCompanyApi/getByCompanyId",param);
        	if(null != res && res.code == 0){
        		this.surveyEntiy = res.data;
        	}
    	} ,
    	
    	chnTranslate:function(){
    		$("#chnctdiv").css("display", "block");
    		$("#ctdiv").css("display", "none");
    		$("#engctdiv").css("display", "none");
    	},
    	
    	engTranslate:function(){
    		$("#chnctdiv").css("display", "none");
    		$("#ctdiv").css("display", "none");
    		$("#engctdiv").css("display", "block");
    	}
    },
    mounted: function() {
    	this.tableSearchModel.companyId= parent.mainVue.$data.companyId;
        //默认排序
        this.searchClick();
    }
};
//添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);

