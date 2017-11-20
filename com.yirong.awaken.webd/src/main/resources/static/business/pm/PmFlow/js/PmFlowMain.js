//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            flowName : null
        }
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        //新增按钮
        addClick: function () {
    		ak.dialog("save","business/pm/PmFlow/PmFlowSave.html");
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/pm/PmFlow/PmFlowUpdate.html");
        	}
        },
        //删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要删除的记录");
        	}else{
        		ak.confirm("确定要删除选中的【" + ids.length + "】条记录吗？",this.delCallback);
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
        	//请求后台
        	var result = ak.msService("pm","PmFlowApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        //启用/停用按钮
        ableClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要启用/停用的记录");
        	}else{
        		ak.confirm("确定要启用/停用选中的【" + ids.length + "】条记录吗？",this.ableCallback);
        	}
        },
        //共享资源启动任务
        shareRunClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	
        },
        //启用/停用回调函数
        ableCallback : function(instance){
        	//处理参数
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	//请求后台
        	var result = ak.msService("pm","PmFlowApi/able",{ ids : idsStr});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        //配置流程
        configClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行配置");
        	}else{
        		ak.dialog("config","business/pm/PmFlow/PmFlowConfig.html");
        	}
        },
        //启用状态列格式化事件
        ableFormatter : function(row, column){
        	if(row.is_enable == 1){
        		return "启用";
        	}else{
        		return "停用";
        	}
        },
        //流程测试结果列格式化事件
        testFormatter : function(row, column){
        	if(row.test_is_success == 1){
        		return "成功";
        	}else{
        		return "失败";
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
    	this.tableInit("pm","PmFlowApi/list");
    	//默认排序
    	this.tableInitSort("flow_name","asc");
    	//请求后台
    	this.searchClick();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);