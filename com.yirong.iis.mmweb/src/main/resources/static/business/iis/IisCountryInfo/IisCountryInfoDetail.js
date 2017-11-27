//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            iso2code : "",
            englishName : "",
            chineseName : "",
            continentCode : ""
        },
        continentCodeList : []
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        //新增按钮
        addClick: function () {
    		ak.dialog("save","business/ct/CtDatabase/CtDatabaseSave.html");
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/ct/CtDatabase/CtDatabaseUpdate.html");
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
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("mm","IisCountryInfoApi/list");
    	//默认排序
		this.tableInitSort("create_time","desc");
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);