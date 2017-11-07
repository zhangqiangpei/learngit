//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		senderId : null,
    		receiverId : null
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
        formatSendState:function(row,column){
    		if(0 == row[column.property]){
    			 return  "待发送";
    		}else if(1 == row[column.property]){
    			 return  "成功";
    		}else{
    			 return  "失败";
    		}
        },
        formatState:function(row,column){
        	if(0 == row[column.property]){
   			 	return  "未读";
	   		}else{
	   			return "已读";
	   		}
        },
        formatDate :function(row, column){
        	if (row[column.property] != null) {
        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
        	}	
        },
        //新增按钮
        addClick: function () {
    		ak.dialog("save","/business/sys/SysMsgSendLog/SysMsgSendLogSave.html");
        }
        
    }
});
 
//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	 
    	//初始化table
    	this.tableInit("sys","msgLogApi/queryList");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);