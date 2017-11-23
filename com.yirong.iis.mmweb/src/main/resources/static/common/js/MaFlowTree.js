//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#MaFlowTree',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("ma","MaFlowApi/list");
    	//默认排序
    	this.tableInitSort("flow_name","asc");
    	//请求后台
    	this.searchClick();
    }
});

//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        isShow:false,
      //查询对象
        tableSearchModel: {
        	flowName : null
        }
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
        rowSelect: function(row,event){
        	var data = new Object();
        	data.name = row.flow_name;
        	data.id = row.id;
        	//先回调
			commonVue.MaFlowCallBack(data);
            //再关闭窗口
			MaFlowTreeVue.dialogClose();
        }
    }
});

//生成vue对象
var MaFlowTreeVue = ak.getMergeVue(dialogAttr,tableAttr);