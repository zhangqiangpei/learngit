//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
            taskName : null
        },
        //选中的ID
        checkId : null
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
       //立即运行按钮
        doClick : function(index,row){
        	if("010001" == row.ct_channel){//爬虫
        		ak.info("爬虫类型任务不允许立即运行！");
        		return;
        	}
        	//获取表格选中的id集
        	this.checkId = row.id;
        	ak.confirm("确定立即运行该任务吗？",this.doClickCallback);
        },
        //立即运行回调函数
        doClickCallback : function(instance){
        	//请求后台
        	var result = ak.msService("pm","PmTaskApi/runTaskById",{ taksId : this.checkId});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            }
        },
        //重新预处理按钮
        doReClick : function(index,row){
        	//获取表格选中的id集
        	this.checkId = row.id;
        	ak.confirm("重新预处理将清空现有预处理数据，重新从采集数据中进行获取及预处理，确定要重新预处理吗？",this.doReClickCallback);
        },
        //重新处理回调函数
        doReClickCallback : function(instance){
        	//请求后台
        	var result = ak.msService("pm","PmTaskApi/doRe",{ id : this.checkId});
        	if(result.code == 0){//操作成功
            	//提示
            	ak.success(result.msg);
            }
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化动态table
    	this.tableInit("pm","PmTaskApi/list");
    	//默认排序
    	this.tableInitSort("task_name","asc");
    	//默认加载数据
    	this.tableSearch(this.tableSearchModel);
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);