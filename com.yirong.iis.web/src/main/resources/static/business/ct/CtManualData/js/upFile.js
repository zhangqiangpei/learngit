//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#upFile',
	data : {
		//与后台及元素交互的model
		form:{
			serviceName:"ct",
			methodName:"CtManualDataDetailApi/uploadFile",
			fileId:null,
			structuring:"1"
		},
		fileList: [],
		fileType:"excel"
	},
	methods : {
	      beforeAvatarUpload(file) {
		        const isExcel = file.type === 'application/vnd.ms-excel';
		        if (!isExcel) {
		            this.$message.error('只能上传excel文件！');
		            return false;
		        }else{
		        	//第一步
					/*	获取文件相关信息，保存到ct的相关服务中	*/
					var fileName = file.name;
					var index1=fileName.lastIndexOf(".");
					var index2=fileName.length;
					var suffixName = fileName.substring(index1+1,index2);//后缀名
					var param = {
						manualDataId:mainVue.tableGetCheckObjs()[0].id,
						fileName:file.name,
						suffixName:suffixName
					}
					var resule = ak.msService("ct","CtManualDataDetailApi/save",param);
					if(resule.code==0){
						//保存成功
						this.form.fileId = resule.data;
						setTimeout("mainVue.tableRefresh();",6000);
						return true;
					}else{
						return false;
					}
		        }
	        }
	},
	//页面初始化事件
    mounted: function () {
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);