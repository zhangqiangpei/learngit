//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.resourceName = data.name;
            this.tableSearchModel.resourceId = data.id;
            this.loadMenu();
            this.tableSearch(this.tableSearchModel);
        }
	}
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	applicationId : null,
        	resourceId : null
        },
        //自定义参数
        appoptions : [],
        //
        activeNames: ['1'],
        //记录id（编辑时赋值）
        handleEditId  : '',
        resourceName: '',
        codeoptions:[]
    },
    methods: {
    	//加载应用
        loadApps : function(){
        	//加载应用系统下拉框数据
        	var appData = ak.msService('sys','loginApi/getApp',{appIds : ''}); 
        	if(null != appData && appData.code == 0){//操作成功
    			this.appoptions = appData.data;
    		}
        },
        //加载菜单信息
        loadMenu : function(){
        	var data = ak.msService("sys", "resourceApi/getById", {id : this.tableSearchModel.resourceId });
        	if(null != data && data.code == 0){//操作成功
    			var result = data.data;
    			$("#name").text(result.name);
            	$("#sn").text(result.sn);
            	$("#url").text(result.url);
            	$("#priority").text(result.priority);
            	$("#className").text(result.className);
            	$("#iconName").text(result.iconName);
    		}
        },
        //重置信息
        reset: function(){
        	this.tableSearchModel.applicationId = null;
        	this.tableSearchModel.resourceId = null;
        	this.tableData = [];
        	this.resourceName = null;
        	
        	$("#name").text('');
        	$("#sn").text('');
        	$("#url").text('');
        	$("#priority").text('');
        	$("#className").text('');
        	$("#iconName").text('');
        },
    	//新增资源
    	addres: function (){
    		if(ak.isNullOrEmpty(this.tableSearchModel.applicationId)){
    			ak.info("请先选择一个应用");
    		}else{
    			ak.dialog("save","/business/sys/SysResourceManage/SysResourceManageSave.html");
    		}
    	},
    	//修改资源
    	upres: function (){
    		if(ak.isNullOrEmpty(this.tableSearchModel.resourceId)){
    			ak.info("请先选择资源目录");
    		}else{
    			ak.dialog("update","/business/sys/SysResourceManage/SysResourceManageUpdate.html");
    		}
    	},
    	//删除资源
    	delres: function (){
    		//获取表格选中的id集
    		if(ak.isNullOrEmpty(this.tableSearchModel.resourceId)){
        		ak.info("请先选择资源目录");
        	}else{
        		ak.confirm("确定要删除【"+$("#name").text()+"】的资源目录吗？",this.delresCallback);
        	}
    	},
    	delresCallback: function (){
    		//请求后台
        	var result = ak.msService("sys","resourceApi/delete",{ ids : this.tableSearchModel.resourceId});
        	/*if(result.code == 0){//操作成功
        	  	//提示
            	ak.success(result.msg);
            	//刷新树
            	mainVue.treeRefresh();
            	mainVue.reset();
            }*/
        	
        	if(null != result && result.code == 0){//操作成功
    			//提示
            	ak.success(result.msg);
            	//刷新树
            	mainVue.treeRefresh();
            	mainVue.reset();
    		}
    	},
/*    	//查询按钮,权限信息
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(this.tableSearchModel);
        },*/
        //新增按钮
        addClick: function () {
        	if(ak.isNullOrEmpty(this.tableSearchModel.resourceId)){
        		ak.info("请先选择资源目录");
        	}else{
    			ak.dialog("pmssave","/business/sys/SysResourceManage/SysResourceManagePemissionSave.html");
    		}
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
        	//请求后台
        	var result = ak.msService("sys","resourceApi/delPermission",{ ids : idsStr});
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
        	ak.dialog("pmsupdate","/business/sys/SysResourceManage/SysResourceManagePemissionUpdate.html");
        },
        //应用下拉框改变事件
        appschg : function(applicationId){
        	//查询树 请求后台数据数据
        	mainVue.reset();
        	this.tableSearchModel.applicationId = applicationId;
        	this.treeSearch({"applicationId" :applicationId});
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
    	this.codeoptions = this.selectSearch("007");
    	//初始化树
    	this.treeInit("sys","resourceApi/tree");
    	
    	//初始化table
    	this.tableInit("sys","resourceApi/pmslist");

    	//加载所有应用
    	this.loadApps();
 
    	//查询树 请求后台数据数据
    	this.treeSearch({"applicationId" :this.tableSearchModel.applicationId});
    	awakenCommonVue.initHeight("resoure_tree");
    	
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,treeAttr,tableAttr,selectAttr);