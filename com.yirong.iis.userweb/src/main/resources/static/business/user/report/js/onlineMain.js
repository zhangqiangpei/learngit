var onlineVue = new Vue({
    el:"#main",
    data:{
        reportData:{
            id:null,
            kmId:null,
            reportName:null
        },
        myReport:{
            objId:null
        },
        myCare:{
            objId:null
        },
        isMyReport:false,
        isCare:false
    },
    methods:{
        addMyReport:function () {
            var result = z.msService("user", "IisMyReportApi/save", this.myReport);
            if (result.code === 0){
                this.isMyReport = true;
                z.success(result.msg);
            }
        },
        cancelMyReport:function () {
            var result = z.msService("user", "IisMyReportApi/delete", {id: this.reportData.id});
            if (result.code === 0){
                this.isMyReport = false;
                z.success(result.msg);
            }
        },
        addCare:function () {
            var result = z.msService("user", "IisMyCareApi/save", this.myCare);
            if (result.code === 0){
                this.isCare = true;
                z.success(result.msg);
            }
        },
        cancelCare:function () {
            var result = z.msService("user", "IisMyCareApi/delete", {id: this.reportData.id});
            if (result.code === 0){
                this.isCare = false;
                z.success(result.msg);
            }
        },
        //在线浏览
        docOnline: function () {
            //获取swf路径
            var param = {};
            param.fileId = this.reportData.kmId;
            param.fileType = "SWF";
            $.post("/file/onlineGetFile", param, function (result) {
                if (result.code == 0 && result.data != null) {
                    var pathArray = result.data;
                    var flashvars = {
                        SwfFile: escape(pathArray[0]),
                        Scale: 1,
                        ZoomTransition: "easeOut",
                        ZoomTime: 0.5,
                        ZoomInterval: 0.1,
                        FitPageOnLoad: false,
                        FitWidthOnLoad: false,
                        PrintEnabled: false,
                        FullScreenAsMaxWindow: true,
                        localeChain: "en_US",
                        crossdomain: ""
                    };
                    var params = {
                        meun: "true",// 是否启用右键菜单
                        loop: "true",// 是否循环播放
                        play: "true",// 是否自动播放
                        allowscriptaccess: "sameDomain",
                        quality: "high",// 指定影片质量，可选值：low、autolow、autohigh、medium、high、high;
                        seamlesstabbing: "true",// true、false；
                        wmode: "opaque"
                    };
                    var attributes = {
                        id: "FlexPaperViewer",
                        "class": "FlexPaperViewer",
                        name: "FlexPaperViewer"
                    }
                    var swfUrl = "/frame/swfObject/FlexPaperViewer.swf";
                    swfobject.embedSWF(swfUrl, "swf", "100%", "600px", "9.0.0",
                        swfUrl, flashvars, params, attributes);
                } else {
                    $("#swf").hide();
                    $("#blank-div").show();
                }
            });
        },
    },
    mounted:function () {
        //外部链接传参
        try {
            var id = z.url().id;
            if (z.isNotNullOrEmpty(id)) {
                this.reportData.id = id;
                this.myReport.objId = id;
                this.myCare.objId = id;
            }
            var kmId = z.url().kmId;
            if (z.isNotNullOrEmpty(kmId)) {
                this.reportData.kmId = kmId;
            }
            var reportName = z.url().reportName;
            if (z.isNotNullOrEmpty(reportName)) {
                this.reportData.reportName = reportName;
            }
        } catch (e) {
        }
        this.docOnline();
        var result;
        result = z.msService("user", "IisMyReportApi/getByObjIdAndCreator", {id: this.reportData.id});
        if (result.code === 0){
            if (result.data.length === 0){
                this.isMyReport = false;
            } else {
                this.isMyReport = true;
            }
        }
        result = z.msService("user", "IisMyCareApi/getByObjIdAndCreator", {id: this.reportData.id});
        if (result.code === 0){
            if (result.data.length === 0){
                this.isCare = false;
            } else {
                this.isCare = true;
            }
        }
    }

});