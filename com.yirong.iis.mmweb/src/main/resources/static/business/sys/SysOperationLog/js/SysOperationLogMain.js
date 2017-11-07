//获取下拉框属性
var selectAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
    	tableSearchModel: {
    		userName : null,
    		ip : null,
    		optState: null,
    		logLevel: null,
    		optEvent: null
        },
        //自定义参数
        stateoptions:[],
        leveloptions:[],
        eventoptions:[]
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
        	for(var i=0;i<this.stateoptions.length;i++){
        		if(this.stateoptions[i].value == row[column.property]){
        			 return  this.stateoptions[i].label;
        		}
        	}
        },
        formatLogLevel:function(row,column){
        	for(var i=0;i<this.leveloptions.length;i++){
        		if(this.leveloptions[i].value == row[column.property]){
        			 return  this.leveloptions[i].label;
        		}
        	}
        },
        formatEventCode:function(row,column){
        	for(var i=0;i<this.eventoptions.length;i++){
        		if(this.eventoptions[i].value == row[column.property]){
        			 return  this.eventoptions[i].label;
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
    	this.stateoptions = this.selectSearch("005");
    	this.leveloptions = this.selectSearch("006");
    	this.eventoptions = this.selectSearch("007");
    	 
    	//初始化table
    	this.tableInit("sys","logApi/opLog");
   	    //查询后台
    	this.onSearch();
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,selectAttr,tableAttr);