//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		appName : null,
    		state : null,
    		startTime : null,
    		endTime : null,
    		
        },
        //自定义参数
        //编辑操作id
        handleEditId : ''
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

        //新增按钮
        addClick: function () {
    		ak.dialog("save","business/km/KmIntegratetAppCfg/KmIntegratetAppCfgSave.html");
        },
 
        //删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要删除的记录");
        	}else{
        		ak.confirm("确定要删除选中的【" + ids.length + "】条记录吗？该应用对应的数据也会清空,请谨慎操作!",this.delCallback);
        	}
        },
        //删除回调函数
        delCallback : function(instance){
        	//处理参数
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	this.delSerivce(idsStr);
        },
        //删除请求后台
        delSerivce : function(ids){
        	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/delete",　{ids : ids});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.info(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }else{
            	//提示
            	ak.warning(result.msg);
            }
        },
        //创建桶
        addBucketClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要操作的记录");
        	}
        	else if(ids.length > 1){
        		ak.info("只能选择一条记录");
        	}
        	else{
        		var bucket = null;
            	$.each(this.tableSelection,function(i,n){
            		bucket = n.bucket;
                });
            	
	        	if(ak.isNotNullOrEmpty(bucket)){
	             	ak.info("桶信息已经存在,无法创建")
	             	return;
	            }else{
	            	//this.handleEditId = ids[0];
	             	//ak.dialog("saveBucket","business/km/KmIntegratetAppCfg/KmIntegratetAppCfgSaveBucket.html");
	             	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/createBucket",　{id : ids[0], bucket : "" });
	            	if(result.code == 0){//操作成功
	                	//提示
	                	ak.info(result.msg);
	                	//saveBucketVue.dialogClose();
	                	//刷新表格
	                	mainVue.tableRefresh();
	                }else{
	                	//提示
	                	ak.warning(result.msg);
	                }
	            }
        	}
        	
        },
        //创建索引
        addEsIndexClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要操作的记录");
        	}
        	else if(ids.length > 1){
        		ak.info("只能选择一条记录");
        	}
        	else{
        		var esIndex = null;
            	$.each(this.tableSelection,function(i,n){
            		esIndex = n.esIndex;
                });
            	
	        	if(ak.isNotNullOrEmpty(esIndex)){
	             	ak.info("es索引已经存在,无法创建")
	             	return;
	            }else{
	            	this.handleEditId = ids[0];
	             	ak.dialog("saveEs","business/km/KmIntegratetAppCfg/KmIntegratetAppCfgSaveEs.html");
	            }
        	}
        },
        //编辑操作
        handleEdit: function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	ak.dialog("update","business/km/KmIntegratetAppCfg/KmIntegratetAppCfgUpdate.html");
        },
        
        //启用/禁用
        onOrOff : function(row){
        	var result = ak.msService("kmdm","kmIntegratetAppCfgApi/onOrOff",　{"id":row.id });
        	if(result.code == 0){//操作成功
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
    	//初始化table
    	this.tableInit("kmdm","kmIntegratetAppCfgApi/queryList");
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);