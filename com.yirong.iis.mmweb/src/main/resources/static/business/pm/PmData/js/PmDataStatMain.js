//时间属性
var dateAttr = {
    data: {
        //日期选择器选项
        ctStartPickerOptions : {
            // 选择的采集日期不能超过当天
            disabledDate : function(time) {
                return time.getTime() > new Date() ;
            }
        },
        //日期选择器选项
        ctEndPickerOptions : {
            // 选择的采集日期不能超过当天，且结束日期不能早于开始日期
            disabledDate : function(time) {
                return time.getTime() > new Date() ||  time.getTime()  < mainVue.tableSearchModel.ctStartDate;
            }
        },
        // 快捷键
        shortcuts: [
            {
                text : '今天',
                onClick : function(picker) {
                    picker.$emit('pick', new Date(new Date(new Date().toLocaleDateString()).getTime()));
                }
            },
            {
                text: '昨天',
                onClick : function(picker) {
                    const date = new Date(new Date(new Date().toLocaleDateString()).getTime());
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                }
            },
            {
                text: '一周前',
                onClick: function (picker) {
                    const date = new Date(new Date(new Date().toLocaleDateString()).getTime());
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                }
            },
            {
                text: '一个月前',
                onClick: function (picker) {
                    const date = new Date(new Date(new Date().toLocaleDateString()).getTime());
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', date);
                }
            }
        ]
    },
    methods: {
        ctEndDateValidator : function () {
            if ( mainVue.tableSearchModel.ctEndDate < mainVue.tableSearchModel.ctStartDate)
                mainVue.tableSearchModel.ctEndDate = mainVue.tableSearchModel.ctStartDate;
        }
    }
};

//主属性
var mainAttr = {
	el: '#main',
	//数据
	data: {
    	//查询对象
        tableSearchModel: {
            ctStartDate: new Date(new Date(new Date().toLocaleDateString()).getTime()-30*24*3600*1000),
            ctEndDate: new Date(),
        	source : null
        },
        //表格数据
        tableData : [],
        //来源集合
        sourceList : []
    },
    //事件
    methods: {
    	//查询按钮
    	searchClick: function () {
    		//特殊处理日期参数
    		var dateStart = this.tableSearchModel.ctStartDate;
    		if(ak.isNotNullOrEmpty(dateStart)){
    			this.tableSearchModel.ctStartDate = ak.date.format('yyyy-MM-dd', dateStart);
    		}
    		var endStart = this.tableSearchModel.ctEndDate;
    		if(ak.isNotNullOrEmpty(endStart)){
    			this.tableSearchModel.ctEndDate = ak.date.format('yyyy-MM-dd', endStart);
    		}
    		var param = this.tableSearchModel;
       	    //查询后台
        	var result = ak.msService("pm","PmDataApi/getDistributionList",param);
        	debugger;
        	if(result.code == 0){
        		this.tableData = result.data.list;
        		//处理图表
				commonVue.getBidDistributionPieChart("pieChart",result.data,true);
        	}
            this.tableSearchModel.ctStartDate = dateStart;
            this.tableSearchModel.ctEndDate = endStart;
        },
        //重置表格查询条件
		tableResetCondition: function () {
        	this.$refs["tableSearchModel"].resetFields();
    	},
        //导出按钮
        exportClick : function(){
        	$('#tableId').tableExport({type:'excel',fileName : "预处理采集情况"});
        }
    },
	//页面初始化事件
    mounted: function () {
    	//初始化表格高度
    	awakenCommonVue.initHeight("tableId");
    	var allHeight = $(window).height();
		$("#tableId").height(allHeight - 75 - 35);
		//初始化采集来源
    	this.sourceList = commonVue.initSource();

        //为日期选择器添加快捷键
        this.ctStartPickerOptions.shortcuts = this.shortcuts;
        this.ctEndPickerOptions.shortcuts = this.shortcuts;
    } 
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,dateAttr);