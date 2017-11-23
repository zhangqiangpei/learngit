//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule300',
	//数据
	data : {
		conditionList : [],
		nameList : [],
		tableData : [],
		elementRemark : null,
		fieldTypes : [],
		rule300Model : [],
		rule300Data : [],
		isCount : false,
		countName:"",
		countCode:"",
		countNameShow:false,
		countCodeShow:false,
		fieldTypesName : []
	},
	methods : {
		//确定按钮
		rule300Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
    		//数据转换，输出数据字段为统计+分组+新增输出字段
			var outField = new Array;
			var datas = new Array;//data
			var countJson = {};
			var groupDatas = new Array;
			if(this.isCount){
				if(this.countName == ""||this.countCode == ""){
					ak.info("计数中英文名称不能为空！");
					return;
				}
				
				countJson.isCount = 1;
				countJson.code = this.countCode;
				countJson.name = this.countName;
				
				outField.push({
					"code": this.countCode,
		            "name": this.countName,
		            "typeName": "integer",
		            "type": "017002"
				});
			}else{
				countJson.isCount = 0;
			}
			
			//暂时将数据处理成map
			var mapTemp = new Array;
			$.each(localFiled.inField,function(i,n){
				mapTemp[n.code] = n;
			});
			
			$.each(this.rule300Model,function(i,n){
				//处理输出字段信息
				outField.push(mapTemp[n]);
				groupDatas.push(mapTemp[n]);
			});
			
			var fieldTypes = this.fieldTypes;
			var fieldTypesName = this.fieldTypesName;
			var tableFlag = false;
			$.each(this.tableData,function(i,n){
				if(ak.isNullOrEmpty(n.name) || ak.isNullOrEmpty(n.operationMode) || ak.isNullOrEmpty(n.newCode)
						|| ak.isNullOrEmpty(n.newName)){
					tableFlag = true;
					return;
				}
				
				outField.push({
					code: n.newCode,
		            name: n.newName,
		            typeName: fieldTypesName[n.name],
		            type: fieldTypes[n.name]
				});
				
				datas.push({
					code : n.name,
					name : n.name,
					type : fieldTypes[n.name],
					operationMode : n.operationMode,
					newName : n.newName,
					newCode : n.newCode
				});
			});
			
			if(tableFlag){
				ak.info("字段名称、操作方式、新字段中文名称、新字段英文名称为必填字段，不可为空！");
				return;
			}
			
			for(var i=0;i<outField.length;i++){
				for(var j=i+1;j<outField.length;j++){
					if(outField[i].code == outField[j].code || outField[i].name == outField[j].name){
						ak.info("中文英文字段名称不可重复！");
						return;
					}
				}
			}
			
			var ids = mainVue.tableGetCheckIds();
			//处理已修改的数据
			objectData[checkId] = {
				countJson : countJson,
				datas : datas,//条件集合
				groupDatas : groupDatas,
				flowId:ids[0]//流程id
			};
			localFiled.outField = outField;
			
			//关闭窗口
			rule300Vue.dialogClose();
		},
		//取消按钮
		rule300Cancel : function(){
			//关闭窗口
			rule300Vue.dialogClose();
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
				if(ak.isNullOrEmpty(n.name) || ak.isNullOrEmpty(n.operationMode) || ak.isNullOrEmpty(n.newName) || ak.isNullOrEmpty(n.newCode)){
					isOk = false;
					return;
				}
			});
			if(!isOk){
				ak.info("存在字段名称、操作方式及新字段名称为空的数据，请先填写后再添加");
				return;
			}
			
			this.tableData.push({
				name : "",
				operationMode : "",
				newName : "",
				newCode : ""
			});
		},
		//删除条件按钮
		delCondition : function(index,row){
	        //删除本行
	        this.tableData.splice(index,1);
	        
	        var rule300DataN = this.rule300Data;
			for(var j=0;j<rule300DataN.length;j++){
				rule300DataN[j].disabled = false;
			}
			
			$.each(this.tableData,function(i,n){
				if(ak.isNotNullOrEmpty(n.name)){
					for(var j=0;j<rule300DataN.length;j++){
						if(n.name == rule300DataN[j].key){
							rule300DataN[j].disabled = true;
							break;
						}
					}
				}
			});
		},
		countChange : function(){//计数checkbox值变化	
			if(this.isCount){//参与计数
				this.countName = "计数";
				this.countCode = "count";
				this.countNameShow = true;
				this.countCodeShow = true;
			}else{
				this.countName = "";
				this.countCode = "";
				this.countNameShow = false;
				this.countCodeShow = false;
			}
		},
		groupChange : function(){//分组列表变化
			var dataTemp = new Array;
			var rule300ModelN = this.rule300Model;
			$.each(objectField[checkId].inField,function(i,n){
				for(var j=0;j< rule300ModelN.length;j++){
					if(n.code == rule300ModelN[j]){
						break;
					}else if(n.code != rule300ModelN[j] && j == rule300ModelN.length-1){
						dataTemp.push({
			    			key : n.code,
			    			label : n.name,
			    			value : n.code
			    		});
					}
				}
			});
			this.nameList = dataTemp;
		},
		fieldChange : function(index,row){//字段下拉框改变
			$.each(this.tableData,function(i,n){
				if(i!=index && n.name == row.name){
					row.name = "";
					ak.info("字段名称已被选中!");
					return;
				}
			});
			
			var rule300DataN = this.rule300Data;
			for(var j=0;j<rule300DataN.length;j++){
				rule300DataN[j].disabled = false;
			}
			
			$.each(this.tableData,function(i,n){
				if(ak.isNotNullOrEmpty(n.name)){
					for(var j=0;j<rule300DataN.length;j++){
						if(n.name == rule300DataN[j].key){
							rule300DataN[j].disabled = true;
							break;
						}
					}
				}
			});
			this.operationModeChange(index,row);
			for(var i=0;i<this.nameList.length;i++){
				if(row.name == this.nameList[i].key){
					row.newName = this.nameList[i].label;
					break;
				}
			}
			if(row.name == ""){
				row.newName = "";
			}
			row.newCode = row.name;
		},
		operationModeChange : function(index,row){//操作模式下拉框改变
			
			switch(row.operationMode){
				case "011001"://默认
				  break;
				case "011002"://最小值
				  break;
				case "011003"://最大值
				  break;
				case "011004"://求和
				  if(row.name == ""){
					  return;
				  }
				  if(this.fieldTypes[row.name]!="017002" && this.fieldTypes[row.name]!="017005"){
					  row.operationMode = "";
					  ak.info("字段类型为"+this.fieldTypesName[row.name]+"类型不能参与求和！");
				  }
				  break;
				default:;
			}
		}
	},
	//页面初始化事件
    mounted: function () {
    	//获取输入信息
    	var inField = objectField[checkId].inField;
    	/**初始化字段信息**/
    	var dataTemp = new Array;
    	var filedTemp = new Array;
    	var filedTempName = new Array;
    	
    	//循环装入字段对象
    	$.each(inField,function(i,n){
    		dataTemp.push({
    			key : n.code,
    			label : n.name,
    			value : n.code
    		});
    		filedTemp[n.code] = n.type;
    		filedTempName[n.code] = n.typeName;
    	});
    	this.fieldTypes = filedTemp;
    	this.fieldTypesName = filedTempName;
    	this.nameList = dataTemp;
    	/**初始化条件下拉框信息**/
    	this.selectInit("pm");
    	this.conditionList = this.selectSearch("011");
    	/**初始化数据**/
    	if(objectData[checkId]){
	    	var datasTemp = new Array;
	    	$.each(objectData[checkId].datas,function(i,n){
	    		datasTemp.push($.extend({}, n));
			});
			this.tableData = datasTemp;
			
			if(objectData[checkId].countJson.isCount == 1){
				this.isCount = true;
				this.countName = objectData[checkId].countJson.countName;
				this.countCode = objectData[checkId].countJson.countCode;
				this.countChange();
			}
    	}
    	
    	var localInTemps = new Array;
    	$.each(objectField[checkId].inField,function(i,n){
    		localInTemps.push({
    			key : n.code,
    			label : n.name,
    			disabled : null
    		});
    	});
    	this.rule300Data = localInTemps;
    	//处理已选择信息
    	var datasTemp1 = new Array;
    	if(objectData[checkId]){
	    	$.each(objectData[checkId].groupDatas,function(i,n){
	    		datasTemp1.push(n.code);
	    	});
    	}
    	this.rule300Model = datasTemp1;
    	
    	var rule300DataN = this.rule300Data;
		for(var j=0;j<rule300DataN.length;j++){
			rule300DataN[j].disabled = false;
		}
		
		$.each(this.tableData,function(i,n){
			if(ak.isNotNullOrEmpty(n.name)){
				for(var j=0;j<rule300DataN.length;j++){
					if(n.name == rule300DataN[j].key){
						rule300DataN[j].disabled = true;
						break;
					}
				}
			}
		});
    }
});

//工具属性
var utilAttr = getUtilMergeAttr({});

//表格属性
var tableAttr = getTableMergeAttr({});

//生成vue对象
var rule300Vue = ak.getMergeVue(dialogAttr,utilAttr,tableAttr);