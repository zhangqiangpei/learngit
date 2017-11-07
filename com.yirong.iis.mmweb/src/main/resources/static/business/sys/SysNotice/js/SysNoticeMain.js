//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		senderAccount : null,
    		senderName : null,
    		state : null
        } 
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
 
        formatState:function(row,column){
        	if(0 == row[column.property]){
   			 	return  "待发送";
	   		}else{
	   			return "已发送";
	   		}
        },
        formatDate :function(row, column){
        	if (row[column.property] != null) {
        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
        	}	
        },
        //新增按钮
        addClick: function () {
    		ak.dialog("save","/business/sys/SysNotice/SysNoticeSave.html");
        },
        
        //删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.success("请选择需要删除的记录");
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
        	var result = ak.msService("sys","sysNoticeApi/delete",　{ids : ids});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        
        //发送
        sendClick: function () {
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.success("请选择需要发送的记录");
        	}else{
        		ak.confirm("确定要发送选中的【" + ids.length + "】条记录吗？",this.sendCallback);
        	}
        },
        
        //删除回调函数
        sendCallback : function(instance){
        	//处理参数
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	this.sendSerivce(idsStr);
        },
        //删除请求后台
        sendSerivce : function(ids){
        	var result = ak.msService("sys","sysNoticeApi/send",　{ids : ids});
        	if(null != result && result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
    
        //是否置顶
        onOrOff : function(row){
        	var result = ak.msService("sys","sysNoticeApi/isTop",{"id":row.id,"isTop":row.isTop});
        	if(null != result && result.code == 0){//操作成功
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
    	 
    	//初始化table
    	this.tableInit("sys","sysNoticeApi/queryList");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);