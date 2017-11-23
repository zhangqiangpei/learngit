//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule202',
	//数据
	data : {
		rule202Model : [],
		rule202Data : [],
		elementRemark : null
	},
	methods : {
		//确定按钮
		rule202Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
    		//处理已选中数据
			localFiled.outField = new Array;
			objectData[checkId] = new Array;
    		
			//暂时将数据处理成map
			$.each(localFiled.inField,function(i,n){
				//处理输出字段信息
				localFiled.outField.push({
					"code":n.code,
					  "name":n.name,
					  "typeName":n.typeName,
					  "type":n.type
				});
				
				//处理本元素数据信息
				/*objectData[checkId].push({
					"code":n.code,
					  "name":n.name,
					  "typeName":n.typeName,
					  "type":n.type
				});*/
			});
			
			var count = 0;
			$.each(rule202Vue.rule202Model,function(i,n){
				for(var j=0;j<localFiled.outField.length;j++){
					if(n == localFiled.outField[j].code){
						localFiled.outField[j].type="017004";
						localFiled.outField[j].typeName="date";
						
						objectData[checkId].push({
							"code":localFiled.outField[j].code,
							  "name":localFiled.outField[j].name,
							  "typeName":"date",
							  "type":"017004"
						});
						/*objectData[checkId][j].type="017004";
						objectData[checkId][j].typeName="date";
						objectData[checkId][j].code=localFiled.outField[j].code;
						objectData[checkId][j].name=localFiled.outField[j].name;*/
						count++;
						break;
					}
				}
				
			});
			
			if(count < 1){
				ak.info("转换时间格式字段未选择！");
				return;
			}
			
			//关闭窗口
			rule202Vue.dialogClose();
		},
		//取消按钮
		rule202Cancel : function(){
			//关闭窗口
			rule202Vue.dialogClose();
		},
		//帮助说明按钮
		getPopoverInfo : function(){
			var checkObj = $("#" + checkId);
			this.elementRemark =  commonVue.getPmElementRemark(checkObj.attr("oid"));
		}
	},
	//页面初始化事件
    mounted: function () {
    	//获取本身输入信息
    	var localInTemps = new Array;
    	$.each(objectField[checkId].inField,function(i,n){
    		localInTemps.push({
    			key : n.code,
    			label : n.name,
    			disabled : null
    		});
    	});
    	this.rule202Data = localInTemps;
    	debugger;
    	//处理已选择信息
    	var datasTemp = new Array;
    	$.each(objectData[checkId],function(i,n){
    		//if(n.type == "017004"){
    			datasTemp.push(n.code);
    		//}
    	});
    	this.rule202Model = datasTemp;
    }
});

//生成vue对象
var rule202Vue = ak.getMergeVue(dialogAttr);