//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取树形属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//树节点单击事件
		treeNodeClick: function (data, node, el) {
            this.tableSearchModel.treeCode  = data.atttibute.code;
            this.orgName = data.name;
            this.orgId = data.id;
            this.orgCode = data.atttibute.code;
            this.tableSearch(this.tableSearchModel);
        }
	}
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	name : null,
        	code: null,
        	treeCode:null
        },
        //自定义参数
        typeoptions:[],
        //机构名称（树节点单击时赋值）
        orgName : '',
        orgId : '',
        orgCode : '',
        //记录id（编辑时赋值）
        handleEditId  : ''
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(this.tableSearchModel);
        },
        //新增按钮
        addClick: function () {
    		//if(ak.isNullOrEmpty(this.orgId)){
    		//	ak.info("请先选择机构目录");
    		//}else{
    			ak.dialog("save","/business/sys/SysOrgManage/SysOrgManageSave.html");
    		//}
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
        	var result = ak.msService("sys","organizationApi/delete",{ ids : idsStr});
        	/*if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            	//刷新树
            	mainVue.treeRefresh();
            }else{
            	
            }*/
        	
        	if(null != result && result.code == 0){//操作成功
    			//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            	//刷新树
            	mainVue.treeRefresh();
            	
            	for(var i=0;i<mainVue.treeDataBF.length;i++){
            		//debugger;
        			var node = mainVue.treeDataBF[i];
        			if(node.atttibute.code != mainVue.orgCode && i == mainVue.treeDataBF.length-1){
        				mainVue.tableSearchModel.treeCode  = null;
        				mainVue.orgName = '';
        				mainVue.orgId = '';
        				mainVue.orgCode = '';
        				mainVue.tableSearch(mainVue.tableSearchModel);
        			}else if(node.atttibute.code == mainVue.orgCode){
        				break;
        			}
        		}
    		}
        },
        //编辑操作
        handleEdit: function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	ak.dialog("update","/business/sys/SysOrgManage/SysOrgManageUpdate.html");
        },
        formatType : function(row,column){
        	for(var i=0;i<this.typeoptions.length;i++){
        		if(this.typeoptions[i].value == row[column.property]){
        			 return  this.typeoptions[i].label;
        		}
        	}
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
    
    	//推送频率下拉框数据
    	this.typeoptions = this.selectSearch("001");
    	
    	//初始化树
    	this.treeInit("sys","organizationApi/getOrgTree");
    	
    	//初始化table
    	this.tableInit("sys","organizationApi/list");
    	
    	//查询树 请求后台数据数据
    	this.treeSearch({"code" :''});
    	
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,treeAttr,tableAttr,selectAttr);

window.onload=function(){
	//设置树  span节点title属性
	$("span[class^='el-tree-node__label']").each(function(){
	    $(this).attr("title",$(this).text());
	});
}