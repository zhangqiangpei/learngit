//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		//与后台及元素交互的model
		updateModel: {
			id:null,
			dbMetadataName: null,
			dbMetadataType:null,
			ctWay:null,
			databaseId:null,
			dbMetadataRemark:null,
			tableName:null,
			runSql:null
        },
        //前端验证
        updateRules: {
        	/*standardCode: [
            { required: true, message: '请输入标准编码', trigger: 'blur' },
            { min: 1, max: 32, message: '长度在1 到 32 个字符', trigger: 'blur' }
	        ],*/
        	dbMetadataName: [
                 { required: true, message: '请输入元数据名称', trigger: 'blur' },
                 { min: 1, max: 64, message: '长度在1 到 64 个字符', trigger: 'blur' }
             ],ctWay: [
                  { required: true, message: '请选择元数据增量方式', trigger: 'blur' }
             ]/*,catalogId: [
                   { required: true, message: '请选择目录', trigger: 'blur' }
 	        ],databaseId:[
   	             { required: true, message: '请选择数据库', trigger: 'blur' }
   	        ]*/
        },
        //自定义的参数
        catalogName:null,
        databaseName:null,
        colData:null,
        //DB类型特有的tableName是否显示
        DBShow:false,
        //SQL类型特有的SQL语句是否显示
        SQLShow:false,
        //已保存的元数据信息
        fieldList:null,
        colSelection:null
	},
	methods : {
		//确定按钮
		updateSubmit : function(){
			this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                	//记得补充元数据的类型
                	var b=true;
                	$.each(this.updateVue.colSelection,function(i,ele){
                		if(ele.fieldType==null || ele.fieldType==''){
                			ak.warning("请为字段名称为"+ele.columnName+"的行选择数据类型！");
                			b=false;
                			return false;
                		}
                	})
                    if(b){
                    	//后台update进行比对
                    	var result = ak.msService("ct","CtDbMetadataApi/update",{metadata:updateVue.updateModel,field:this.updateVue.colSelection});
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
                } 
            });
		},
		//取消按钮
		updateCancel : function(){
			//关闭窗口
            updateVue.dialogClose();
		},
		/*changeClick : function(){
			//定义回调函数
			commonVue.CtCatalogTreeCallBack = this.changeClickCallback;
			ak.dialog("CtCatalogTree","common/CtCatalogTree.html");
		},
		//标准分类选择器回调事件
		changeClickCallback : function(data){
			this.updateModel.catalogId = data.id;
			this.catalogName = data.name;
		},*/
		checked(){
			for(var k = 0, length = this.colData.length; k < length; k++){
				if(this.colData[k].id){
					this.$refs.colDataTable.toggleRowSelection(this.colData[k]);
				}
			}
		},
		colSelect :function(selection){
	    	this.colSelection=selection;
	    }
	},
	//页面初始化事件
    mounted: function () {
    	//
    	//处理参数
    	var checkObj = mainVue.tableGetCheckObjs()[0];
    	var result = ak.msService("ct","CtDbMetadataApi/get",{ id : checkObj.id});
    	if(result.code == 0){//请求成功
    		var metadataData = result.data.metadata;//显示metadata相关属性
    		//复制属性
    		ak.copyObjValue(metadataData,this.updateModel);
    		//处理界面显示属性
    		this.updateModel.id = checkObj.id;
    		if(metadataData.dbMetadataType=="012001"){
    			this.DBShow =true;
    		}else if(metadataData.dbMetadataType=="012002"){
    			this.SQLShow =true;
    		}
    		//设置目录名称
    		this.catalogName = result.data.catalogName;
    		//设置数据库名称
    		this.databaseName = result.data.ctDatabaseName;
    		this.fieldList=result.data.fieldList;
    		//根据元数据的不同类型查询对应的元数据字段信息
    		if(metadataData.dbMetadataType=="012001"){
    			//库表元数据类型的数据
    			var dsID = this.updateModel.databaseId;
	    		var param =  new Object(); 
	    		param.id=dsID;
	    		var resultDB = ak.msService("ct", "CtDatabaseApi/getDB", param);
	    		if(resultDB.code == 0){//操作成功
	    			var dataDB = resultDB.data;
	    			var colList = [];
	    			for (var j = 0; j < dataDB.length; j++) {
						var ele = dataDB[j];
						if(ele.name==metadataData.tableName){
		    				colList=ele.columnList;
		    				//构造数据结构
		    				break;
		    			}
					}
		    		//构造field对应的数据结构
		    		var fieldArray = [];
	    			for (var i = 0; i < colList.length; i++) {
	    				var ele = colList[i];
	    				var b= true;
	    				for (var j = 0; j < this.fieldList.length; j++) {
							var field = this.fieldList[j];
							if(ele.columnName==field.fieldCode){
								fieldArray.push({
				    				fieldCode:field.fieldCode,
				    				fieldName:field.fieldName,
				    				fieldType:field.fieldType,
				    				dbMetadataId:metadataData.id,
				    				id: field.id
				    			})
				    			b=false;
				    			break;
							}
						}
						if(b){
							fieldArray.push({
			    				fieldCode:ele.columnName,
			    				fieldName:ele.comments,
			    				fieldType:'017001',
			    				dbMetadataId:metadataData.id,
			    				id: null
			    			})
						}
					}
		    		this.colData = fieldArray;
	    		}else{
	    			ak.warning("此元数据对应的数据库连接失败！");
	    		}
    		}else if(metadataData.dbMetadataType=="012002"){
    			//SQL元数据类型
    			var ddl = metadataData.runSql;
        		var datasourceID = metadataData.databaseId;
        		var data =  ak.msService("ct", "CtDatabaseApi/getColBySQl", { ddl : ddl,datasourceID:datasourceID});
        		if(data.code!=0){
        			ak.warning(data.msg);
        		}else{
        			var fieldArray = [];
        			for (var i = 0; i < data.data.length; i++) {
						var ele = data.data[i];
						var b= true;
	    				for (var j = 0; j < this.fieldList.length; j++) {
							var field = this.fieldList[j];
							if(ele==field.fieldCode){
								fieldArray.push({
				    				fieldCode:field.fieldCode,
				    				fieldName:field.fieldName,
				    				fieldType:field.fieldType,
				    				dbMetadataId:metadataData.id,
				    				id: field.id
				    			})
				    			b=false;
				    			break;
							}
						}
						if(b){
							fieldArray.push({
			    				fieldCode:ele,
			    				fieldName:'',
			    				fieldType:'017001',
			    				dbMetadataId:metadataData.id,
			    				id: null
			    			})
						}
					}
        			this.colData = fieldArray;
        		}
    		}
    		//必须在更新完数据之后，出发此函数，才能让所需要的行进行选中
    		this.$nextTick(function(){
                this.checked();//每次更新了数据，触发这个函数即可。
             })
    	}
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);
