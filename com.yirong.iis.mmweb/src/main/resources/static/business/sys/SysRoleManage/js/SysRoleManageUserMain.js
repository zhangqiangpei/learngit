//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		id : null,
    		userName : null,
    		userDisplayName : null 
        },
        //下拉框参数
        serviceName : ''
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
        	//请求后台
        	var para = {
        		"ids" :ids
        	}
        	var result = ak.msService("sys","roleManageApi/delUserRole",para);
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	roleUserMainVue.tableRefresh();
            }else{
            	//提示
            	ak.error(result.msg);
            }
        },
        //新增按钮
        addClick: function () {
    		//ak.dialog("roleUserSave","business/sys/SysRoleManage/SysRoleManageUserSave.html");
        	//请求后台数据数据
        	commonVue.SysOrgUserTreeParam = {
        		code : '',
        		roleId :  this.tableSearchModel.id
        	}
        	commonVue.SysOrgUserTreeCallBack = this.changeClickCallback;
			ak.dialog("SysOrgUserTree","common/SysOrgUserTree.html");
        },
        //树选择器回调事件
        changeClickCallback : function(checkedNodes,ids){
        	
        	var userIds = "";
			$.each(ids,function(i,id){
				userIds += id;
				if(i != (ids.length - 1)){//不是最后一个数据均需加入分隔符
					userIds += ",";
				}
			});
        	
        	var param = {
        		roleId : this.tableSearchModel.id,
        		userIds : userIds
        	}
 
			var data = ak.msService("sys","roleManageApi/addUserRole",param);
        	if(null != data && data.code == 0){//操作成功
        		//提示
            	ak.success(data.msg);
            	//刷新表格
            	roleUserMainVue.tableRefresh();
        	}
			 
        }  
    },
  //页面初始化事件
    mounted: function () {
    	//初始化下拉框微服务名
    	this.selectInit("sys");
    	//初始化table
    	this.tableInit("sys","roleManageApi/usreRole");
    	//加载初始化数据
    	this.tableSearchModel.id = mainVue.handleEditId;
    	this.onSearch();
    	
    	setTimeout(function(){
    		var allHeight = $(window).height();
     		var otherHeight = $("#roleUserForm").height() + $(".el-dialog__header").height();
     		$("#roleUserTable").height(allHeight - otherHeight - 100);
    	},100);
    	
 		
    }
});
//获取dialog属性
var mainAttr = getDialogMergeAttr({
	el: '#roleUsermain'
});	
 
//生成vue对象
var roleUserMainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr); 