var upField = new Object();
//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
        	wsMetadataName: null,
        	websiteGroupId:null,
        	wsMetadataRemark:null
        },
        //前端验证
        updateRules: {
        	wsMetadataName: [
                { required: true, message: '请输入元数据名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],websiteGroupId: [
                 { required: true, message: '请选择网址组', trigger: 'blur' }
            ]
        },
        fieldRules: {
        	fieldName: [
                { required: true, message: '请输入字段中文名称', trigger: 'blur' },
                { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],fieldCode: [
                 { required: true, message: '请输入字段英文名称', trigger: 'blur' },
                 { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
            ],fieldType: [
                  { required: true, message: '请选择字段类型', trigger: 'blur' }
             ]
        },
        websiteGroupList:null,
        fieldData:[],
        upIndex:null
	},
	methods : {
		/*checked:function(){
			for (var k = 0; k < this.fieldData.length; k++) {
				this.$refs.fieldDataTable.toggleRowSelection(this.fieldData[k]);
			}
		},*/
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
            		for (var i = 0; i < updateVue.fieldData.length; i++) {
            			delete updateVue.fieldData[i].fieldTypeName
					}
                	var param = {
                		ctWsMetadata:updateVue.updateModel,
                		field:updateVue.fieldData
                	}
            		var result = ak.msService("ct","CtWsMetadataApi/update",param);
                    if(result.code == 0){//操作成功
                    	//提示
                    	ak.success(result.msg);
                    	//关闭窗口
                    	updateVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }else{
                    	ak.warning(result.msg);
                    }
                } 
            });
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		},
		fieldSelect:function(selection){
			this.fieldSelection=selection;
		},
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
			for (var i = 0; i < updateVue.fieldData.length; i++) {
				if(updateVue.fieldData[i].fieldCode==fieldData.fieldCode){
					b=false;
					ak.warning("字段英文名称已存在，添加失败！");
				}
			}
			if(b){
				updateVue.fieldData.push(fieldData);
			}
			
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
			var id=updateVue.fieldData[index].id;
			var fieldName=updateVue.fieldData[index].fieldName;
			var fieldCode=updateVue.fieldData[index].fieldCode;
			var nodeName= updateVue.fieldData[index].nodeName;
			var fieldSort=updateVue.fieldData[index].fieldSort;
			var fieldType=updateVue.fieldData[index].fieldType+","+updateVue.fieldData[index].fieldTypeName;
			upField={
					"id":id,
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
					id:data.id,
    				fieldName:data.fieldName,
    				fieldCode:data.fieldCode,
    				nodeName:data.nodeName,
    				fieldSort:data.fieldSort,
    				fieldType:codeAndName[0],
    				fieldTypeName:codeAndName[1]
    			}
			var b= true;
			for (var i = 0; i < updateVue.fieldData.length; i++) {
				if(updateVue.fieldData[i].fieldCode==fieldData.fieldCode && i!=updateVue.upIndex){
					b=false;
					ak.warning("字段英文名称已存在，添加失败！");
				}
			}
			if(b){
				updateVue.fieldData[updateVue.upIndex]=fieldData;
				updateVue.fieldData.push();
			}
            
		}
	},
	//页面初始化事件
    mounted: function () {
    	//查询所有的网址组
    	var result = ak.msService("ct","CtWebsiteGroupApi/getCtWebsiteGroup",null);
    	if(result.code == 0 ){
    		this.websiteGroupList = result.data;
    	}
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("ct","CtWsMetadataApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var data = result.data;
    		//复制属性
    		ak.copyObjValue(data.ctWsMetadata,this.updateModel);
    		
    		//复制属性
    		this.updateModel.id=checkObj.id;
    		for (var i = 0; i < data.fieldList.length; i++) {
    			for (var j = 0; j < mainVue.fieldTypes.length; j++) {
    				if(data.fieldList[i].fieldType==mainVue.fieldTypes[j].key){
    					data.fieldList[i]['fieldTypeName']=mainVue.fieldTypes[j].label;
    					continue;
    				}
				}
			}
    		this.fieldData=data.fieldList;
    		this.fieldSelection = data.fieldList;
    		//this.checked();
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);