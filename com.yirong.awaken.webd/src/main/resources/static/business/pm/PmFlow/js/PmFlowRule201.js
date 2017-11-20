//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#rule201',
	//数据
	data : {
		rule201Model : {
			field : null,
			standardType : null,
			relationFieldCode : null,
			relationFieldName : null,
			convertFieldCode : null,
			convertFieldName : null
		},
		//字段下拉
		fieldList : [],
		//标准分类
		standardTypeName : null,
		//帮助
		elementRemark : null,
		//前端验证
        rule201Rules: {
            field: [
                { required: true, message: '请选择转换字段', trigger: 'blur' }
            ],
            relationFieldCode: [
                { required: true, message: '请输入关联字段英文名', trigger: 'blur' },
                { min: 1, max: 20, message: '长度在1 到 20 个字符', trigger: 'blur' }
            ],
            relationFieldName: [
                { required: true, message: '请输入关联字段中文名', trigger: 'blur' },
                { min: 1, max: 40, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ],
            convertFieldCode: [
                { required: true, message: '请输入关联字段中文名', trigger: 'blur' },
                { min: 1, max: 20, message: '长度在1 到 20 个字符', trigger: 'blur' }
            ],
            convertFieldName: [
                { required: true, message: '请输入关联字段中文名', trigger: 'blur' },
                { min: 1, max: 40, message: '长度在1 到 40 个字符', trigger: 'blur' }
            ]
        }
	},
	methods : {
		//确定按钮
		rule201Submit : function(){
			var relationFieldCode = this.rule201Model.relationFieldCode;
		    var convertFieldCode = this.rule201Model.convertFieldCode;
			this.$refs["rule201Model"].validate(function (valid) {
                if (valid) {//验证通过
                	if(null == rule201Vue.standardTypeName){
                		ak.info("请选择标准分类");
                		return;
                	}
                	//获取本身信息
		    		var localFiled = objectField[checkId];
		    		/**验证编码是否存在**/
		    		var isOk = false;
		    		$.each(localFiled.inField,function(i,n){
		    			if(n.code == relationFieldCode){
		    				isOk = true;
		    				return;
		    			}
						if(n.code == convertFieldCode){
		    				isOk = true;
		    				return;
		    			}
					});
					if(isOk){
						ak.info("字段英文名称已存在，请修改后重试");
						return;
					}
		    		/**处理输出字段信息**/
		    		//先复制之前的数据
					localFiled.outField = new Array;
					$.each(localFiled.inField,function(i,n){
						localFiled.outField.push(n);
					});
					//新增关联字段
					localFiled.outField.push({
						code : relationFieldCode,
						name : rule201Vue.rule201Model.relationFieldName,
						type : "017001",
						typeName : "string"
					});
					//新增转换字段
					localFiled.outField.push({
						code : convertFieldCode,
						name : rule201Vue.rule201Model.convertFieldName,
						type : "017001",
						typeName : "string"
					});
					//处理已修改的数据
					objectData[checkId] = rule201Vue.rule201Model;
					objectData[checkId]["standardTypeName"] = rule201Vue.standardTypeName;
					//关闭窗口
					rule201Vue.dialogClose();
                }
			});
		},
		//取消按钮
		rule201Cancel : function(){
			//关闭窗口
			rule201Vue.dialogClose();
		},
		//帮助说明按钮
		getPopoverInfo : function(){
			var checkObj = $("#" + checkId);
			this.elementRemark =  commonVue.getPmElementRemark(checkObj.attr("oid"));
		},
		//字段选择事件
		fieldChange : function(code){
			var name = null;
			$.each(this.fieldList,function(i,n){
				if(code == n.code){
					name = n.name;
					return;//跳出循环
				}
			});
			//处理默认显示信息
			this.rule201Model.relationFieldCode = code + "_relation";
			this.rule201Model.relationFieldName = name + "_关联";
			this.rule201Model.convertFieldCode = code + "_convert";
			this.rule201Model.convertFieldName = name + "_转换";
		},
		//对照数据选择器
		changeClick : function(index,row){
			//查询输入的对象ID（200写死防止死循环）
	    	var oid = "";
	    	var i = 0;
	    	var forCheckId = checkId;
	    	while(i < 200 && null != objectLineObject[forCheckId] && "" == oid){
	    		var checkObj = $("#" + forCheckId);
	    		if("input" == checkObj.data("type")){//输入框
	    			oid = checkObj.attr("oid");
	    			break;
	    		}
	    		forCheckId = objectLineObject[forCheckId].inId;
	    		i++;
	    	}
			//处理参数
			commonVue.PmContrastTypeTreeParam = {
				ctObjectId : oid,
				index : index
			};
			//指定回调函数
			commonVue.PmContrastTypeTreeCallBack = this.changeClickCallback;
			//打开页面
			ak.dialog("PmContrastTypeTree","common/PmContrastTypeTree.html");
		},
		//标准数据选择器回调事件
		changeClickCallback : function(row){
			this.rule201Model.standardType = row.id;
			this.standardTypeName = row.name;
		}
	},
	//页面初始化事件
    mounted: function () {
    	//获取输入信息
    	var inField = objectField[checkId].inField;
    	var dataTemp = new Array;
    	//初始化下拉框
    	$.each(inField,function(i,n){
    		dataTemp.push({
    			code : n.code,
    			name : n.name
    		});
    	});
    	this.fieldList = dataTemp;
    	//处理数据
    	if(objectData[checkId]){
    		ak.copyObjValue(objectData[checkId],this.rule201Model);
    		this.standardTypeName = objectData[checkId].standardTypeName;
    	}
    }
});


//生成vue对象
var rule201Vue = ak.getMergeVue(dialogAttr);