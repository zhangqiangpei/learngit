

//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#CtWebsiteMain',
	//页面初始化事件
    mounted: function () {
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	this.tableSearchModel.websiteGroupId = checkObj.id;
    	//初始化树
    	this.tableInit("ct","CtWebsiteApi/list");
    	//获取外面传入的参数
    	var param = commonVue.PmStandardListParam;
    	//与本选择器合并
    	param = $.extend(true,this.tableSearchModel,param);
    	//请求后台数据数据
    	this.tableSearch(this.tableSearchModel);
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	websiteGroupId : null,
        	websiteAddress: null
        },
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
        restClick: function () {
        	this.$refs["tableSearchModel"].resetFields();
        	var param = this.tableSearchModel;
    		// 当前页切换成第一页
            param.page = 1;
           	//查询后台
            this.tableSearch(param);
        },
        //新增按钮
        addClick:function(){
    		ak.dialog("save","business/ct/CtWebsite/CtWebsiteSave.html");
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
        	var result = ak.msService("ct","CtWebsiteApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	CtWebsiteMainVue.tableRefresh();
            }
        },
    }
});

//生成vue对象
var CtWebsiteMainVue = ak.getMergeVue(dialogAttr,selectAttr,tableAttr);