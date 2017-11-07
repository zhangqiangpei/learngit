//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		name : null,
    		type : null,
    		applicationId: null
        },
        //自定义
        typeoptions : [],
        appoptions : [],
		//记录id（编辑时赋值）
        handleEditId  : '',
        applicationId : ''
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
    		ak.dialog("save","business/sys/SysRoleManage/SysRoleManageSave.html");
        },
        //删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.info("请选择需要删除的记录");
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
        	//请求后台
        	var para = {
        		"ids" :　ids
        	}
        	var result = ak.msService("sys","roleManageApi/delete",　para);
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        //
        //编辑操作
        handleEdit: function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	ak.dialog("update","business/sys/SysRoleManage/SysRoleManageUpdate.html");
        },
        //角色用户
        handleRoleUser : function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	this.serviceName = row.serviceName;
        	ak.dialog("roleUsermain","business/sys/SysRoleManage/SysRoleManageUserMain.html");
        },
        //配置权限
        configPermission:function(index,row){
        	this.applicationId = row.applicationId;
        	this.handleEditId = row.id;
        	ak.dialog("SysPermissionTree","business/sys/SysRoleManage/SysConfigPermission.html");
        },
        SysPermissionTreeCallBack:function(ids){
        	var returnData = ak.msService('sys','sysPermissionApi/updateRolePermission',{id :this.handleEditId,ids:ids});
        	if(null != returnData && returnData.code == 0){//操作成功
        		ak.success(returnData.msg);
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
    	this.selectInit("sys");
    	//初始化table
    	this.tableInit("sys","roleManageApi/list");
    	//角色类型下拉框数据
    	this.typeoptions = this.selectSearch("002");
    	//首次查询数据
    	this.onSearch();
    	//加载应用系统下拉框数据
    	var appData = ak.msService('sys','loginApi/getApp',{appIds : ''}); 
    	if(null != appData && appData.code == 0){//操作成功
			this.appoptions = appData.data;
		}
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);