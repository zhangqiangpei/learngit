//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	state:0,
        	receiverId:null,
        	orders : [{
				orderField : "createTime",
				orderType : "desc"
			}]
        },
        activeName: 'unRead'
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
        read: function () {
	    	this.tableSearchModel.state=1
			var param = this.tableSearchModel;
			// 当前页切换成第一页
	    	param.page = 1;
	   	    //查询后台
	    	this.tableSearch(param);
	    },
	    unRead: function () {
	    	this.tableSearchModel.state=0
			var param = this.tableSearchModel;
			// 当前页切换成第一页
	    	param.page = 1;
	   	    //查询后台
	    	this.tableSearch(param);
	    },
	    all: function () {
	    	this.tableSearchModel.state=""
			var param = this.tableSearchModel;
			// 当前页切换成第一页
	    	param.page = 1;
	   	    //查询后台
	    	this.tableSearch(param);
	    },
	    showDetail : function(index,row){
	    	id=index.id
	    	var url = "common/SysMsgSendLogDetail.html"
	              url = "forward.do?viewPath=" + url+"&id="+id;
	            window.open(url);
	        //刷新 
	        setTimeout(function(){mainVue.tableRefresh();},1000);
	    },
	  //后台实体返回date,进行格式化展示
		formatDate :function(row, column){
        	if (row[column.property] != null) {
        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
        	}	
        },
	    formatState :function(row, column){
        	if (row[column.property] != null) {
        		return  row[column.property]=="0"?"未读":"已读";
        	}	
        },
        handleClick:function(tab, event){
        	if("unRead"== tab.name){
        		this.unRead()
        	}else if("read"== tab.name){
        		this.read();
        	}else if("all"== tab.name){
        		this.all();
        	}
        }
        
    }
});

//主属性
var mainAttr ={
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("sys","msgLogApi/queryList");
    	//参数
    	var query = location.search.substring(1);  
    	var values= query.split("loginUser=");
    	//用户非空才查询
    	if(ak.isNotNullOrEmpty(values[1])){
    	   	this.tableSearchModel.receiverId=values[1];
    		//默认排序
        	this.tableInitSort("createTime","DESC");
        	//请求后台
        	this.searchClick();
        	//加载初始化数据
    	}
 
    }
}


//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);