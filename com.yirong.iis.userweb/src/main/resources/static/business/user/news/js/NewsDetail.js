var mainAttr={
    el:"#main",
    data:{
        newsId:"",
        news:"",
        newsType:[],
        isFavorite:"",
        myFavorite:{
            objId:null
        }
    },
    methods:{
        print:function () {
            
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
    },
    mounted:function () {
        //外部链接传参
        try {
            var id = z.url().id;
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
        }
        result = z.msService("user", "IisMyFavoritesApi/getByObjIdAndCreator", {id: this.newsId});
        if (result.code === 0){
            if (result.data.length === 0){
                this.isFavorite = false;
            } else {
                this.isFavorite = true;
            }
        }
        this.selectInit("user");
        this.newsTypes = this.selectSearch("024");
        for (var j = 0; j<this.newsTypes.length; j++){
            if (this.news.TYPE === this.newsTypes[j].value){
                this.news.TYPE = this.newsTypes[j].label;
                break;
            }
        }
    }
};
// 添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);