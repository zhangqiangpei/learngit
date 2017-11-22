//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule203',
	//数据
	data : {
		rule203Model : [],
		rule203Data : [],
		elementRemark : null
	},
	methods : {
		//确定按钮
		rule203Submit : function(){
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
			$.each(rule203Vue.rule203Model,function(i,n){
				for(var j=0;j<localFiled.outField.length;j++){
					if(n == localFiled.outField[j].code){
						localFiled.outField[j].type="017002";
						localFiled.outField[j].typeName="integer";
						
						
						objectData[checkId].push({
							"code":localFiled.outField[j].code,
							  "name":localFiled.outField[j].name,
							  "typeName":"integer",
							  "type":"017002"
						});
						/*objectData[checkId][j] = new Object();
						objectData[checkId][j].type="017002";
						objectData[checkId][j].typeName="integer";
						objectData[checkId][j].code=localFiled.outField[j].code;
						objectData[checkId][j].name=localFiled.outField[j].name;*/
						count++;
						break;
					}
				}
				
			});
			
			if(count < 1){
				ak.info("金额格式转换字段未选择！");
				return;
			}
			
			//关闭窗口
			rule203Vue.dialogClose();
		},
		//取消按钮
		rule203Cancel : function(){
			//关闭窗口
			rule203Vue.dialogClose();
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
    	this.rule203Data = localInTemps;
    	debugger;
    	//处理已选择信息
    	var datasTemp = new Array;
    	$.each(objectData[checkId],function(i,n){
    		//if(n.type == "017002"){
    			datasTemp.push(n.code);
    		//}
    	});
    	this.rule203Model = datasTemp;
    }
});

//生成vue对象
var rule203Vue = ak.getMergeVue(dialogAttr);