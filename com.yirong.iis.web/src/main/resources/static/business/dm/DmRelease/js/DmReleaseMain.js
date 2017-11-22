//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            catalogName : null,
            releaseState : null
        },
        //下拉框数组
        releaseStateList : []
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        	//请求后台后需处理界面展示数据
        	var tlength = this.tableData.length;
        	for(var i = 0;i < tlength;i++){
        		var data = this.tableData[i];
        		if(data.is_mining_analysis == 1){
        			data.is_mining_analysis = "已发布"
        		}else{
        			data.is_mining_analysis = "未发布"
        		}
        		if(data.is_visualization == 1){
        			data.is_visualization = "已发布"
        		}else{
        			data.is_visualization = "未发布"
        		}
        	}
        },
        //发布按钮
        enReleaseClick: function () {
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要发布的记录");
        	}else{
        		ak.dialog("release","business/dm/DmRelease/DmReleaseRelease.html");  
        	}
        },
        //发布按钮
        enPublishClick: function () {
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要发布的记录");
        	}else{
        		ak.dialog("release","business/dm/DmRelease/DmReleaseReleaseOld.html");  
        	}
        },
        /*
         * //取消发布按钮--停用取消发布的功能
        unReleaseClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要取消发布的记录");
        	}else{
        		ak.confirm("确定要取消发布选中的【" + ids.length + "】条记录吗？",this.unReleaseCallback);
        	}
        },*/
        //删除回调函数
        unReleaseCallback : function(instance){
        	//处理参数
        	releaseVue.releaseModel.isRelease = false;
        	//处理参数
        	var ids = mainVue.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	releaseVue.releaseModel.idStrs = idsStr;
        	//请求后台
            var result = ak.msService("pm","DmReleaseApi/release",releaseVue.releaseModel);
            if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.searchClick();
            }
        }
    }
});

//主属性
var mainAttr = getUtilMergeAttr({
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("pm","DmReleaseApi/list");
    	//初始化发布状态下拉框
    	this.selectInit("pm");
    	this.releaseStateList = this.selectSearch("016");
    	//默认排序
    	this.tableInitSort("catalog_name","asc");
    	//请求后台
    	this.searchClick();
    } 
});

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);