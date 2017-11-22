//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		integratetAppId : null,
    		docName : null,
    		docType : null 
        } ,
        //自定义
        docTypeOptions : [] ,
        appsOptions : []
    },
    methods: {
    	//查询按钮
    	onSearch: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        } 
 
    }
});
 
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化下拉框微服务名
    	this.selectInit("kmdm");
    	//角色类型下拉框数据
    	this.docTypeOptions = this.selectSearch("011");
 
    	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/getAllApps",null);
    	if(null != result && result.code == 0){//操作成功
    		this.appsOptions = result.data;
        } 
    	
    	//初始化table
    	this.tableInit("kmdm","kmEosDocInfoApi/queryList");
    	this.tableInitSort("createTime","desc");
    	this.onSearch();
 
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);