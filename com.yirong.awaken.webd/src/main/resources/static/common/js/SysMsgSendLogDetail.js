var vm = new Vue({
    el: '#detail',
    data: {
    	detailId:null,
    	senderName:null,
    	msgType:null,
    	msgTitle:null,
    	msgContent:null,
    	createTime:null
    },
    methods: {
        
    },
    mounted: function () {
    	var query = location.search.substring(1);  
    	var values= query.split("id=");
    	this.detailId = values[1];
    	
    	var para = {
    		id : this.detailId
    	}
    	var result = ak.msService("sys","msgLogApi/getMsg",　para);
    	
    	if(result.code == 0){//操作成功
    		var data = result.data;
    		console.log(data);
    		if(null != data){
    			if(ak.isNotNullOrEmpty(data.createTime)){
    				this.createTime =  ak.date.format("yyyy-MM-dd hh:mm:ss",new Date(data.createTime.time));
        		}
    			console.log(data.createTime);
    			this.msgTitle = data.msgTitle;
    			this.senderName = data.senderName;
    			this.msgContent = data.msgContent;
    			$("#contentdiv").html(this.msgContent);
    		}
    	}
 
    }
   
});

 
 