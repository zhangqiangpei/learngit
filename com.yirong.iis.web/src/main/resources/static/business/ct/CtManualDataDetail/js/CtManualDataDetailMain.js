//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
    	//查询对象
        tableSearchModel: {
        	fileName : null
        }
    },
    methods: {
    	//查询按钮
    	searchClick: function () {
    		var param = this.tableSearchModel;
			// 当前页切换成第一页
        	param.page = 1;
       	    //查询后台
        	this.tableSearch(param);
        },
        //删除按钮
        delClick : function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length == 0){
        		ak.warning("请选择需要删除的记录");
        	}else{
        		ak.confirm("确定要删除选中的【" + ids.length + "】条记录吗？",this.delCallback);
        	}
        },
        //删除回调函数
        delCallback : function(instance){
        	//处理参数
        	var ids = this.tableGetCheckIds();
        	var idsStr = "";
        	$.each(ids,function(i,n){
        		idsStr += n;
        		if(i != ids.length){
        			idsStr += ",";
        		}
        	});
        	//请求后台
        	var result = ak.msService("ct","CtManualDataDetailApi/delete",{ ids : idsStr});
        	if(result.code == 0){//操作成功
        		for (var i = 0; i < ids.length; i++) {
        			//删除eos文件
            		var eosResult = ak.msService("eos","EosApi/deleteFile",{ id : ids[i]});
				}
            	//提示
            	ak.success(result.msg);
            	//刷新表格
            	mainVue.tableRefresh();
            }else{
            	//提示
            	ak.warning(result.msg);
            }
        },
        upFile:function(){
        	ak.dialog("upFile","business/ct/CtManualDataDetail/upFile.html");
        },
        downFile:function(){
        	//获取表格选中的id集
        	var ids = this.tableGetCheckIds();
        	if(ids.length != 1){
        		ak.warning("请选择需要下载的文件！");
        	}else{
        		var idStr =  mainVue.tableGetCheckObjs()[0].id;
        		var result = ak.msService("ct","CtManualDataDetailApi/getSuffix",{ id : idStr});
        		var suffix = mainVue.tableGetCheckObjs()[0].suffixName; 
        		if(result.code==0){
        			var form = $("<form>");   //定义一个form表单
                    form.attr('style', 'display:none');   //在form表单中添加查询参数
                    form.attr('target', '');
                    form.attr('method', 'post');
                    form.attr('action', "/file/downFile");

                    var input1 = $('<input>');
                    input1.attr('type', 'hidden');
                    input1.attr('name', 'fileId');
                    input1.attr('value', idStr);
                    var input2 = $('<input>');
                    input2.attr('type', 'hidden');
                    input2.attr('name', 'suffix');
                    input2.attr('value',result.data);
                    $('body').append(form);  //将表单放置在web中
                    form.append(input1);
                    form.append(input2);
                    //将查询参数控件提交到表单上
                    form.submit();
           		/*
               	var result = ak.msServiceFile("downFile",idStr+"."+suffix);
               	$("#path").attr("href",result.data);
               	document.getElementById("path").click();*/
        		}
        	}
        }
    }
});

//主属性
var mainAttr = {
	el: '#main',
	//页面初始化事件
    mounted: function () {
    	//初始化table
    	this.tableInit("ct","CtManualDataDetailApi/list");
    	/*//默认排序
    	var order = this.tableSearchModel.orders[0];
    	order.orderField="create_time";
    	order.orderType="desc";*/
    	//请求后台
    	this.searchClick();
    	//加载初始化数据
    }
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr,tableAttr);