//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.tableSearchModel.standardTypeId  = data.id;
            this.standardTypeName = data.name;
            this.tableSearch(this.tableSearchModel);
        },
        //请求数据后回调函数
        treeDataCallBack : function(data){
        	//处理根路径
        	data.push({
        		id : "",
	    		name : "====标准目录====",
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
            standardName : null,
            standardCode: null
        }
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
    		if(null == param.standardTypeId){
    			ak.warning("请先选择标准分类");
    		}else{
    			// 当前页切换成第一页
            	param.page = 1;
           	    //查询后台
            	this.tableSearch(param);
    		}
            
        },
        //新增标准分类按钮
        addTypeClick: function () {
        	var param = this.tableSearchModel;
    		if(null == param.standardTypeId){
    			ak.warning("请先选择标准分类");
    		}else{
    			ak.dialog("saveType","business/pm/PmStandard/PmStandardTypeSave.html");
    		}
        },
        //修改标准分类按钮
        updateTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.standardTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
        		ak.dialog("updateType","business/pm/PmStandard/PmStandardTypeUpdate.html");
        	}
        },
        //删除标准分类按钮
        delTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.standardTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
        		ak.confirm("确定要删除选中的【" + this.standardTypeName + "】记录吗？",this.delTypeCallback);
        	}
        },
        //删除标准分类回调函数
        delTypeCallback : function(instance){
        	//请求后台
        	var result = ak.msService("pm","PmStandardApi/deleteType",{ ids : this.tableSearchModel.standardTypeId});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新树
            	mainVue.treeRefresh();
            }
        },
        //新增按钮
        addClick: function () {
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.standardTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
    			ak.dialog("save","business/pm/PmStandard/PmStandardSave.html");
    		}
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/pm/PmStandard/PmStandardUpdate.html");
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
        	var result = ak.msService("pm","PmStandardApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
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
    	//初始化树
    	this.treeInit("pm","PmStandardApi/treeType",this.treeDataCallBack);
    	//请求后台数据数据
    	this.treeSearch(null);
    	//初始化table
    	this.tableInit("pm","PmStandardApi/list");
    	//默认排序
    	this.tableInitSort("standard_code","asc");
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,treeAttr,tableAttr);