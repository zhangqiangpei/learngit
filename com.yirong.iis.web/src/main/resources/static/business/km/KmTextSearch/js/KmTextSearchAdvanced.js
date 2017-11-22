//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#advanced',
	data : {
		//与后台及元素交互的model
		advancedModel: {
			keyword : null,
			uploadDate : null,
			catalog : null,
			knowledge:  null
		},
 
        //前端验证
		advancedRules: {
			 
        },
        
        pickerOptions: {
            shortcuts: [{
              text: '最近一周',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', [start, end]);
              }
            }, {
              text: '最近一个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                picker.$emit('pick', [start, end]);
              }
            }, {
              text: '最近三个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                picker.$emit('pick', [start, end]);
              }
            }]
          } 
	},
	methods : {
		 
		//确定按钮
		submit : function(){
			this.$refs["advancedModel"].validate(function (valid) {
                if (valid) {//验证通过
 
        			var uploadDates = advancedVue.advancedModel.uploadDate;
                	var catalogs = advancedVue.advancedModel.catalog
                	var knowledges =  advancedVue.advancedModel.knowledge;
           
                	//设置查询条件
                	if(ak.isNotNullOrEmpty(uploadDates)){
                		mainVue.tableSearchModel.uploadStartTm = ak.date.format('yyyy-MM-dd HH:mm:ss',  uploadDates[0]);
                    	mainVue.tableSearchModel.uploadEndTm =  ak.date.format('yyyy-MM-dd HH:mm:ss',  uploadDates[1]);
                	}else{
                		mainVue.tableSearchModel.uploadStartTm = null;
                		mainVue.tableSearchModel.uploadEndTm = null;
                	}
                	/**
                	if(ak.isNotNullOrEmpty(knowledges)){
                		mainVue.tableSearchModel.knowledge =  knowledges[knowledges.length-1];
                	}
                	if(ak.isNotNullOrEmpty(catalogs)){
                		var catalog = "";
                		$.each(catalogs,function(i,n){
                    		catalog += n;
                    		if(i != catalogs.length){
                    			catalog += "/";
                    		}
                    	});
                		mainVue.tableSearchModel.catalog =  catalog;
                	}**/
                	mainVue.tableSearchModel.keyword =  advancedVue.advancedModel.keyword;
                	mainVue.tableSearchModel.knowledge = knowledges;
                	mainVue.tableSearchModel.catalog =  catalogs;
                	//关闭窗口
                	advancedVue.dialogClose();
                	mainVue.onSearch(); 
                } 
            });
		},
		 
		//重置按钮
		reset : function(){
			this.$refs["advancedModel"].resetFields();
		}
	},
	//页面初始化事件
    mounted: function () {
    	this.advancedModel.keyword=mainVue.tableSearchModel.keyword ;
    }
});
//生成vue对象
var advancedVue = new Vue(dialogAttr);