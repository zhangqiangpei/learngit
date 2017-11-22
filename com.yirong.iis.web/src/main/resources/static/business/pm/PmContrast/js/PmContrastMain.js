//采集table属性
var ctTableAttr = getTableMergeAttr({
    data: {
    	//采集table数据
		ctTableData :[],
    	//采集table查询对象
        ctTableSearchModel: {
            ctChannel : null,
            ctObjectName: null,
			pageSize : 99999
        },
        //采集table初始化参数
		ctTableInitParam : {
		},
        //采集table选中行对象集
		ctTableSelection : null,
		ctChannelList : null
    },
    methods: {
    	//采集table查询按钮
    	ctSearchClick: function () {
    		var param = this.ctTableSearchModel;
    		this.ctTableSearch(param);
        },
        //采集table查询
		ctTableSearch : function(param){
			//获取参数
			var serviceName = this.ctTableInitParam.serviceName;
			var pathName = this.ctTableInitParam.pathName;
			//获取数据
			var result = ak.msService(serviceName,pathName,param);
			//处理结果
			this.ctTableData = result.data.data;
		},
		//采集table初始化
		ctTableInit : function(serviceName,pathName){
			this.ctTableInitParam.serviceName = serviceName;
			this.ctTableInitParam.pathName = pathName;
		},
		//采集table选中事件
        ctTableChange : function(currentRow, oldCurrentRow){
        	//处理参数
        	this.treeSearchModel.ctObjectId = currentRow.id;
        	//处理显示页面变量
        	this.ctObjectName = currentRow.ct_object_name;
        	this.ctChannelName = currentRow.ct_channel;
        	//请求数据
        	this.treeSearch(this.treeSearchModel);
        },
        //采集table重置查询条件
		ctTableResetCondition: function () {
        	this.$refs["ctTableSearchModel"].resetFields();
    	}
    }
});

//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.tableSearchModel.contrastTypeId  = data.id;
            this.standardTypeId = data.atttibute.standardTypeId;
            this.standardTypeName = data.name;
            this.tableSearch(this.tableSearchModel);
        }
	}
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	sourceCode : null,
            sourceName: null,
            standardName : null,
            standardCode: null
        },
        treeSearchModel :{
        	ctObjectId:""
        }
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.contrastTypeId)){
    			ak.warning("请先选择根节点以外对照分类");
    		}else{
    			// 当前页切换成第一页
            	param.page = 1;
           	    //查询后台
            	this.tableSearch(param);
    		}
            
        },
        //新增标准分类按钮
        addTypeClick: function () {
    		if(ak.isNullOrEmpty(this.treeSearchModel.ctObjectId)){
    			ak.warning("请先选择采集对象");
    		}else{
    			ak.dialog("saveType","business/pm/PmContrast/PmContrastTypeSave.html");
    		}
        },
        //修改标准分类按钮
        updateTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.contrastTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
        		ak.dialog("updateType","business/pm/PmContrast/PmContrastTypeUpdate.html");
        	}
        },
        //删除标准分类按钮
        delTypeClick : function(){
        	var param = this.tableSearchModel;
    		if(ak.isNullOrEmpty(param.contrastTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
        		ak.confirm("确定要删除选中的【" + this.standardTypeName + "】记录吗？",this.delTypeCallback);
        	}
        },
        //删除标准分类回调函数
        delTypeCallback : function(instance){
        	//请求后台
        	var result = ak.msService("pm","PmContrastApi/deleteType",{ ids : this.tableSearchModel.contrastTypeId});
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
    		if(ak.isNullOrEmpty(param.contrastTypeId)){
    			ak.warning("请先选择根节点以外的标准分类");
    		}else{
    			ak.dialog("save","business/pm/PmContrast/PmContrastSave.html");
    		}
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/pm/PmContrast/PmContrastUpdate.html");
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
        	var result = ak.msService("pm","PmContrastApi/delete",{ ids : idsStr});
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
var mainAttr = getUtilMergeAttr({
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化采集table
    	this.ctTableInit("ct","VeCtObjectApi/list");
    	//初始化发布状态下拉框
    	this.selectInit("pm");
    	this.ctChannelList = this.selectSearch("010");
    	//默认请求采集数据
    	this.ctSearchClick();
    	//初始化tree
    	this.treeInit("pm","PmContrastApi/treeType");
    	//初始化对照table
    	this.tableInit("pm","PmContrastApi/list");
    	//默认排序
    	this.tableInitSort("source_code","asc");
    },
    methods : {
    	test : function(){
    		alert(1);
    	}
    }
});

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,ctTableAttr,treeAttr,tableAttr);
