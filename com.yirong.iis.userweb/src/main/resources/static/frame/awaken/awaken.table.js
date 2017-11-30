//定义table公用属性
function getTableMergeAttr(attrs){
	//第一个参数必须为true，代表深层拷贝
	attrs = $.extend(true,{
		//数据
		data: {
			//数据
			tableData :[],
			//总记录数
			tableTotal : 0,
			//公用查询参数
			tableSearchModel : {
				currentPage : 1,
				pageSize : 15,
				orders : []
			},
			//初始化参数
			tableInitParam : {
			},
			//是否加载过数据
			tableIsAlreadySearch : false,
			//最后一次查询参数
			tableLastParam : null,
			//选中行对象集
			tableSelection : null
		},
		//方法
		methods: {
	        //表格选中事件（适用复选框）
	        tableChange : function(selection){
	        	this.tableSelection = selection;
	        },
	        //表格选中事件（适用单行选择）
	        tableCurrentRowChange : function(currentRow, oldCurrentRow){
	        	this.tableSelection = new Array;
	        	this.tableSelection.push(currentRow);
	        },
			//获取表格选中ids
			tableGetCheckIds : function(){
				var ids = new Array;
				$.each(this.tableSelection,function(i,n){
					ids.push(n.id);
				});
				return ids;
			},
			//获取表格选中对象
			tableGetCheckObjs : function(){
				var objs = new Array;
				$.each(this.tableSelection,function(i,n){
					objs.push(n);
				});
				return objs;
			},
			//刷新表格（根据最后一次查询参数）
			tableRefresh : function(){
				var param = this.tableLastParam;
				this.tableSearch(param);
			},
			//查询表格
			tableSearch : function(param){
				//获取参数
				var serviceName = this.tableInitParam.serviceName;
				var pathName = this.tableInitParam.pathName;
				//获取数据
				this.tableLastParam = param;
				var result = ak.msService(serviceName,pathName,param);
				if(!this.tableIsAlreadySearch){
					this.tableIsAlreadySearch = true;
				}
				if(0 !== result.code){
					ak.error(result.msg);
				}else{
					this.tableTotal = result.data.totalCount;
					this.tableData = result.data.data;
				}
				
				setTimeout(function (){
    				awakenCommonVue.initMenu();
    			},1);
				 
			},
			//初始化表格
			tableInit : function(serviceName,pathName){
				this.tableInitParam.serviceName = serviceName;
				this.tableInitParam.pathName = pathName;
				//初始化高度
				awakenCommonVue.initHeight("tableId");
			},
			//重置表格查询条件
			tableResetCondition: function () {
            	this.$refs["tableSearchModel"].resetFields();
        	},
        	//每页数据条数选择事件
        	tableSizeChange: function (val) {
        		//处理参数
        		var param = this.tableSearchModel;
	            param.currentPage = 1;
	            param.pageSize = val;
	            //请求后台（查询过才请求）
	            if(this.tableIsAlreadySearch){
	            	 this.tableSearch(param);
	            }
	        },
	        //当前页选择事件
	        tableCurrentChange: function (val) {
	        	//处理参数
	            var param = this.tableSearchModel;
	            param.currentPage = val;
	            //请求后台
	            this.tableSearch(param);
	        },
	        //排序事件
	        tableSortChange : function(column){
	        	if(null == column.order){//不排序
	        		this.tableSearchModel.orders = [];
	        	}else{//需要排序
	        		this.tableInitSort(column.prop,column.order.replace("ending",""));
	        	}
    			//刷新表格
    			this.tableSearch(this.tableSearchModel);
	        },
	        //初始化表格排序
	        tableInitSort : function(field,type){
	        	this.tableSearchModel.orders = [];
	        	this.tableSearchModel.orders.push({
					orderField : field,
					orderType : type
				});
	        }
		}
	}, attrs);
	return attrs;
}