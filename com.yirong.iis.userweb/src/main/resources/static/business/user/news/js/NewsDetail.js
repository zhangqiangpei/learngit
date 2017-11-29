var mainAttr={
    el:"#main",
    data:{
        newsId:"",
        news:"",
        newsType:[],
        isFavorite:"",
        myFavorite:{
            objId:null
        },
        isChinese:false,
        isEnglish:false,
        chooseEng:true,
        isTrack:false,
        addProjectTrackDetailDialogVisible:false,
        newProjectTrackDialogVisible:false,
        trackType:[],
        tableSearchModel:{
            typeCode:""
        },
        projectTrackModel:{
            typeCode:"",
            projectName:"",
            projectInfo:"",
            reason:"",
            keyWord:""
        },
        projectTrackDetailModel:{
            proId:"",
            objId:"",
            isSign:"1",
            objOrder:1
        },
        signList:[{key:"1",label:"作为标记点"},{key:"0",label:"不作为标记点"}],
        rules:{
            projectName: [
                { required: true, message: '请输入名称', trigger: 'blur' },
                { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
            ]
        },
        correctDialogVisible:false
    },
    methods:{
        // 修正按钮
        correctBtnClick:function () {
            this.correctDialogVisible = true;
        },
        tableRowClick:function (row, event, column) {
            this.projectTrackDetailModel.proId = row.ID;
        },
        // 保存追踪明细
        saveProjectTrackDetail:function () {
            if (z.isNullOrEmpty(this.projectTrackDetailModel.proId)){
                z.info("请选择追踪项目！");
                return;
            }
            z.msServiceAsync("user","IisProjectTrackDetailApi/save", this.projectTrackDetailModel, function (result) {
                if (result.code === 0){
                    z.success(result.msg);
                    mainVue.addProjectTrackDetailDialogVisible = false;
                    mainVue.projectTrackDetailModel.proId = "";
                }
            })
        },
        // 保存追踪
        saveProjectTrack:function () {
            this.$refs["projectTrackForm"].validate((valid) => {
                if (valid) {
                    var result = z.msService("user", "IisProjectTrackApi/save", this.projectTrackModel);
                    if (result.code === 0){
                        z.success(result.msg);
                        this.newProjectTrackDialogVisible = false;
                        this.searchClick();
                    }
                }
            });
        },
        // 追踪时，追踪类型发生变化
        aptdTypeCodeChange:function (typeCode) {
            mainVue.projectTrackDetailModel.proId = "";
            this.searchClick();
        },
        // 追踪
        addTrack:function () {
            this.addProjectTrackDetailDialogVisible = true;
        },
        print:function () {
            // 保留样式打印
            $("#isChineseContent").print({
                globalStyles: true,
                mediaPrint: false,
                stylesheet: null,
                noPrintSelector: ".no-print",
                iframe: true,
                append: null,
                prepend: null,
                manuallyCopyFormValues: true,
                deferred: $.Deferred(),
                timeout: 750,
                title: null,
                doctype: '<!doctype html>'
            });
        },
        turnToChinese:function () {
            this.chooseEng = false;
        },
        turnToEnglish:function () {
            this.chooseEng = true;
        },
        // 添加我的收藏
        addFavorite:function () {
            var result = z.msService("user", "IisMyFavoritesApi/save", this.myFavorite);
            if (result.code === 0){
                this.isFavorite = true;
                z.success(result.msg);
            }
        },
        // 取消收藏
        cancelFavorite:function () {
            var result = z.msService("user", "IisMyFavoritesApi/delete", {id: this.newsId});
            if (result.code === 0){
                this.isFavorite = false;
                z.success(result.msg);
            }
        },
        //查询
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.currentPage = 1;
            //查询后台
            this.tableSearch(param);
        },
    },
    mounted:function () {
        //外部链接传参
        try {
            var id = z.url().esId;
            if (z.isNotNullOrEmpty(id)) {
                this.newsId = id;
                this.myFavorite.objId = id;
            }
        } catch (e) {
        }
        var result;
        result = z.msService("user", "IisEsSearchApi/getReportById", {id:this.newsId});
        if (result.code === 0){
            this.news=result.data;
            this.projectTrackDetailModel.objId = this.news.new_id;
        }
        // 新闻是中文
        if (this.news.COUNTRY_ENG_NAME === 'China'){
            this.isChinese = true;
        } else {
            this.isChinese = false;
        }
        // 新闻是英文
        if (this.news.COUNTRY_ENG_NAME === 'America'){
            this.isEnglish = true;
        } else {
            this.isEnglish = false;
        }
        // 判断是否已经追踪
        result = z.msService("user", "IisMyFavoritesApi/getByObjIdAndCreator", {id: this.newsId});
        if (result.code === 0){
            if (result.data.length === 0){
                this.isFavorite = false;
            } else {
                this.isFavorite = true;
            }
        }
        this.selectInit("user");
        // 新闻类型
        this.newsTypes = this.selectSearch("024");
        // 追踪类型
        this.trackType = this.selectSearch("026");
        for (var j = 0; j<this.newsTypes.length; j++){
            if (this.news.TYPE === this.newsTypes[j].value){
                this.news.TYPE = this.newsTypes[j].label;
                break;
            }
        }
        if (this.trackType.length > 0){
            this.tableSearchModel.typeCode = this.trackType[0].value;
            this.projectTrackModel.typeCode = this.trackType[0].value;
        }

        //初始化table
        this.tableInit("user", "IisProjectTrackApi/list");
        //默认排序
        this.tableInitSort("CREATE_TIME", "desc");
        this.searchClick();
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);

$(function(){
    // 全屏浏览
    document.getElementById("fullScreenContent").onclick =
        document.getElementById("fullScreenContentCN").onclick =
            document.getElementById("fullScreenContentEN").onclick = function () {
        var elem = this.parentNode.parentNode.parentNode.parentNode;
        requestFullScreen(elem);
    };
    function requestFullScreen(element) {
        // beforeH = document.getElementById("content").style.height;
        // beforeW = document.getElementById("content").style.width;
        // document.getElementById("content").style.width=window.screen.availWidth - 30 +"px";
        // document.getElementById("content").style.height=window.screen.availHeight - 30 +"px";
        var requestMethod = element.requestFullScreen || element.webkitRequestFullScreen || element.mozRequestFullScreen || element.msRequestFullScreen;
        if (requestMethod) {
            requestMethod.call(element);
        } else if (typeof window.ActiveXObject !== "undefined") {
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        }
    }
    // 打印
    document.getElementById("printContentBtn").onclick = function () {
        // 保留样式打印
        $("#content").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null,
            manuallyCopyFormValues: true,
            deferred: $.Deferred(),
            timeout: 750,
            title: null,
            doctype: '<!doctype html>'
        });
        // 不保留样式打印
        // let newWindow = window.open("_blank");   //打开新窗口
        // let codeStr = this.parentNode.parentNode.parentNode.parentNode.innerHTML;   //获取需要生成pdf页面的新闻div代码
        // newWindow.document.write(codeStr);   //向文档写入HTML表达式或者JavaScript代码
        // newWindow.document.close();     //关闭document的输出流, 显示选定的数据
        // newWindow.print();   //打印当前窗口
    };
    document.getElementById("printContentBtnEN").onclick = function () {
        // 保留样式打印
        $("#contentEN").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null,
            manuallyCopyFormValues: true,
            deferred: $.Deferred(),
            timeout: 750,
            title: null,
            doctype: '<!doctype html>'
        });
    };
    document.getElementById("printContentBtnCN").onclick = function () {
        // 保留样式打印
        $("#contentCN").print({
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null,
            manuallyCopyFormValues: true,
            deferred: $.Deferred(),
            timeout: 750,
            title: null,
            doctype: '<!doctype html>'
        });
    }
});

//
// window.onresize = function() {
//     if (!checkFull()) {
//         //要执行的动作
//         document.getElementById("content").style.width = beforeW + "px";
//         document.getElementById("content").style.height = beforeH + "px";
//         // document.getElementById("content").style.overflowY = "initial";
//     }
// }
//
//
// function checkFull() {
//     var isFull = document.fullscreenEnabled || window.fullScreen || document.webkitIsFullScreen || document.msFullscreenEnabled;
//     //to fix : false || undefined == undefined
//     if (isFull === undefined) isFull = false;
//     return isFull;
// }