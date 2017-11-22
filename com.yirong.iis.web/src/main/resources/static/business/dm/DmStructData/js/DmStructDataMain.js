//获取table属性
var tableAttr = getTableFieldMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            dynamicList : [["","","",true]]
        },
        //自定义参数
        catalogName : null,
         //下拉框数组
        conditionList : []
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		if(ak.isNullOrEmpty(this.tableSearchModel.catalogId)){
    			ak.warning("请先选择目录");
    			return;
    		}
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        //重置按钮
        psdTableResetCondition : function(){
        	this.tableSearchModel.dynamicList = [["","","",true]];
        	this.tableSearchModel.catalogId = null;
        	this.catalogName = null;
        },
        //添加条件
        addCondition : function(row){
        	if(row[0].length > 0 && row[1].length > 0 && row[2].length > 0){//所以字段均必须有值
        		this.tableSearchModel.dynamicList.push(["","","",true]);
        		row[3] = false;
        	}else{
        		ak.warning("请确保本行动态条件均有值");
        	}
        },
        //删除条件
        deleteCondition : function(index){
        	if(index != 0 ){
        		var list = this.tableSearchModel.dynamicList;
	        	//删除本行
	        	list.splice(index,1);
	        	//开启上一行添加按钮
	        	if(index == (listLength-1)){//最后一行
	        		list[index - 1][3] = true;
	        	}
        	}else{
        		ak.warning("至少保留一行动态查询条件");
        	}
        },
        //目录选择器点击事件
        changeClick : function(){
        	commonVue.DmCatalogTreeCallBack = this.changeClickCallback;
			ak.dialog("DmCatalogTree","common/DmCatalogTree.html");
        },
        //目录选择器回调事件
        changeClickCallback : function(data){
        	//处理参数
        	this.catalogName = data.name;
        	this.tableSearchModel.catalogId = data.id;
        	//处理动态表格信息
        	var result = this.tableFieldList({catalogId : this.tableSearchModel.catalogId});
	    	if(result && result.code == 0 ){
	    		this.fieldList = result.data;
	    	}
        },
       //列格式化
        columnFormatter : function(row,column){
        	var result = row[column.property];
        	//循环获取本列类型
        	var fieldType = null;
        	$.each(this.fieldList,function(i,n){
        		if(n.fieldCode == column.property){//本列
        			fieldType = n.fieldType;
        			return;
        		}
        	});
        	//时间类型
        	if("017004" == fieldType){
        		result = ak.date.format("yyyy-MM-dd hh:mm:ss",new Date(result.time));
        	}
        	return result;
        }
    }
});

//主属性
var mainAttr = getUtilMergeAttr({
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化动态表格列
    	this.tableFieldInit("pm","DmStructDataApi/getField");
    	//初始化动态table
    	this.tableInit("pm","DmStructDataApi/list");
    	//初始化条件下拉框
    	this.selectInit("pm");
    	this.conditionList = this.selectSearch("018");
    } 
});

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);