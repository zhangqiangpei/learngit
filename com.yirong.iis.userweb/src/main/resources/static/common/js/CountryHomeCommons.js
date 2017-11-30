var cnVue = new Vue({
    el: '#CountryHome',
    data: {
    	menuList:[],
        defOpens:["1","2","3","4","5"]
    },
    methods: {
    	initMenu:function(){
            var sURL = '/common/json/menu_country.json';
            z.get(sURL,null,function(r){
                if(r.code==0){
                    if(r.data.length>0){
                        vm.menuList = r.data;
                        if(z.isNotNullOrEmpty(r.data[0].children)&&r.data[0].children.length>0){
                            var id = r.data[0].children[0].id;
                             // 选择默认页
                            setTimeout(function () {$('#item_'+id).click();}, 100);
                        }
                    }
                }
            })
        }
    },
    mounted: function() {
    	debugger;
    	this.initMenu();
    }
})

