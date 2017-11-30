//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		userName : null,
    		userDisplayName : null
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
    		ak.dialog("save","business/sys/SysUserManage/SysUserManageSave.html");
        },
        //删除按钮
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
        	var result = ak.msService("sys","userManageApi/delete",　{ids : ids});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        //编辑操作
        handleEdit: function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	ak.dialog("update","business/sys/SysUserManage/SysUserManageUpdate.html");
        },
        //启用/禁用
        onOrOff : function(row){
        	
        	var userStatus = "000000";
        	if(row.userStatus == "000000"){
        		userStatus = "999999";
        	}
        	
        	var result = ak.msService("sys","userManageApi/onOrOff",{"id":row.id,"userStatus":userStatus });
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        	 
        },
        //重置密码
        upClick: function () {
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择记录进行重置密码！");
        	}else{
        		var idsStr = "";
            	$.each(ids,function(i,n){
            		idsStr += n;
            		if(i != ids.length){
            			idsStr += ",";
            		}
            	});
        		var result = ak.msService("sys","userManageApi/resetPwd",{"userIds":idsStr});
        		if(null != result && result.code == 0){//操作成功
        			ak.success(result.msg);
        		}
        	}
        },
        selectAble:function(row, column){
        	if(row.isBuilt == 1){
        		return false;
        	}
        	return true;
        }
    }
});
 
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("sys","userManageApi/list");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);