vm = new Vue({
    el: '#main',
    data: {
        thematicList:[],
        thematicName:'',
        pageSize:12,
        currentPage:1,
        totalCount:0
    },
    methods: {
        fnInitThematicList:function(){
            var param = {};
            param.pageSize = this.pageSize;
            param.currentPage = this.currentPage;
            param.thematicName = this.thematicName;
            var result = z.msService("user", "IisThematicApi/list",param);
            if(result!=null&&result.code==0){
                this.thematicList = result.data.data;
                this.currentPage = result.data.currentPage;
                this.pageSize = result.data.pageSize;
                this.totalCount = result.data.totalCount;
                setTimeout(function(){vm.fnResizeOutFrame()},100);
            }
        },
        fnSizeChange: function (val) {
            this.pageSize = val;
            this.fnInitThematicList();
        },
        fnCurrentChange: function (val) {
            this.currentPage = val;
            this.fnInitThematicList();
        },
        fnAddThematic:function(){
            parent.$('#iframeThematicOpt').attr('src','/forward.do?viewPath=business/user/tools/thematic/ThematicCustomizationOpt.html&isEdit=true');
        },
        fnShowThematic:function(id){
            window.open('/forward.do?viewPath=business/user/tools/thematic/ThematicCustomizationOpt.html&thematicId='+id);
        },
        fnEditThematic:function(item){
            parent.$('#iframeThematicOpt').attr('src','/forward.do?viewPath=business/user/tools/thematic/ThematicCustomizationOpt.html&isEdit=true&thematicId='+item.id+'&status='+item.status);
        },
        fnDelThematic:function(id){
            z.msServiceAsync("user", "IisThematicApi/delete",{ids:id},function(r){
                 if(r!=null&&r.code==0){
                     z.success('删除专题成功!');
                     vm.fnInitThematicList();
                 }else{
                     z.error('删除专题失败!');
                 }
            })
        },
        fnFormatDate:function(date){
            return z.date.format('yyyy-MM-dd HH:mm:ss',date);
        },
        //重置外层iframe高度
        fnResizeOutFrame:function(){
            top.$('#iframeThematicOpt').parent().height($('#main').height()+'px');
            top.$('#iframeThematicOpt').height($('#main').height()+'px');
        }
    },
    mounted: function() {
        var thematicName=z.url(parent.document.getElementById("iframeThematicOpt").contentWindow.location.search).thematicName;
        if(z.isNotNullOrEmpty(thematicName))this.thematicName = thematicName;
        this.fnInitThematicList();
    }
})
