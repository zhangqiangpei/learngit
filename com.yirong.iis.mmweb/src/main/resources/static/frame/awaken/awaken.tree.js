/**tree公用属性继承类**/
//定义tree公用属性
function getTreeMergeAttr(attrs){
	//第一个参数必须为true，代表深层拷贝
	attrs = $.extend(true,{
		data: {
			//数据
			treeData: [],
			//属性
	        treeProps: {
	            children: 'children',
	            label: 'name'
	        },
	        //初始化参数
	        treeInitParam : {},
	        //最后一次查询参数
			treeLastParam : null,
			treeDataBF:[]
		},
		//方法
		methods: {
			//刷新树（根据最后一次查询参数）
			treeRefresh : function(){
				var param = this.treeLastParam;
				this.treeSearch(param);
			},
			//查询树数据
			treeSearch : function(param){
				//获取参数
				var serviceName = this.treeInitParam.serviceName;
				var pathName = this.treeInitParam.pathName;
				//获取数据
				this.treeLastParam = param;
				var result = ak.msService(serviceName,pathName,param);
				//处理数据
    			this.treeData = ak.ArrayToTreeData(result.data);
    			this.treeDataBF = result.data;
			},
			//初始化树
			treeInit : function(serviceName,pathName){
				this.treeInitParam.serviceName = serviceName;
				this.treeInitParam.pathName = pathName;
			}
		}
	},attrs);
	return attrs;
}