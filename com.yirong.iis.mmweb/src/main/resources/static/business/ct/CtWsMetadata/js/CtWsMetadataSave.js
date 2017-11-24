var upField = new Object();
//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
        saveModel: {
        	wsMetadataName: null,
        	websiteGroupId:null,
        	wsMetadataRemark:null
        },
        //前端验证
        saveRules: {
        	wsMetadataName: [
                { required: true, message: '请输入元数据名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],websiteGroupId: [
                 { required: true, message: '请选择网址组', trigger: 'blur' }
            ],fieldType: [
                 { required: true, message: '请选择字段类型', trigger: 'blur' }
	        ]
        },
        fieldRules: {
        	fieldName: [
                { required: true, message: '请输入字段中文名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],fieldCode: [
                 { required: true, message: '请输入字段英文名称', trigger: 'blur' },
                 { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ]
        },
        websiteGroupList:null,
        fieldData:[],
        upIndex:null
	},
	methods : {
		saveFieldData:function(){
			//定义回调函数
			commonVue.WsMetadataFieldCallBack = this.changeClickCallback;
			ak.dialog("saveField","business/ct/CtWsMetadata/CtWsMetadataField.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(data){
			var codeAndName = data.fieldType.split(',');
			var fieldData = {
    				fieldName:data.fieldName,
    				fieldCode:data.fieldCode,
    				fieldSort:data.fieldSort,
    				fieldType:codeAndName[0],
    				fieldTypeName:codeAndName[1],
    				nodeName:data.nodeName
    			}
			var b= true;
			for (var i = 0; i < saveVue.fieldData.length; i++) {
				if(saveVue.fieldData[i].fieldCode==fieldData.fieldCode){
					b=false;
					ak.warning("字段英文名称已存在，添加失败！");
				}
			}
			if(b){
				saveVue.fieldData.push(fieldData);
			}
            
		},
		//元数据保存按钮
		saveSubmit : function(){
			this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                	if(!saveVue.fieldData || saveVue.fieldData.length==0){
                		ak.warning("请添加字段信息！");
                	}
                	for (var i = 0; i < saveVue.fieldData.length; i++) {
                		delete saveVue.fieldData[i].fieldTypeName
					}
                	var param = {
                		ctWsMetadata:saveVue.saveModel,
                		field:saveVue.fieldData
                	}
                	if(!(saveVue.fieldData) || (saveVue.fieldData.length==0)){
                		ak.warning("请添加并勾选元数据字段！");
                	}else{
                		var result = ak.msService("ct","CtWsMetadataApi/save",param);
                        if(result.code == 0){//操作成功
                        	//提示
                        	ak.success(result.msg);
                        	//关闭窗口
                        	saveVue.dialogClose();
                        	//刷新表格
                        	mainVue.tableRefresh();
                        }else{
                        	ak.warning(result.msg);
                        }
                	}
                }
            });
		},
		//重置按钮
		saveReset : function(){
			this.$refs["saveModel"].resetFields();
			this.fieldData=[];
		},
		//删除条件按钮
		delCondition : function(index,row){
	        //删除本行
	        this.fieldData.splice(index,1);
		},
		//修改条件按钮
		upFieldData:function(index,row){
			//定义回调函数
			this.upIndex=index;
			var fieldName=saveVue.fieldData[index].fieldName;
			var fieldCode=saveVue.fieldData[index].fieldCode;
			var fieldSort=saveVue.fieldData[index].fieldSort;
			var fieldType=saveVue.fieldData[index].fieldType+","+saveVue.fieldData[index].fieldTypeName;
			var nodeName=saveVue.fieldData[index].nodeName;
			upField={
					"fieldName":fieldName,
    				"fieldCode":fieldCode,
    				"fieldSort":fieldSort,
    				"fieldType":fieldType,
    				"nodeName":nodeName
			}
			commonVue.WsMetadataFieldCallBack = this.upClickCallback;
			ak.dialog("saveField","business/ct/CtWsMetadata/CtWsMetadataField.html");
		},
		//标准分类选择器回调事件
		upClickCallback : function(data){
			var codeAndName = data.fieldType.split(',');
			var fieldData = {
    				fieldName:data.fieldName,
    				fieldCode:data.fieldCode,
    				fieldSort:data.fieldSort,
    				fieldType:codeAndName[0],
    				fieldTypeName:codeAndName[1],
    				nodeName:data.nodeName
    			}
			var b= true;
			for (var i = 0; i < saveVue.fieldData.length; i++) {
				if(saveVue.fieldData[i].fieldCode==fieldData.fieldCode){
					b=false;
					ak.warning("字段英文名称已存在，添加失败！");
				}
			}
			if(b){
				saveVue.fieldData[saveVue.upIndex]=fieldData;
				saveVue.fieldData.push();
			}
            
		},
		//新增自定义订阅用户
		saveFieldData:function(){
			//定义回调函数
			commonVue.WsMetadataFieldCallBack = this.changeClickCallback;
			ak.dialog("saveField","business/ct/CtWsMetadata/CtWsMetadataField.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(data){
			var codeAndName = data.fieldType.split(',');
			var fieldData = {
    				fieldName:data.fieldName,
    				fieldCode:data.fieldCode,
    				fieldSort:data.fieldSort,
    				fieldType:codeAndName[0],
    				fieldTypeName:codeAndName[1],
    				nodeName:data.nodeName
    			}
			var b= true;
			for (var i = 0; i < saveVue.fieldData.length; i++) {
				if(saveVue.fieldData[i].fieldCode==fieldData.fieldCode){
					b=false;
					ak.warning("字段英文名称已存在，添加失败！");
				}
			}
			if(b){
				saveVue.fieldData.push(fieldData);
			}
            
		},
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//查询所有的网址组
    	var result = ak.msService("ct","CtWebsiteGroupApi/getCtWebsiteGroup",null);
    	if(result.code == 0 ){
    		this.websiteGroupList = result.data;
    	}
    	
    }
});

//生成vue对象
var saveVue = new Vue(dialogAttr);