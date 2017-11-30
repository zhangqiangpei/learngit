//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#showUser',
	//数据
	data : {
		showUserModel : [],
		showUserData : []
	},
	methods : {
		//确定按钮
		showUserSubmit : function(){
			//组装所需要的数据,已选择的目录列表,已选择的目录名
			var uName = "";
			for (var i = 0; i < this.showUserData.length; i++) {
				var n = this.showUserData[i];
				for (var j = 0; j < this.showUserModel.length; j++) {
					if(n.key == this.showUserModel[j]){
						uName += n.label;
						debugger;
						if(i != (this.showUserData.length-1)){
							uName += ",";
						}
						break;
					}
				}
			}
			commonVue.SysOrgUserTreeCallBack(this.showUserModel,uName);//回填数据
			//关闭窗口
			showUserVue.dialogClose();
		},
		//取消按钮
		showUserCancel : function(){
			//关闭窗口
			showUserVue.dialogClose();
		} 
	},
	//页面初始化事件
    mounted: function () {
    	//获取所有的目录
    	var result = ak.msService("sys","userManageApi/getAllUsers",null).data;
    	var localInTemps = new Array;
    	for (var i = 0; i < result.length; i++) {
    		var n = result[i];
    		console.log(n);
    		var obj = new Object();
    		obj["receiveId"] = n.id;
    		obj["receiveName"] = n.userDisplayName;
    		obj["receiveEmail"] = n.email;
    		obj["receivePhone"] = n.phone;
    		localInTemps.push({
    			key : obj,
    			label : n.userDisplayName,
    			disabled : null
    		});
		}
    	this.showUserData = localInTemps;
    }
});

//生成vue对象
var showUserVue = ak.getMergeVue(dialogAttr);