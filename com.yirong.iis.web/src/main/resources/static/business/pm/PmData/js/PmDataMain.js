//获取table属性
var tableAttr = getTableFieldMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            dynamicList : [["","","",true]]
        },
        //自定义参数
        flowName : null,
         //下拉框数组
        conditionList : [],
        // 下拉框数组
        conditionListTemp : []
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		if(ak.isNullOrEmpty(this.tableSearchModel.flowId)){
    			ak.warning("请先选择流程");
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
        },
        //添加条件
        addCondition : function(row,index){
        	if(row[0].length > 0 && row[1].length > 0 && row[2].length > 0){//所以字段均必须有值
        		this.tableSearchModel.dynamicList.push(["","","",true]);
        		row[3] = false;
        		this.initCondition(index+1);
        	}else{
        		ak.warning("请确保本行动态条件均有值");
        	}
        },
        //删除条件
        deleteCondition : function(index){
        	if(index != 0 ){
        		var list = this.tableSearchModel.dynamicList;
        		var listLength = list.length;
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
        //流程选择器点击事件
        changeClick : function(){
        	commonVue.PmFlowListCallBack = this.changeClickCallback;
			ak.dialog("PmFlowList","common/PmFlowList.html");
        },
        //目录选择器回调事件
        changeClickCallback : function(row){
        	//处理参数
        	this.flowName = row.flow_name;
        	this.tableSearchModel.flowId = row.id;
        	//处理动态表格信息
        	var result = this.tableFieldList({flowId : row.id});
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
        },
        //字段选择事件 
        fieldChange : function(value){
        	var strs = value.split("@");
        	var index = strs[1];
        	//获取当前选择的字段类型
        	var fieldType = null;
        	$.each(this.fieldList,function(i,n){
        		if(n.fieldCode == strs[0]){//为该字段
        			fieldType = n.fieldType;
        			return;//跳出循环
        		}
        	});
        	//处理变量
        	commonVue.doConditionListByfieldType(fieldType,this.conditionList[index]);
        	//默认将条件修改等"等于"
        	this.tableSearchModel.dynamicList[index][1] = "";
        	//输入框默认修改成允许输入
        	this.tableSearchModel.dynamicList[index][4] = false;
        },
        //初始化下拉框（新对象）
        initCondition : function(index){
        	//处理下拉框
        	var arrayTemp = new Array;
        	$.each(this.conditionListTemp,function(i,n){
        		n.disabled = false;//默认显示
        		arrayTemp.push($.extend({},n));
        	});
        	Vue.set(this.conditionList,index,arrayTemp);
        } 
    }
});

//主属性
var mainAttr = getUtilMergeAttr({
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化动态表格列
    	this.tableFieldInit("pm","PmDataApi/getField");
    	//初始化动态table
    	this.tableInit("pm","PmDataApi/list");
    	//初始化条件下拉框
    	this.selectInit("pm");
    	//初始化下拉框
    	this.conditionListTemp = this.selectSearch("018");
    	//初始化第一行条件
    	this.initCondition(0);
    } 
});

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);