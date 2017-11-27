var vm = new Vue({
    el: '#main',
    data: {
    	//菜单默认展开节点
    	defOpens:["1"],
    	companyId:null
    },
    methods: {
    	back:function(){
    		
    	},
    	//加载iframe
    	loadPageToIFrame:function(sURL){
            var iframe = document.getElementById('contentCenter');
            var idx = z.getUrlParam('idx');
            iframe.src = "/forward.do?viewPath="+sURL+"&idx="+idx;;
            //
            var iHeight = $(document).height();
            if (iframe.attachEvent){    
                iframe.attachEvent("onload", function(){
                    iHeight = vm.getIFrameBodyHeight('contentCenter')?vm.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px'); 
                });
            } else {    
                /*iframe.onload = function(){
                    iHeight = vm.getIFrameBodyHeight('contentCenter')?vm.getIFrameBodyHeight('contentCenter'):iHeight;
                    $('#contentCenter').height(iHeight+'px');
                };*/
            }
        }
    },
    mounted: function() {
    	this.companyId = z.getUrlParam('id');
    }
})

