//获取table属性
var tableAttr = getDialogMergeAttr({
	el: '#save',
	data : {
		//与后台及元素交互的model
		saveModule : {
    		title : null,
    		content : null,
    		effectiveTime : null,
    		failureTime : null,
    		isTop : "0",
    		state : "0"
    	},
 
    	effectiveAndFailure : null,
        //前端验证
		saveRules: {
			title: [
                { required: true, message: '请输公告标题', trigger: 'blur' },
                { min: 1, max: 1000, message: '长度在1到1000个字符', trigger: 'blur' }
            ] 
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
 
    methods: {
    	//确定按钮
		submit : function(){
			this.$refs["saveModule"].validate(function (valid) {
                if (valid) {//验证通过
               
                	if(null == saveVue.effectiveAndFailure){
                		ak.info("请选择时间范围!");
                		return ;
                	}
                	
                	var content = document.getElementById('keIframe').contentWindow.document.getElementById('editor_id').value;
                	if(ak.isNullOrEmpty(content)){
                		ak.info("请输入内容!");
                		return ;
                	}
                	
                	var param =  saveVue.saveModule;
                	param.content = content;
 
                	if(null != saveVue.effectiveAndFailure && saveVue.effectiveAndFailure.length == 2){
                		param.effectiveTime = new Date(saveVue.effectiveAndFailure[0]).Format("yyyy-MM-dd HH:mm:ss");  
                    	param.failureTime  = new Date(saveVue.effectiveAndFailure[1]).Format("yyyy-MM-dd HH:mm:ss");  
                	}

            		var data = ak.msService("sys","sysNoticeApi/save",param);
                	if(null != data && data.code == 0){//操作成功
                		//提示
                    	ak.success(data.msg);
                    	//关闭窗口
                    	saveVue.dialogClose();
                    	//刷新表格
                    	mainVue.tableRefresh();
                    }
                } 
            });
		},
		 
		//重置按钮
		reset : function(){
			this.$refs["saveModule"].resetFields();
		}
    },
    //页面初始化事件
    mounted: function () {
    	 
    }
});
 

//生成vue对象
var saveVue = new Vue(tableAttr);

Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "H+" : this.getHours(), //小时
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}