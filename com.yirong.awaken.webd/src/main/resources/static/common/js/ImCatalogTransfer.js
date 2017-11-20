//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#showCatalog',
	//数据
	data : {
		showCatalogModel : [],
		showCatalogData : []
	},
	methods : {
		//确定按钮
		showCatalogSubmit : function(){
			//组装所需要的数据,已选择的目录列表,已选择的目录名
			var clName = "";
			for (var i = 0; i < this.showCatalogData.length; i++) {
				var n = this.showCatalogData[i];
				for (var j = 0; j < this.showCatalogModel.length; j++) {
					if(n.key == this.showCatalogModel[j]){
						clName += n.label;
						debugger;
						if(i != (this.showCatalogData.length-1)){
							clName += ",";
						}
						break;
					}
				}
			}
			commonVue.ImCatalogSubAdd(this.showCatalogModel,clName);//回填数据
			//关闭窗口
			showCatalogVue.dialogClose();
		},
		//取消按钮
		showCatalogCancel : function(){
			//关闭窗口
			showCatalogVue.dialogClose();
		} 
	},
	//页面初始化事件
    mounted: function () {
    	//获取所有的目录
    	var result = ak.msService("im","imCatalogApi/list",null).data;
    	console.log(result);
    	var localInTemps = new Array;
    	for (var i = 0; i < result.length; i++) {
    		var n = result[i];
    		localInTemps.push({
    			key : n.id,
    			label : n.catalogName,
    			disabled : null
    		});
		}
    	this.showCatalogData = localInTemps;
    }
});

//生成vue对象
var showCatalogVue = ak.getMergeVue(dialogAttr);