var onlineVue = new Vue({
    el:"#main",
    data:{
        reportData:{
            id:null,
            kmId:null
        },
        myFavorite:{
            objId:null
        },
        myCare:{
            objId:null
        },
        isFavorite:false,
        isCare:false
    },
    methods:{
        addFavorite:function () {
            var result = z.msService("user", "IisMyFavoritesApi/save", this.myFavorite);
            if (result.code === 0){
                this.isFavorite = true;
                z.success(result.msg);
            }
        },
        cancelFavorite:function () {
            var result = z.msService("user", "IisMyFavoritesApi/delete", {id: this.reportData.id});
            if (result.code === 0){
                this.isFavorite = false;
                z.success(result.msg);
            }
        },
        addCare:function () {
            var result = z.msService("user", "IisMyCareApi/save", this.myFavorite);
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
                this.myFavorite.objId = id;
                this.myCare.objId = id;
            }
            var kmId = z.url().kmId;
            if (z.isNotNullOrEmpty(kmId)) {
                this.reportData.kmId = kmId;
            }
        } catch (e) {
        }
        this.docOnline();
        var result;
        result = z.msService("user", "IisMyFavoritesApi/getByObjIdAndCreator", {id: this.reportData.id});
        if (result.code === 0){
            if (result.data.length === 0){
                this.isFavorite = false;
            } else {
                this.isFavorite = true;
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