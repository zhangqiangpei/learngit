/*//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.tableSearchModel.catalogId  = data.id;
            this.websiteGroupName = data.name;
            this.tableSearch(this.tableSearchModel);
            $("#catalogId").val(data.id);
            $("#catalogName").val(data.name);
        }
	}
});*/

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	websiteGroupName: null
        }
    },
    methods: {
    	//查询按钮
    	searchClick: function (formName) {
    		var param = this.tableSearchModel;
    		// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
            
        },
        //新增按钮
        addClick: function () {
        	ak.dialog("save","business/ct/CtWebsiteGroup/CtWebsiteGroupSave.html");
        },
        //修改按钮
        updateClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行修改");
        	}else{
        		ak.dialog("update","business/ct/CtWebsiteGroup/CtWebsiteGroupUpdate.html");
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
        	var result = ak.msService("ct","CtWebsiteGroupApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }else{
            	ak.warning(result.msg);
            }
        },
        ctWebsiteList:function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择一条记录进行查看网址");
        	}else{
        		ak.dialog("CtWebsiteMain","business/ct/CtWebsite/CtWebsiteMain.html");
        	}
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	/*//初始化树
    	this.treeInit("ct","CtCatalogApi/tree");*/
    	//初始化table
    	this.tableInit("ct","CtWebsiteGroupApi/list");
    	/*var order = this.tableSearchModel.orders[0];
    	order.orderField="create_time";
    	order.orderType="desc";*/
    	//请求后台
    	this.searchClick();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);