var ue = UE.getEditor('editor');
var editOnlineReportVue = new Vue({
    el: '#main',
    data: {
        //总记录数
        reportTableTotal : 0,
        reportTableSearchModel:{
            isOnlineReport:1,
            currentPage : 1,
            pageSize : 10,
            orders : [],
            reportName: ""
        },
        reportTemplateTableTotal : 0,
        reportTemplateTableSearchModel:{
            currentPage : 1,
            pageSize : 10,
            orders : [],
            templateName: ""
        },
        reportTypeTreeData: [],
        reportTemplateData:[],
        reportData:[],
        defaultProps: {
            label: 'TYPENAME'
        },
        currentOpeObjType:"",
        currentChooseOpeObjId:"",
        chooseOperateType:false,
        report:{
            id:null,
            typeId:"",
            reportName:"",
            reportInfo:""
        },
        reportTemplate:{
            id:null,
            templateName:"",
            templateInfo:""
        },
        isSave:false,
        title:"",
        operation:null,
        currentClickData:{
            id:null,
            currentOpeObjType:null,
            content:null,
            title:null,
            typeId:null
        },
        addOperation:"新增",
        updateOperation:"更新",
        reportOpeType:"报告",
        templateOpeType:"报告模板"
    },
    methods: {
        // 使用模板按钮
        useReportTemplate:function () {
            if (!this.chooseOperateType){
                z.info("请选择要使用的报告模板！");
            } else {
                this.editClick();
                this.currentOpeObjType = this.reportOpeType;
                this.operation = this.addOperation;
                setTitleVue.dialogVisible = true;
            }
        },
        //当前页选择事件
        reportTableCurrentChange: function (val) {
            //处理参数
            var param = this.reportTableSearchModel;
            param.currentPage = val;
            //请求后台
            var result = z.msService("user", "IisReportApi/list", this.reportTableSearchModel);
            if(result.code === 0){
                this.reportTableTotal = result.data.totalCount;
                this.reportData = result.data.data;
            }
        },
        //当前页选择事件
        reportTemplateTableCurrentChange: function (val) {
            //处理参数
            var param = this.reportTemplateTableSearchModel;
            param.currentPage = val;
            //请求后台
            var result = z.msService("user", "IisReportTemplateApi/list", this.reportTemplateTableSearchModel);
            if(result.code === 0){
                this.reportTemplateTableTotal = result.data.totalCount;
                this.reportTemplateData = result.data.data;
            }
        },
        // 点击报告模板表格
        reportTemplateCellClick:function(row, column, cell, event){
            this.chooseOperateType = true;
            this.currentOpeObjType = this.templateOpeType;
            this.operation = this.updateOperation;
            this.currentClickData.id = row.ID;
            this.currentClickData.content = row.TEMPLATEINFO;
            this.currentClickData.title = row.TEMPLATENAME;
        },
        // 点击报告表格
        reportCellClick:function(row, column, cell, event){
            this.chooseOperateType = true;
            this.currentOpeObjType = this.reportOpeType;
            this.operation = this.updateOperation;
            this.currentClickData.id = row.ID;
            this.currentClickData.typeId = row.TYPEID;
            this.currentClickData.content = row.REPORTINFO;
            this.currentClickData.title = row.REPORTNAME;
        },
        // 点击报告分类树
        reportTypeNodeClick:function(data) {
            this.report.typeId = data.ID;
        },
        // 点击新增报告模板按钮
        addReportTemplateClick:function () {
            this.currentOpeObjType = this.templateOpeType;
            this.operation = this.addOperation;
            setTitleVue.dialogVisible = true;
            ue.setContent("");
        },
        // 点击新增报告按钮
        addReportClick:function () {
            this.currentOpeObjType = this.reportOpeType;
            this.operation = this.addOperation;
            setTitleVue.dialogVisible = true;
            ue.setContent("");
        },
        // 点击编辑按钮
        editClick:function () {
            if (!this.chooseOperateType){
                z.info("请选择要编辑的报告或报告模板！");
            } else {
                switch (this.currentOpeObjType){
                    case this.templateOpeType:
                        // 加载报告模板
                        this.reportTemplate.id = this.currentClickData.id;
                        ue.setContent(this.currentClickData.content);
                        this.title = this.currentClickData.title;
                        break;
                    case this.reportOpeType:
                        // 加载报告
                        this.report.id = this.currentClickData.id;
                        ue.setContent(this.currentClickData.content);
                        this.title = this.currentClickData.title;
                        break;
                    default:
                        break;
                }
            }
        },
        // 点击删除按钮
        deleteClick:function () {
            if (!this.chooseOperateType){
                z.info("请选择要删除的报告或报告模板！");
            } else {
                z.confirm("确定要删除当前选择的" + this.currentOpeObjType +"吗？", function () {
                    var result;
                    switch (editOnlineReportVue.currentOpeObjType){
                        case this.templateOpeType:
                            // 删除报告模板
                            result = z.msService("user", "IisReportTemplateApi/delete", {id:editOnlineReportVue.currentClickData.id});
                            break;
                        case this.reportOpeType:
                            // 删除报告
                            result = z.msService("user", "IisReportApi/delete", {id:editOnlineReportVue.currentClickData.id});
                            break;
                        default:
                            break;
                    }
                    if (result.code === 0){
                        z.success(result.msg);
                        editOnlineReportVue.setDefaultData();
                    }
                })
            }
        },
        // 点击保存按钮
        saveClick:function () {
            this.isSave = true;
            var result;
            switch (this.operation){
                case  this.updateOperation:
                    switch (this.currentOpeObjType){
                        case this.templateOpeType:
                            // 更新报告模板
                            this.reportTemplate.templateName = this.title;
                            this.reportTemplate.templateInfo = ue.getContent();
                            if(z.isNullOrEmpty(this.reportTemplate.templateName)){
                                z.info("请输入模板名称！");
                                setTitleVue.dialogVisible = true;
                                return;
                            }
                            if(z.isNullOrEmpty(this.reportTemplate.templateInfo)){
                                z.info("请输入模板内容！");
                                return;
                            }
                            result = z.msService("user", "IisReportTemplateApi/update",this.reportTemplate);
                            break;
                        case this.reportOpeType:
                            // 更新报告
                            this.report.reportName = this.title;
                            this.report.reportInfo = ue.getContent();
                            if(z.isNullOrEmpty(this.report.reportName)){
                                z.info("请输入报告名称！");
                                setTitleVue.dialogVisible = true;
                                return;
                            }
                            if(z.isNullOrEmpty(this.report.typeId)){
                                z.info("请选择报告所属的分类！");
                                return;
                            }
                            if(z.isNullOrEmpty(this.report.reportInfo)){
                                z.info("请输入报告内容！");
                                return;
                            }
                            result = z.msService("user", "IisReportApi/update",this.report);
                            break;
                        default:
                            z.info("请选择新增的类型。");
                            return;
                            break;
                    }
                    break;
                case this.addOperation:
                    switch (this.currentOpeObjType){
                        case this.templateOpeType:
                            // 保存报告模板
                            this.reportTemplate.templateName = this.title;
                            this.reportTemplate.templateInfo = ue.getContent();
                            if(z.isNullOrEmpty(this.reportTemplate.templateName)){
                                z.info("请输入模板名称！");
                                setTitleVue.dialogVisible = true;
                                return;
                            }
                            if(z.isNullOrEmpty(this.reportTemplate.templateInfo)){
                                z.info("请输入模板内容！");
                                return;
                            }
                            result = z.msService("user", "IisReportTemplateApi/save",this.reportTemplate);
                            break;
                        case this.reportOpeType:
                            // 保存报告
                            this.report.reportName = this.title;
                            this.report.reportInfo = ue.getContent();
                            if(z.isNullOrEmpty(this.report.reportName)){
                                z.info("请输入报告名称！");
                                setTitleVue.dialogVisible = true;
                                return;
                            }
                            if(z.isNullOrEmpty(this.report.typeId)){
                                z.info("请选择报告所属的分类！");
                                return;
                            }
                            if(z.isNullOrEmpty(this.report.reportInfo)){
                                z.info("请输入报告内容！");
                                return;
                            }
                            result = z.msService("user", "IisReportApi/save",this.report);
                            break;
                        default:
                            z.info("请选择新增的类型。");
                            return;
                            break;
                    }
                    break;
                default:
                    z.info("请选择报告或者报告模板。");
                    return;
                    break;
            }
            if (result.code === 0){
                z.success(result.msg);
                editOnlineReportVue.setDefaultData();
            }
        },
        // 点击退出按钮
        // exitClick:function () {
        //     if (!this.isSave ){
        //         z.confirm("当前编辑内容未保存，确定要退出吗？", function () {
        //             // 退出当前系统
        //         })
        //     }
        // },
        // 点击导出按钮
        exportClick: function(params){
            //定义一个form表单
            var form=$("<form></form>");
            //设置相关属性
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("enctype","multipart/form-data");
            form.attr("method","post");
            form.attr("action","http://10.142.50.100:8080/fileplug/fileConvertApi/htmlToDoc");
            //将表单放置在web中
            $("body").append(form);
            //定义参数
            var input = $("<input></input>");
            input.attr("type","text");
            input.attr("name","html");
            input.attr("value",this.report.reportInfo);
            form.append(input);
            //表单提交
            form.submit();
        },
        // 设置成默认数据
        setDefaultData:function() {
            this.currentOpeObjType = "";
            this.currentChooseOpeObjId = "";
            this.chooseOperateType=false;
            this.report.id = null;
            this.report.typeId = "";
            this.report.reportName = "";
            this.report.reportInfo = "";
            this.reportTemplate.id = null;
            this.reportTemplate.templateName="";
            this.reportTemplate.templateInfo = "";
            this.isSave = false;
            this.title = "";
            this.operation = null;
            this.currentClickData.id = null;
            this.currentClickData.typeId = null;
            this.currentClickData.currentOpeObjType = null;
            this.currentClickData.content = null;
            this.currentClickData.title = null;
            ue.setContent("");
            var result;
            // 初始化报告分类
            result = z.msService("user","IisReportTypeApi/list",{typeName: "", isOutside: 0});
            if(result.code === 0){
                this.reportTypeTreeData = result.data;
            }
            // 初始化报告表
            result = z.msService("user", "IisReportApi/list", this.reportTableSearchModel);
            if(result.code === 0){
                this.reportTableTotal = result.data.totalCount;
                this.reportData = result.data.data;
            }
            // 初始化报告模板
            result = z.msService("user", "IisReportTemplateApi/list", this.reportTableSearchModel);
            if(result.code === 0){
                this.reportTemplateTableTotal = result.data.totalCount;
                this.reportTemplateData = result.data.data;
            }
        }
    },
    mounted: function() {
        var result;
        // 初始化报告分类
        result = z.msService("user","IisReportTypeApi/list",{typeName: ""});
        if(result.code === 0){
            this.reportTypeTreeData = result.data;
        }
        // 初始化报告表
        result = z.msService("user", "IisReportApi/list", this.reportTableSearchModel);
        if(result.code === 0){
            this.reportTableTotal = result.data.totalCount;
            this.reportData = result.data.data;
        }
        // 初始化报告模板
        result = z.msService("user", "IisReportTemplateApi/list", this.reportTableSearchModel);
        if(result.code === 0){
            this.reportTemplateTableTotal = result.data.totalCount;
            this.reportTemplateData = result.data.data;
        }
    }
});

var setTitleVue = new Vue({
    el:"#setTitle",
    data:{
        dialogVisible:false,
        title:""
    },
    methods:{
        setTitle:function () {
            this.dialogVisible = false;
            editOnlineReportVue.title = this.title;
            // ue.setContent("");
            this.title = ""
        }
    }
});