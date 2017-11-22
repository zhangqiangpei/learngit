var DataMainVue = new Vue({
    el:"#main",
    data:{
    	category:[{}],
    	activeTab:null,
    	allInfo:[],
    	currentTabInfo:[],
    	searchData:null
    },
    methods:{
    	//tab页点击事件
    	handleClick:function(tab, event) {
    		this.activeTab = tab.name;
    		this.currentTabInfo = this.allInfo[this.activeTab];
        },
        //指标查询
        searchReport:function(){
        	var infoData = ak.msService('user','iisDataClassifyApi/getSecondClassifyAndInfo',{"infoName":this.searchData});
        	if(null != infoData && infoData.code == 0){//操作成功
    			this.allInfo = infoData.data;
    			for(var key in this.allInfo){
    				this.activeTab = key;
    				this.currentTabInfo = this.allInfo[this.activeTab];
    				break;
    			}
        	}
        },
        //指标点击事件
        infoClick:function(infoId){
        	
        }
    },
    mounted: function() {
    	
    	var classifyData = ak.msService('user','iisDataClassifyApi/getFirstClassify',null);
    	if(null != classifyData && classifyData.code == 0){//操作成功
			this.category = classifyData.data;
			if(this.category.length>0){
				this.activeTab = this.category[0].id;
			}
    	}
    	
    	var infoData = ak.msService('user','iisDataClassifyApi/getSecondClassifyAndInfo',{"infoName":""});
    	if(null != infoData && infoData.code == 0){//操作成功
			this.allInfo = infoData.data;
			
			this.currentTabInfo = this.allInfo[this.activeTab];
    	}
    	
    }
});