//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule900',
	//数据
	data : {
		rule900Model : [],
		rule900Data : [],
		elementRemark : null,
		tableData : [],
		tableDataArray : [
			["发布日期","public_date","string","017001"],
			["截止日期","end_date","string","017001"],
			["开标日期","open_date","string","017001"],
			["中标日期","win_date","string","017001"],
			["文件获取日期","file_date","string","017001"],
			["预算金额","budget_price","string","017001"],
			["成交金额","deal_price","string","017001"],
			["采购金额","buy_price","string","017001"],
			["开标金额","open_price","string","017001"],
			["中标金额","win_price","string","017001"],
			["入围金额","enter_price","string","017001"],
			["项目","project","string","017001"],
			["代理机构","proxy","string","017001"],
			["代理机构地址","proxy_address","string","017001"],
			["代理机构电话","proxy_telephone","string","017001"],
			["代理机构联系人","proxy_contacter","string","017001"],
			["代理机构联系人手机","proxy_contacter_mobile","string","017001"],
			["代理机构联系人邮箱","proxy_contacter_email","string","017001"],
			["采购机构","buyer","string","017001"],
			["采购机构地址","buyer_address","string","017001"],
			["采购机构电话","buyer_telephone","string","017001"],
			["采购机构联系人","buyer_contacter","string","017001"],
			["采购机构联系人手机","buyer_contacter_mobile","string","017001"],
			["采购机构联系人邮箱","buyer_contacter_email","string","017001"],
			["投标文件递交地址","file_mail_address","string","017001"],
			["资质","qualification","string","017001"],
			["省","province","string","017001"],
			["市","city","string","017001"],
			["区","area","string","017001"],
			["招标类型","bid_type","string","017001"],
			["公告类型","title_type","string","017001"],
			["境内注册","registered_in_china","string","017001"],
			["接受联合体投标","allow_union","string","017001"],
			["项目(招标)编号","bid_number","string","017001"],
			["招标服务单位","service_provider","string","017001"],
			["评标委员会","committee","string","017001"],
			["招标方式","bid_method","string","017001"],
			["采购方式","buy_method","string","017001"],
			["工期","duration","string","017001"],
			["开标地址","open_address","string","017001"],
			["文件获取开始日期","file_start_date","string","017001"],
			["文件获取结束日期","file_end_date","string","017001"],
			["公示时间","publicity_time","string","017001"]
		]
	},
	methods : {
		//确定按钮
		rule900Submit : function(){
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
			$.each(this.rule900Model,function(i,n){
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
			//关闭窗口
			rule900Vue.dialogClose();
		},
		//取消按钮
		rule900Cancel : function(){
			//关闭窗口
			rule900Vue.dialogClose();
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
    	this.rule900Data = localInTemps;
    	//处理表格信息
    	var tableDatasTemp = new Array;
    	$.each(this.tableDataArray,function(i,n){
    		tableDatasTemp.push({
    			code : n[1],
    			name : n[0],
    			type : n[3],
    			typeName : n[2]
    		})
    	});
    	this.tableData = tableDatasTemp;
    	//处理已选择信息
    	if(objectData[checkId]){
    		var datasTemp = new Array;
	    	$.each(objectData[checkId].request,function(i,n){
	    		datasTemp.push(n.code);
	    	});
	    	this.rule900Model = datasTemp;
    	}
    }
});

//生成vue对象
var rule900Vue = ak.getMergeVue(dialogAttr);