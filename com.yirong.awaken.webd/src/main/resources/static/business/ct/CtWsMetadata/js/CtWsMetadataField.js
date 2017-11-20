//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#saveField',
	data : {
		//与后台及元素交互的model
		saveModel: {
			id:null,
			fieldName:null,
        	fieldCode:null,
        	fieldSort:null,
        	fieldType:null,
        	nodeName:null
        },
        fieldTypes:null,
        //前端验证
        saveRules: {
        	fieldName: [
            { required: true, message: '请输入字段中文名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],fieldCode: [
                 { required: true, message: '请输入字段英文名称', trigger: 'blur' },
                 { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],fieldType: [
                  { required: true, message: '请选择字段类型', trigger: 'blur' }
             ]
        }
	},
	methods : {
		//确定按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                	//先回调
        			commonVue.WsMetadataFieldCallBack(saveFieldVue.saveModel);
        	        //再关闭窗口
        			saveFieldVue.dialogClose();
                }
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	this.fieldTypes = mainVue.fieldTypes;
    	this.saveModel.fieldType='017001,string';
    	this.saveModel.id= upField.id;
		this.saveModel.fieldName = upField.fieldName;
		this.saveModel.fieldCode = upField.fieldCode;
		this.saveModel.fieldSort = upField.fieldSort;
		this.saveModel.nodeName = upField.nodeName;
    }
});
//生成vue对象
var saveFieldVue = new Vue(dialogAttr);