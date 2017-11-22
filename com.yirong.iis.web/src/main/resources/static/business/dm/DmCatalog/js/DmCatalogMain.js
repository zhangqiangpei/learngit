//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.tableSearchModel.catalogTypeId  = data.id;
            this.pname = data.name;
            this.tableSearch(this.tableSearchModel);
        },
        //请求数据后回调函数
        treeDataCallBack : function(data){
        	//处理根路径
        	data.push({
        		id : "",
	    		name : "======目录分类======",
	    		pid : ""
        	});
        }
	}
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	catalogTypeId: null,
        	catalogName: null
        },
        pname : null
    },
    methods: {
    	//查询按钮
    	searchClick: function (formName) {
    		var param = this.tableSearchModel;
    		if(null == param.catalogTypeId){//选中节点的ID
    			ak.warning("请先选择目录分类");
    		}else{
    			// 当前页切换成第一页
            	param.page = 1;
           	    //查询后台
            	this.tableSearch(param);
    		}
            
        },
         //新增目录分类按钮
        addTypeClick: function () {
        	var param = this.tableSearchModel;
    		if(null == param.catalogTypeId){
    			ak.warning("请先选择目录分类");
    		}else{
    			ak.dialog("saveType","business/dm/DmCatalog/DmCatalogTypeSave.html");
    		}
        },
        //修改目录分类按钮
        updateTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.catalogTypeId)){
    			ak.warning("请先选择根节点以外的目录分类");
    		}else{
        		ak.dialog("updateType","business/dm/DmCatalog/DmCatalogTypeUpdate.html");
        	}
        },
        //删除目录分类按钮
        delTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.catalogTypeId)){
    			ak.warning("请先选择根节点以外的目录分类");
    		}else{
        		ak.confirm("确定要删除选中的【" + this.pname + "】记录吗？",this.delTypeCallback);
        	}
        },
        //删除目录分类回调函数
        delTypeCallback : function(instance){
        	//请求后台
        	var result = ak.msService("pm","DmCatalogApi/deleteType",{ ids : this.tableSearchModel.catalogTypeId});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.info(result.msg);
            	//刷新树
            	mainVue.treeRefresh();
            }
        },
        //新增按钮
        addClick: function () {
        	var param = this.tableSearchModel;
    		if(null ==(param.catalogTypeId)){
    			ak.warning("请先选择目录分类");
    		}else{
    			ak.dialog("save","business/dm/DmCatalog/DmCatalogSave.html");
    		}
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/dm/DmCatalog/DmCatalogUpdate.html");
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
        	var result = ak.msService("pm","DmCatalogApi/delete",{ ids : idsStr});
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
    	//初始化树
    	this.treeInit("pm","DmCatalogApi/treeType",this.treeDataCallBack);
    	//请求后台数据数据
    	this.treeSearch(null);
    	//初始化table
    	this.tableInit("pm","DmCatalogApi/list");
    	//默认排序
    	this.tableInitSort("catalog_name","asc");
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,treeAttr,tableAttr);