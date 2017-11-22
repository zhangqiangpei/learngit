//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule101',
	//数据
	data : {
		rule101Radio : 0,
		conditionList : [],
		nameList : [],
		tableData : [],
		elementRemark : null,
		fieldTypes : []
	},
	methods : {
		//确定按钮
		rule101Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
    		//数据转换，输出数据字段不变（所以复制即可）
			localFiled.outField = new Array;
			$.each(localFiled.inField,function(i,n){
				localFiled.outField.push(n);
			});
			//处理已修改的数据
			objectData[checkId] = {
				conditionType : this.rule101Radio,//条件类别
				datas : []//条件集合
			};
			var fieldTypes = this.fieldTypes;
			$.each(this.tableData,function(i,n){
				if(ak.isNotNullOrEmpty(n.name) && ak.isNotNullOrEmpty(n.condition)){
					objectData[checkId].datas.push({
						code : n.name,
						name : n.name,
						type : fieldTypes[n.name],
						condition : n.condition,
						conditionValue : n.conditionValue
 					});
				}
			});
			//关闭窗口
			rule101Vue.dialogClose();
		},
		//取消按钮
		rule101Cancel : function(){
			//关闭窗口
			rule101Vue.dialogClose();
		},
		//帮助说明按钮
		getPopoverInfo : function(){
			var checkObj = $("#" + checkId);
			this.elementRemark =  commonVue.getPmElementRemark(checkObj.attr("oid"));
		},
		//新增条件按钮
		saveCondition : function(){
			var isOk = true;
			$.each(this.tableData,function(i,n){
				if(ak.isNullOrEmpty(n.name) && ak.isNullOrEmpty(n.condition)){
					isOk = false;
					return;
				}
			});
			if(!isOk){
				ak.info("存在字段名称及条件均为空的数据，请先填写后再添加");
				return;
			}
			this.tableData.push({
				name : "",
				condition : "",
				conditionValue : ""
			});
		},
		//删除条件按钮
		delCondition : function(index,row){
	        //删除本行
	        this.tableData.splice(index,1);
		}
	},
	//页面初始化事件
    mounted: function () {
    	//获取输入信息
    	var inField = objectField[checkId].inField;
    	/**初始化字段信息**/
    	var dataTemp = new Array;
    	var filedTemp = new Array;
    	//循环装入字段对象
    	$.each(inField,function(i,n){
    		dataTemp.push({
    			key : n.code,
    			label : n.name,
    			value : n.code
    		});
    		filedTemp[n.code] = n.type;
    	});
    	this.fieldTypes = filedTemp;
    	this.nameList = dataTemp;
    	/**初始化条件下拉框信息**/
    	this.selectInit("pm");
    	this.conditionList = this.selectSearch("018");
    	/**初始化数据**/
    	if(objectData[checkId]){
    		this.rule101Radio = objectData[checkId].conditionType;
	    	var datasTemp = new Array;
	    	$.each(objectData[checkId].datas,function(i,n){
	    		datasTemp.push($.extend({}, n));
			});
			this.tableData = datasTemp;
    	}
    }
});

//工具属性
var utilAttr = getUtilMergeAttr({});

//表格属性
var tableAttr = getTableMergeAttr({});

//生成vue对象
var rule101Vue = ak.getMergeVue(dialogAttr,utilAttr,tableAttr);