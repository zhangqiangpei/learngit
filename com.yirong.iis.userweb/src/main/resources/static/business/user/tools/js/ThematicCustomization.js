vm = new Vue({
    el: '#left',
    data: {
        keyword:'',
        tabActiveName:'first',
        thematic:[{name:'美元汇率',id:'1',isPublish:1},{name:'阿根廷大选',id:'2',isPublish:1},{name:'长江基建',id:'3',isPublish:1},{name:'未发布',id:'1',isPublish:0},{name:'未发布',id:'2',isPublish:0},{name:'未发布',id:'3',isPublish:0}]
    },
    methods: {
        fnAutoSrhThematic:function(keyword,cb){
            cb([{value:'智能匹配结果'},{value:'智能匹配结果'},{value:'智能匹配结果'}]);
        },
        fnSelThematic:function(){
            alert(this.keyword);
        }
    },
    mounted: function() {
    }
})