var dialogAttr = getDialogMergeAttr({
    el: '#info_div',
    data: {
    	content:"无内容"
    },
    methods: {
    },
    mounted: function() {
    	var param={
    			"id":lawVue.showId
    	}
    	var result = ak.msService("user","IisLawsRegulationsApi/get",param);
    	if(result.code ==0){
    		this.content = result.data.content;
    	}
    }
})
var updateVue = new Vue(dialogAttr);

