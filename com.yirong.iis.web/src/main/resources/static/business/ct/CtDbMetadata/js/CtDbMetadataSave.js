var dbTableData = new Object();
var dbTableArray = [];
var dbTable = "";
var colData = [];

//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModelDB: {
			dbMetadataName: null,
			dbMetadataType:'012001',
			ctWay:"011001",
			//catalogId:null,
			databaseId:null,
			dbMetadataRemark:null,
			tableName:null,
			inCondition:null
        },
        saveModelSQL: {
			dbMetadataName: null,
			dbMetadataType:'012002',
			ctWay:"011001",
			//catalogId:null,
			databaseId:null,
			dbMetadataRemark:null,
			runSql:null,
			inCondition:null
        },
        //下拉框
        ctWays:null,
        fieldTypes:null,
        fieldTypesSave:null,
        field:{
        	dbColData:null,
        	SQLColData:null
        },
        metadataTab: 'first',
        inConditionDB:false,
        inConditionSQL:false,
        //前端验证
        saveRules: {
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
                  { required: true, message: '请选择数据库', trigger: 'blur' }
	        ]*/,runSql:[
  	             { required: true, message: '请输入sql语句', trigger: 'blur' }
  	        ],databaseId:[
  	             { required: true, message: '请选择数据库', trigger: 'blur' }
  	        ]
        },
        dbTableData:null,
        dbColSelection:null,
        SQLColSelection:null,
        tableDataList:null,
        dbDatabaseList:null,
        /*DBcatalogName:null,
        SQLcatalogName:null*/
	},
	methods : {
		loadData : function(){
    		this.fieldTypes = mainVue.fieldTypes;
    		this.ctWays =mainVue.ctWays;
    	},
		//库表元数据保存按钮
		saveSubmitDB : function(){
			this.$refs["saveModelDB"].validate(function (valid) {
                if (valid) {//验证通过
                	//记得补充元数据的类型
                	var b=true;
                	var fieldArray = [];
                	$.each(saveVue.dbColSelection,function(i,ele){
                		if(ele.fieldType==null || ele.fieldType==''){
                			ak.warning("请为字段名称为"+ele.columnName+"的行选择数据类型！");
                			b=false;
                			return false;
                		}
                		fieldArray.push({
                			fieldName:ele.comments,
                			fieldCode:ele.columnName,
                			fieldType:ele.fieldType
                		})
                	})
                	if(saveVue.saveModelDB.ctWay=="011002" && saveVue.saveModelDB.inCondition==null){
                		b=false;
                		ak.warning("增量采集类型的元数据请填写增量条件！");
                	}
                    if(b){
                    	var result = ak.msService("ct","CtDbMetadataApi/save",{metadata:saveVue.saveModelDB,field:fieldArray});
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
		saveResetDB : function(){
			this.$refs["saveModelDB"].resetFields();
		},
		//SQL元数据保存按钮
		saveSubmitSQL : function(){
			this.$refs["saveModelSQL"].validate(function (valid) {
                if (valid) {//验证通过
                	var b = true;
                	var fieldArray = [];
                	$.each(saveVue.SQLColSelection,function(i,ele){
                		if(ele.fieldType==null || ele.fieldType==''){
                			ak.warning("请为字段名称为"+ele.columnName+"的行选择数据类型！");
                			b=false;
                			return false;
                		}
                		if(ele.columnName==null || ele.columnName==''){
                			ak.warning("请为字段名称为"+ele.columnName+"的行填写名称！");
                			b=false;
                			return false;
                		}
                		fieldArray.push({
                			fieldName:ele.comments,
                			fieldCode:ele.columnName,
                			fieldType:ele.fieldType
                		})
                	})
                	if(saveVue.saveModelSQL.ctWay=="011002" && saveVue.saveModelSQL.inCondition==null){
                		b=false;
                		ak.warning("增量采集类型的元数据请填写增量条件！");
                	}
                    if(b){
                    	var result = ak.msService("ct","CtDbMetadataApi/save",{metadata:saveVue.saveModelSQL,field:fieldArray});
                        if(result.code == 0){//操作成功
                        	//提示
                        	ak.info(result.msg);
                        	//关闭窗口
                        	saveVue.dialogClose();
                        	//刷新表格
                        	mainVue.tableRefresh();
                        }
                    }
                } 
            });
		},
		//重置按钮
		saveResetSQL : function(){
			this.$refs["saveModelSQL"].resetFields();
		},
		//切换tab页面
		handleClick(tab, event) {
	    },
	    dbTableChange :function(tableName){
	    	dbTable = tableName;
	    	//库表元数据的表名
	    	this.field.dbColData = dbTableData[dbTable].columnList;
	    },
	    //db元数据的col点击事件
	    dbColSelect :function(selection){
	    	this.dbColSelection=selection;
	    },
	    SQLColSelect :function(selection){
	    	this.SQLColSelection=selection;
	    },
	    //连接数据库
	    connectDB : function(){
	    	if(this.saveModelDB.databaseId==null){
	    		ak.warning("请选择数据库！");
	    	}
	    	else{
	    		var dsID = this.saveModelDB.databaseId;
	    		var param =  new Object(); 
	    		param.id=dsID;
	    		var result = ak.msService("ct", "CtDatabaseApi/getDB", param);
	    		if(result.code == 0){//操作成功
	    			var data = result.data;
	    			$.each(data,function(i,ele){
		    			$.each(ele.columnList,function(j,e){
		    				e['fieldType']='017001';
		    			})
		    			dbTableData[ele.name] = {
		    					columnList:ele.columnList,
		    			};
		    			dbTableArray.push({
		    				tableName:ele.name
		    			})
		    		})
		    		this.dbTableData = dbTableArray;
		    		this.tableDataList = dbTableArray;
		    		ak.info("数据库连接成功，请选择表名！");
	    		}else{
	    			ak.warning(result.msg);
	    		}
	    		
	    		/*$.each(data,function(i,obj){
	    			tableData[obj.name]=obj.columnList;//构造一个属性为表名，值为对应的列的对象
	    		})
	    		//显示所有表名
	    		var tableHtml="";
	    		if((data.length % 2)!=0 ){
	    			data.push({});
	    		}
	    		for(i=0;i<data.length;i+=2){
	    			tableHtml += '<tr>';
	    			tableHtml += '<td class="am-text-center" >'
	    			tableHtml += '<label class="am-checkbox">';
	    			tableHtml += '<input type="checkbox" value="'+data[i].name+'" data-am-ucheck onchange="showColumn(this);">'+data[i].name;
	    			tableHtml += '</label>';
	    			tableHtml += "</td>"
	    			tableHtml += '<td class="am-text-center" >';
	    			if(data[i+1]!=null && undefined != data[i+1]){
	    				tableHtml += '<label class="am-checkbox">';
	    				tableHtml += '<input type="checkbox" value="'+data[i+1].name+'" data-am-ucheck onchange="showColumn(this);">'+data[i+1].name;
	    				tableHtml += '</label>';
	    			}
	    			tableHtml += "</td>"
	    			tableHtml == "</tr>";
	    		}
	    		$("#tableName").html('');
	    		$.each($("#colunmsTable tbody"),function(i,ele){
	    			$(ele).remove(); 
	    		 })
	    		$("#tableName").html(tableHtml);*/
	    	}
	    },
	    connectSQL : function(){
	    	if(this.saveModelSQL.databaseId==null){
	    		ak.warning("请选择数据库！");
	    		return;
	    	}
	    	if(this.saveModelSQL.runSql==null){
	    		ak.warning("请填写SQL查询语句！");
	    		return;
	    	}
	    	//
	    	var ddl = this.saveModelSQL.runSql;
    		var datasourceID = this.saveModelSQL.databaseId;
    		var data =  ak.msService("ct", "CtDatabaseApi/getColBySQl", { ddl : ddl,datasourceID:datasourceID});
    		if(data.code!=0){
    			ak.warning(data.msg);
    		}else{
    			$.each(data.data,function(i,ele){
    				colData.push({
    					columnName:ele,
    					comments:'',
    					fieldType:'017001'
    				})
    			})
    			this.field.SQLColData = colData;
    			ak.info("查询成功，请选择字段！");
    		}
	    },
	    ctWayDB:function(value){
	    	if("011002"==value){
	    		this.inConditionDB=true;
	    	}else{
	    		this.inConditionDB=false;
	    	}
	    },
	    ctWaySQL:function(value){
	    	if("011002"==value){
	    		this.inConditionSQL=true;
	    	}else{
	    		this.inConditionSQL=false;
	    	}
	    }
	  /*//标准分类选择器点击事件
	    changeClickDB : function(){
			//定义回调函数
			commonVue.CtCatalogTreeCallBack = this.changeClickCallbackDB;
			ak.dialog("CtCatalogTree","common/CtCatalogTree.html");
		},
		//标准分类选择器回调事件
		changeClickCallbackDB : function(data){
			this.saveModelDB.catalogId = data.id;
			this.DBcatalogName = data.name;
		},
		changeClickSQL : function(){
			//定义回调函数
			commonVue.CtCatalogTreeCallBack = this.changeClickCallbackSQL;
			ak.dialog("CtCatalogTree","common/CtCatalogTree.html");
		},
		//标准分类选择器回调事件
		changeClickCallbackSQL : function(data){
			this.saveModelSQL.catalogId = data.id;
			this.SQLcatalogName = data.name;
		}*/
	},
	//页面初始化事件
    mounted: function () {
    	//名称不作为传递参数
    	this.standardTypeName = mainVue.standardTypeName;
    	//查询所有的数据库
    	var result = ak.msService("ct","CtDatabaseApi/getCtDatabase",null);
    	if(result.code == 0 ){
    		this.dbDatabaseList = result.data;
    	}
    	this.loadData();
    }
});

//生成vue对象
var saveVue = new Vue(dialogAttr);