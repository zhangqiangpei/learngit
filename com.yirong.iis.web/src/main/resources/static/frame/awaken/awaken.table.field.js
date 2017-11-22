//定义table Field公用属性
function getTableFieldMergeAttr(attrs){
	//第一个参数必须为true，代表深层拷贝
	attrs = $.extend(true,{
		//数据
		data: {
			//数据
			fieldList :[]
		},
		//方法
		methods: {
			//初始化查询表头字段微服务
			tableFieldInit : function(serviceName,pathName){
				this.tableInitParam.fserviceName = serviceName;
				this.tableInitParam.fpathName = pathName;
			},
			//表头字段初始化
			tableFieldList : function(param){
				//重置表格数据
    			this.tableData = [];
				//获取参数
				var serviceName = this.tableInitParam.fserviceName;
				var pathName = this.tableInitParam.fpathName;
				//获取数据
				var result = ak.msService(serviceName,pathName,param);
				//console.log(result);
				if(null == result){
					ak.error("系统错误");
				}else if(result.code == 0){//操作成功
					//处理结果
	    			this.fieldList = result.data;
				}else{
					ak.error(result.msg);
				}
			}
		}
	},getTableMergeAttr(attrs));
	return attrs;
}