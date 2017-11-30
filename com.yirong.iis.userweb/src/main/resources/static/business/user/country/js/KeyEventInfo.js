var dialogAttr = getDialogMergeAttr({
    el: '#info_div',
    data: {
    	content:"无内容",
    	source:"",
    	releaseTime:''
    },
    methods: {
    },
    mounted: function() {
    	var param={
    			"id":mainVue.showId
    	}
    	var result = ak.msService("user","IisKeyEventsApi/get",param);
    	if(result.code ==0){
    		this.content = result.data.content;
    		this.source =result.data.source;
    		this.releaseTime = result.data.releaseTime;
    	}
    }
})
var updateVue = new Vue(dialogAttr);

