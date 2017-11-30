/**tree公用属性继承类**/
//定义工具公用属性
function getUtilMergeAttr(attrs){
	//第一个参数必须为true，代表深层拷贝
	attrs = $.extend(true,{
		data: {
			//初始化参数
			//下拉框参数
	        selectInitParam : {}
		},
		//方法
		methods: {
			//************************下拉框（码表操作）*****************
			//初始化下拉框微服务
			selectInit : function(serviceName){
				this.selectInitParam.serviceName = serviceName;
			},
			//下拉框（根据父节点code查询子节点数据）
			selectSearch : function(code){
				var serviceName = this.selectInitParam.serviceName;
				var pathName = "sysDictionaryApi/getCodeList";
				var param = {"code" : code};
				var result = ak.msService(serviceName,pathName,param);
				if(0 == result.code){
					return result.data;
				}
			},
			//下拉框（根据code查询节点名称）
			selectName : function(code){
				var serviceName = this.selectInitParam.serviceName;
				var pathName = "sysDictionaryApi/getCodeName";
				var param = {"code" : code};
				var result = ak.msService(serviceName,pathName,param);
				console.log(result);
				if(0 == result.code){
					return result.data;
				}
			},
			//************************table格式化方法*****************
			//后台实体返回date,进行格式化展示
			formatDate :function(row, column){
	        	if (row[column.property] != null) {
	        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
	        	}	
	        },
	       /* //启用/禁用switch开关,后台返回true和false,进行格式化展示
	        formatState: function(row, column){
	        	if(null != row[column.property]){
	        		return  row[column.property]=='000000' ? '启用' : '禁用';
	        	}
	        },*/
	        formatSelect: function(row, column){
	        	if(null != row[column.property]){
	        		return  this.selectName(row[column.property]);
	        	}
	        },
	        formatDateFromTime :function(row, column){
	        	if (row[column.property] != null) {
	        		return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property]));
	        	}	
	        },
	        //格式化任务专题
	        formatTaskState: function(row, column){
	        	if(null != row[column.property]){
	        		var stateName = '';
	        		if(row[column.property]=='WAITING'){
	        			stateName = '等待'
	        		}
	        		else if(row[column.property]=='PAUSED'){
	        			stateName = '暂停'
	        		}
					else if(row[column.property]=='ACQUIRED'){
						stateName = '正常执行'        			
					}
					else if(row[column.property]=='BLOCKED'){
						stateName = '阻塞'
					}
					else if(row[column.property]=='ERROR'){
						stateName = '错误'
					}
	        		
	        		return  stateName;
	        	}
	        }
	        
		}
	},attrs);
	return attrs;
}