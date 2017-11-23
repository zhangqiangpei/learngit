//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule901',
	//数据
	data : {
		rule901Model : [],
		rule901Data : [],
		elementRemark : null,
		tableData : [],
		tableDataArray : [
			["关注分类","careClassify","integer","017002"]
		]
	},
	methods : {
		//确定按钮
		rule901Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
    		objectData[checkId] = {
				request : [],
				response : []
			};
			//暂时将数据处理成map
			var mapTemp = new Array;
			$.each(localFiled.inField,function(i,n){
				mapTemp[n.code] = n;
			});
			//处理已选中数据
			$.each(this.rule901Model,function(i,n){
				//处理本元素数据信息
				objectData[checkId].request.push(mapTemp[n]);
			});
			/**处理输出字段信息**/
			localFiled.outField = new Array;
			//输出字段需包括上一个流程的输出字段
			$.each(localFiled.inField,function(i,n){
				//输出字段
				localFiled.outField.push(n);
			});
			//再包含本次新增的字段
			$.each(this.tableData,function(i,n){
				//输出字段
				localFiled.outField.push(n);
				//数据信息
				objectData[checkId].response.push(n);
			});
			
			if(objectData[checkId].request.length < 1){
				ak.info("参与提取的字段未选择！");
				return;
			}
			
			//关闭窗口
			rule901Vue.dialogClose();
		},
		//取消按钮
		rule901Cancel : function(){
			//关闭窗口
			rule901Vue.dialogClose();
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
    	this.rule901Data = localInTemps;
    	//处理表格信息
    	var tableDatasTemp = new Array;
    	$.each(this.tableDataArray,function(i,n){
    		tableDatasTemp.push({
    			code : n[1],
    			name : n[0],
    			type : n[3],
    			typeName : n[2]
    		});
    	});
    	this.tableData = tableDatasTemp;
    	//处理已选择信息
    	if(objectData[checkId]){
    		var datasTemp = new Array;
	    	$.each(objectData[checkId].request,function(i,n){
	    		datasTemp.push(n.code);
	    	});
	    	this.rule901Model = datasTemp;
    	}
    }
});

//生成vue对象
var rule901Vue = ak.getMergeVue(dialogAttr);