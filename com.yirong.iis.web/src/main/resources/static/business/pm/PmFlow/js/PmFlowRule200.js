//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule200',
	//数据
	data : {
		rule200Model : [],
		rule200Data : [],
		elementRemark : null
	},
	methods : {
		//确定按钮
		rule200Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
			//暂时将数据处理成map
			var mapTemp = new Array;
			$.each(localFiled.inField,function(i,n){
				mapTemp[n.code] = n;
			});
			//处理已选中数据
			localFiled.outField = new Array;
			objectData[checkId] = new Array;
			$.each(this.rule200Model,function(i,n){
				//处理输出字段信息
				localFiled.outField.push(mapTemp[n]);
				//处理本元素数据信息
				objectData[checkId].push(n);
			});
			//关闭窗口
			rule200Vue.dialogClose();
		},
		//取消按钮
		rule200Cancel : function(){
			//关闭窗口
			rule200Vue.dialogClose();
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
    	this.rule200Data = localInTemps;
    	//处理已选择信息
    	var datasTemp = new Array;
    	$.each(objectData[checkId],function(i,n){
    		datasTemp.push(n);
    	});
    	this.rule200Model = datasTemp;
    }
});

//生成vue对象
var rule200Vue = ak.getMergeVue(dialogAttr);