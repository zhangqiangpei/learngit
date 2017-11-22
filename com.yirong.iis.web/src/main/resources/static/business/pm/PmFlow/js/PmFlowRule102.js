//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule102',
	//数据
	data : {
		rule102Model : [],
		rule102Data : [],
		elementRemark : null
	},
	methods : {
		//确定按钮
		rule102Submit : function(){
			//获取本身信息
    		var localFiled = objectField[checkId];
			//去重，输出字段不变（所以复制即可）
			localFiled.outField = new Array;
			$.each(localFiled.inField,function(i,n){
				localFiled.outField.push(n);
			});
			//处理已选中数据
			objectData[checkId] = new Array;
			$.each(this.rule102Model,function(i,n){
				//处理本元素数据信息
				objectData[checkId].push(n);
			});
			//关闭窗口
			rule102Vue.dialogClose();
		},
		//取消按钮
		rule102Cancel : function(){
			//关闭窗口
			rule102Vue.dialogClose();
		},
		//帮助说明按钮
		getPopoverInfo : function(){
			var checkObj = $("#" + checkId);
			this.elementRemark =  commonVue.getPmElementRemark(checkObj.attr("oid"));
		}
	},
	//页面初始化事件
    mounted: function () {
    	//获取本身信息
    	var localFiled = objectField[checkId];
    	//获取本身输入信息
    	var localIn = localFiled.inField;
    	var localInTemps = new Array;
    	$.each(localIn,function(i,n){
    		localInTemps.push({
    			key : n.code,
    			label : n.name,
    			disabled : null
    		});
    	});
    	this.rule102Data = localInTemps;
    	//处理已选择信息
		var datasTemp = new Array;
    	$.each(objectData[checkId],function(i,n){
    		datasTemp.push(n);
    	});
    	this.rule102Model = datasTemp;
    }
});

//生成vue对象
var rule102Vue = ak.getMergeVue(dialogAttr);