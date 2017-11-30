//获取table属性
var tableAttr = getDialogMergeAttr({
	el: '#save',
    data: {
    	title:null,
    	receiverName:null,
    	receiverIds:null,
    	receiverCode:null
    },
    methods: {
    	//查询按钮
    	messageSave: function () {
    		if(this.title == null || this.title == ""){
    			ak.error("标题不能为空!");
    			return;
    		}
    		
    		if(this.receiverCode == null || this.receiverCode == ""){
    			ak.error("接收人不能为空!");
    			return;
    		}
    		
    		var param = {
    			title:this.title,
    			receiverCode:this.receiverCode,
    			content:document.getElementById('keIframe').contentWindow.document.getElementById('editor_id').value
        	};
    		
    		var data = ak.msService("sys","msgLogApi/save",param);
        	if(null != data && data.code == 0){//操作成功
        		//提示
            	ak.success(data.msg);
            	//关闭窗口
            	saveVue.dialogClose();
            	//刷新表格
            	mainVue.tableRefresh();
            }
        },
        selectReceiver:function (){
        	commonVue.SysOrgUserTreeParam = {
        		code : '',
        		roleId :  ''
        	};
        	commonVue.SysOrgUserTreeCallBack = this.changeClickCallback;
			ak.dialog("SysOrgUserTree","common/SysOrgUserTree.html");
        },
        //树选择器回调事件
        changeClickCallback : function(checkedNodes,ids){
        	
        	var userIds = "";
			$.each(ids,function(i,id){
				userIds += id;
				if(i != (ids.length - 1)){//不是最后一个数据均需加入分隔符
					userIds += ",";
				}
			});
			debugger;
			var userNames = "";
			var userCodes ="";
			$.each(checkedNodes,function(i,node){
				if (typeof(node.children) == "undefined"){
					userNames += node.name;
					userCodes += node.atttibute.code;
					if(i != (checkedNodes.length - 1)){//不是最后一个数据均需加入分隔符
						userNames += ",";
						userCodes += ",";
					}
				}
			});
			
        	this.receiverName = userNames;
        	this.receiverIds = userIds;
        	this.receiverCode = userCodes;
        },
        mainResetInput : function(){
        	this.title=null;
        	this.receiverName=null;
        	this.receiverIds=null;
        	this.receiverCode=null;
        	document.getElementById('keIframe').contentWindow.editor.html("");
        }
    }
});
 

//生成vue对象
var saveVue = new Vue(tableAttr);