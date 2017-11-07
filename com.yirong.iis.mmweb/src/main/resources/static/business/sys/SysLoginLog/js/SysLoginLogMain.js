//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		userName : null,
    		ip : null,
    		isSuccess: null,
    		isOnline: null
        },
        loginoptions:[],
        onlineoptions:[]
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
        onlineFormat:function(row,column){
        	for(var i=0;i<this.onlineoptions.length;i++){
        		if(this.onlineoptions[i].value == row[column.property]){
        			 return  this.onlineoptions[i].label;
        		}
        	}
        },
        successFormat:function(row,column){
        	for(var i=0;i<this.loginoptions.length;i++){
        		if(this.loginoptions[i].value == row[column.property]){
        			 return  this.loginoptions[i].label;
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
    	
    	//下拉框数据
    	this.loginoptions = this.selectSearch("003");
    	this.onlineoptions = this.selectSearch("004");
    	
    	//初始化table
    	this.tableInit("sys","logApi/loginLog");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);