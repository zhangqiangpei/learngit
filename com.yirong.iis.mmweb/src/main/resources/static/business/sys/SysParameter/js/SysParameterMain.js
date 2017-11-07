//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		paramKey : null,
    		paramName : null
        },
        //自定义参数
        appoptions : [],
        //编辑操作id
        handleEditId : ''
    },
    methods: {
    	//查询按钮
    	onSearch: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },

        //新增按钮
        addClick: function () {
    		ak.dialog("save","business/sys/SysParameter/SysParameterSave.html");
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
        	this.delSerivce(idsStr);
        },
        //删除请求后台
        delSerivce : function(ids){
        	var result = ak.msService("sys","sysParameterApi/delete",{ids : ids});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }else{
            	//提示
            	ak.warning(result.msg);
            }
        },
        //编辑操作
        handleEdit: function(index,row){
        	//获取id
        	this.handleEditId = row.id;
        	ak.dialog("update","business/sys/SysParameter/SysParameterUpdate.html");
        },
        formatDate :function(row, column){
        	if (row[column.property] != null) {
        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
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
    	this.tableInit("sys","sysParameterApi/queryList");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);