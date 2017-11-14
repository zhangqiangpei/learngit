var vm_search = new Vue({
    el: '#search',
    data: {
        countryName:'',//国家名称
        continentName:'',//大洲名称
        continentList:[{name:'北美洲',id:'1'},{name:'南美洲',id:'2'},{name:'欧洲',id:'3'},{name:'亚洲',id:'4'},{name:'非洲',id:'5'},{name:'大洋洲',id:'6'}]//大洲列表
    },
    methods: {
    
    },
    mounted: function() {

    }
})

var vm_country = new Vue({
    el: '#main',
    data: {
        activeName:'first',
        labelName:'全部国家',
        countryList:[{name:'A-E',id:'222',children:[{name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}]},{name:'A-E',id:'222',children:[{name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}]},{name:'A-E',id:'222',children:[{name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}]},{name:'A-E',id:'222',children:[{name:'阿根廷',id:'1'},{name:'巴拉圭',id:'2'},{name:'巴西',id:'3'},{name:'秘鲁',id:'4'},{name:'玻利维亚',id:'5'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'},{name:'大洋洲',id:'6'}]}]//国家列表
    },
    methods: {
        showCountryDetail:function(id){
            var sURL = 'forward.do?viewPath=business/user/country/country_detail.html&idx='+z.getUrlParam('idx')+'&countryId='+id;
            window.location.href = sURL;
        }
    },
    mounted: function() {
    }
})