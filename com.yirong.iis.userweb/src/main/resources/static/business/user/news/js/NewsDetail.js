var mainAttr={
    el:"#main",
    data:{
        newsId:"",
        news:"",
        newsType:[]
    },
    methods:{

    },
    mounted:function () {
        //外部链接传参
        try {
            if (z.isNotNullOrEmpty(z.url().id)) {
                this.newsId = z.url().id;
            }
        } catch (e) {
        }
        var result = z.msService("user", "IisEsSearchApi/getReportById", {id:this.newsId});
        if (result.code === 0){
            this.news=result.data;
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