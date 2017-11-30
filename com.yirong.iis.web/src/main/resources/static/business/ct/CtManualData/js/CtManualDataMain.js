//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	fileTypeName : null
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
    		ak.dialog("save","business/ct/CtManualData/CtManualDataSave.html");
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/ct/CtManualData/CtManualDataUpdate.html");
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
        	var result = ak.msService("ct","CtManualDataApi/delete",{ ids : idsStr});
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
        upFile:function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择文件类型");
        	}else{
        		ak.dialog("upFile","business/ct/CtManualData/upFile.html");
        	}
        },
        detailList:function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行查看详细记录");
        	}else{
        		ak.dialog("CtManualDataDetailMain","business/ct/CtManualData/CtManualDataDetailMain.html");
        	}
        },
      //查看数据
        queryDataClick : function(index,row){
        	this.checkId = row.id;
        	//请求后台
        	ak.dialog("queryData","business/ct/CtManualData/CtManualDataQueryData.html");
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("ct","CtManualDataApi/list");
    	/*//默认排序
    	var order = this.tableSearchModel.orders[0];
    	order.orderField="create_time";
    	order.orderType="desc";*/
    	//请求后台
    	this.searchClick();
    	//加载初始化数据
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);