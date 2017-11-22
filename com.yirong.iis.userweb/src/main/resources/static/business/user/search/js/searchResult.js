var searchResultVue = new Vue({
    el:"#main",
    data:{
        search:{
            condition:null
        },
    },
    methods:{
        // 搜索报告
        searchReport:function () {
            window.open("forward.do?viewPath=business/user/search/searchResult.html&search=" +this.searchData)
        }
    },
    mounted:function () {
        //外部链接传参
        try {
            var search = z.url().search;
            if (z.isNotNullOrEmpty(search)) {
                this.search.condition = search;
            }
        } catch (e) {
        }
    }

});