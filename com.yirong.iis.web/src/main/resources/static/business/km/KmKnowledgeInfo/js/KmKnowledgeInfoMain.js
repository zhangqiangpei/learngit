//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		integratetAppId : null,
    		docName : null,
    		docType : null,
    		extractState : null,
    		transforPdfState : null,
    		transforSwfState : null
        } ,
        //自定义
        docTypeOptions : [],
        stateOptions : [] ,
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
        },
 
        //重处理文本抽取
        redoExtract : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要重处理的记录");
        	}else{
        		ak.confirm("确定要重处理选中的【" + ids.length + "】条记录吗？",this.redoExCB);
        	}
        },
        redoExCB : function(){
        	this.callback("resetExtract");
        },
 
        //重处理文本转换pdf
        redoTransforPdf : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要重处理的记录");
        	}else{
        		ak.confirm("确定要重处理选中的【" + ids.length + "】条记录吗？",this.redoTPCB);
        	}
        },
        redoTPCB : function(){
        	this.callback("resetTransforPdf");
        },
        
        //重处理文本抽取
        redoTransforSwf : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要重处理的记录");
        	}else{
        		ak.confirm("确定要重处理选中的【" + ids.length + "】条记录吗？",this.redoTSCB);
        	}
        },
        redoTSCB : function(){
        	this.callback("resetTransforSwf");
        },
        
        //回调函数
        callback : function(method){
        	//处理参数
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length-1){
        			idsStr += ",";
        		}
        	}); 
        	var result = ak.msService("kmdm","kmDocInfoApi/"+method,　{ids : idsStr});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.info(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            } 
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
    	this.stateOptions = this.selectSearch("013"); 
    	
    	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/getAllApps",null);
    	if(null != result && result.code == 0){//操作成功
    		this.appsOptions = result.data;
        } 
 
    	//初始化table
    	this.tableInit("kmdm","kmDocInfoApi/queryList");
    	this.tableInitSort("createTime","desc");
    	this.onSearch();
 
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);