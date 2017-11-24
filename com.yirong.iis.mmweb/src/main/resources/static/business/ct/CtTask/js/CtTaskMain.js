var ctWays = new Object();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	catalogId : null,
        	taskName : null
        },
        catalogNameMain:null
    },
    methods: {
    	loadData : function(){
    		this.selectInit("ct");
        	var ctWayArray = this.selectSearch("010");
        	for (var i = 0; i < ctWayArray.length; i++) {
        		ctWays[ctWayArray[i].key]=ctWayArray[i].label;
			}
        },
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
    		ak.dialog("save","business/ct/CtTask/html/CtTaskSave.html");
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/ct/CtTask/html/CtTaskUpdate.html");
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
        	var result = ak.msService("ct","CtTaskApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }else{
            	//提示
            	ak.warning(result.msg);
            }
        },
        runTask :function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		var result = ak.msService("ct","CtTaskApi/runTask",{ id :ids[0]});
        		ak.info(result.msg);
        		//刷新表格
            	mainVue.tableRefresh();
        	}
        },
        stopTask:function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		//查询共享资源启动的任务列表
        		var shareResult = ak.msService("ct","CtTaskApi/getShareRunTask",{ id :ids[0]});
        		//组装提示
        		if(shareResult.data.length>1){
        			var nameStr = "";
        			for (var j = 0; j < shareResult.data.length; j++) {
        				nameStr+=","+shareResult.data[j].taskName;
					}
        			ak.confirm("此任务"+nameStr+"共享资源启动,如果停止此任务,会停止全部相关任务,您确定停止吗?",this.stopCallback);
        		}else{
        			stopCallback;
        		}
        	}
        },
        stopCallback:function(instance){
        	var result = ak.msService("ct","CtTaskApi/stopTask",{ id :ids[0]});
    		ak.info(result.msg);
    		//刷新表格
        	mainVue.tableRefresh();
        },
        runCycle:function(row,column){
        	if("0" == row[column.property]){
        		return  "无";
        	}else{
        		return row[column.property];
        	}
        },
        ctObjectWay:function(row,column){
        	return ctWays[row[column.property]];
        },
        sharRunTask :function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	var result = ak.msService("ct","CtTaskApi/shareRunTask",{ id :idsStr});
    		ak.info(result.msg);
    		//刷新表格
        	mainVue.tableRefresh();
        },
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	this.loadData();
    	//初始化table
    	this.tableInit("ct","CtTaskApi/list");
    	/*//默认排序
    	var order = this.tableSearchModel.orders[0];
    	order.orderField="create_time";
    	order.orderType="desc";*/
    	//请求后台
    	this.searchClick();
    } 
};
//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);