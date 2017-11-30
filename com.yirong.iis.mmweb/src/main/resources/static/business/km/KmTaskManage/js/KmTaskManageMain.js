//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		 
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
    		ak.dialog("save","business/km/KmTaskManage/KmTaskManageSave.html");
        },
        /*//删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.success("请选择需要删除的记录");
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
        	this.delSerivce(idsStr);
        },
        //删除请求后台
        delSerivce : function(ids){
        	var result = ak.msService("kmdm","kmDocJobApi/delete",　{"jobClassName":row.JOB_NAME,"jobGroupName":row.JOB_GROUP});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },*/
        //单个操作
        handleDel: function(index,row){
        	var result = ak.msService("kmdm","kmDocJobApi/deletejob",　{"jobName":row.JOB_NAME,"jobGroupName":row.JOB_GROUP});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        //暂停
        handlePause: function(index,row){
        	var result = ak.msService("kmdm","kmDocJobApi/pausejob",　{"jobName":row.JOB_NAME,"jobGroupName":row.JOB_GROUP});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        	
        },
        //恢复
        handleResume: function(index,row){
        	var result = ak.msService("kmdm","kmDocJobApi/resumejob",　{"jobName":row.JOB_NAME,"jobGroupName":row.JOB_GROUP});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
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
    	this.tableInit("kmdm","kmDocJobApi/queryjob");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);