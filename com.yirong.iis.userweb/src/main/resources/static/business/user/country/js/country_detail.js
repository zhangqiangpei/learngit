var vm = new Vue({
    el: '#main',
    data: {
       menuList:[],
       defOpens:["1","2","3","4","5"],
       countryName:'',
       countryList:[{name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}],
       defContinent:'1',//默认大洲
       continentList:[{name:'北美洲',id:'1'},{name:'南美洲',id:'2'},{name:'欧洲',id:'3'},{name:'亚洲',id:'4'},{name:'非洲',id:'5'},{name:'大洋洲',id:'6'}]//大洲列表
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
        },
        fnSrhCountry:function(){
            alert('search');
        },
        getIFrameBodyHeight:function(sIFrame){
            if(z.isNullOrEmpty(sIFrame))return;
            var oIFrame = document.getElementById(sIFrame);
            var iHeight = 0;
            iHeight = $(oIFrame.contentWindow.document.body).height();
            return iHeight;
        },
        loadPageToIFrame:function(sURL){
            var iframe = document.getElementById('contentCenter');
            iframe.src = "/forward.do?viewPath=business/user/country/country.html&idx=2";
            var iHeight = $(document).height();
            if (iframe.attachEvent){    
                iframe.attachEvent("onload", function(){
                    iHeight = vm.getIFrameBodyHeight('contentCenter')?vm.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px'); 
                });
            } else {    
                iframe.onload = function(){
                    iHeight = vm.getIFrameBodyHeight('contentCenter')?vm.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px');
                };
            }
        }
    },
    mounted: function() {
        this.initMenu();
        //this.resizeIframe('contentCenter');
    }
})

