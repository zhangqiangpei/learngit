vm = new Vue({
    el: '#left',
    data: {
        keyword:'',
        tabActiveName:'first',
        thematicList:[]
    },
    methods: {
        //按发布状态
        fnInitThematicListByStatus:function(){
            var param = {};
            param.pageSize = 100;
            param.currentPage = 1;
            z.msServiceAsync("user", "IisThematicApi/list",param,function(r){
                 if(r!=null&&r.code==0){
                     vm.thematicList = r.data.data;
                 }
            })
        },
        fnShowThematic:function(id){
            window.open('/forward.do?viewPath=business/user/tools/thematic/ThematicCustomizationOpt.html&thematicId='+id);
        },
        fnAutoSrhThematic:function(keyword,cb){
            var param = {};
            param.pageSize = 10;
            param.currentPage = 1;
            param.thematicName = keyword;
            z.msServiceAsync("user", "IisThematicApi/list",param,function(r){
                 if(r!=null&&r.code==0){
                     var data = r.data.data;
                     if(data.length>0){
                         var arr = [];
                         for(var i=0;i<data.length;i++){
                             var jo = {};
                             jo.value = data[i].thematicName;
                             jo.id = data[i].id;
                             arr.push(jo);
                         }
                         cb(arr);
                     }else{
                         cb([]);
                     }
                 }else{
                     cb([]);
                 }
            })
            
        },
        fnSelThematic:function(){
            $('#iframeThematicOpt').attr('src','/forward.do?viewPath=business/user/tools/thematic/ThematicCustomizationList.html&thematicName='+this.keyword);
            //var vo = document.getElementById('iframeThematicOpt').contentWindow.vm;
            //vo.$data.thematicName = this.keyword;
            //vo.fnInitThematicList();
        }
    },
    mounted: function() {
        this.fnInitThematicListByStatus();
    }
})
