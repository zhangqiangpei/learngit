var mainAttr={
    el: '#main',
    data: {
    	//菜单默认展开节点
    	defOpens:["1"],
    	companyId:null,
    	companyEntity:null
    },
    methods: {
    	back:function(){
    		var idx = z.getUrlParam('idx');
    		window.location.href = "/forward.do?viewPath=business/user/company/CompanyMain.html&idx="+idx;;
    	},
    	
    	handleSelect:function(){
    		
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
    	//查询公司 
    	var res = z.msService("user", "IisCompanyApi/getByCompanyId",{companyId: this.companyId});
    	if(null != res && res.code == 0){
    		this.companyEntity = res.data;
    	}
    	
    	//this.loadPageToIFrame('business/user/company/CompanySurvey.html');
    }
};

//添加工具
var utilAttr = getUtilMergeAttr({});
//获取table属性
var tableAttr = getTableMergeAttr({});
//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);