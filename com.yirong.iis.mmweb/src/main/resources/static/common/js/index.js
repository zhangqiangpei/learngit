var vm = new Vue({
	el: '#wrap',
	data: {
        menuList: [],
        url:'',
        isCollapse:false,
        divLeftWidth:'',
        myScroll:'',
        msgData: [],
        pageSize : 5,
        msgnum : 0,
        emailnum : 0,
        loginUser:'',
        applicationIds:'',
        userRoleIds:'',
        applicationId:'',
        websocketUtl:null
    },
    methods: {
        loginOut: function () {
            this.$confirm('确认退出系统吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                callback: function (action, instance) {
                    if (action == 'confirm') {
                        
                        ak.ajax({
                    		url : "/baseRest/loginout",
                    		async : false,
                    		success : function(res, textStatus) {
                    			window.location.href = "/login.do";
                    		}
                        });
                       
                    }
                }
            });
        },
        setPwd: function () {
        	ak.dialog("update","common/SysUserPassword.html");
        },
        setAvatar: function () {
        	ak.dialog("update","common/SysUserAvatar.html");
        },
        jump:function(url){
        	var msg = null;
        	//先验证登录信息是否有效
        	ak.ajax({
        		url : "/isCheck.do",
        		async : false,
        		success : function(res, textStatus) {
        			if(res.code == 2){//超时
        				msg = res.msg;
        			}
        		}
            });
            if(null != msg){//超时
            	awakenCommonVue.$alert(msg ,'超时提示', {
		            confirmButtonText: '确定',
		            type: 'warning',
		            callback: function (action, instance) {
		            	window.location.href = "/login.do";
		            }
		        });
            }else{
            	this.url= "/forward.do?viewPath=" + url;
            }
        },
        initMenuTop:function(){
        	this.divLeftWidth = $('#left').width();
        	var sText = '系统管理子系统';
        	var sHTML = '';
        	sHTML +='<div style="clear:both;height:30px;"><div class="divMenuText">'+sText+'</div>';
        	sHTML +='<div class="divMenuOpt">';
        	sHTML +='<i class="el-icon-caret-left" onclick="vm.handleMenu(this)"></i></div></div>';
        	$('#divMenuTop').html(sHTML);
        	$('#divMenuTop').next().height($('#left').height()-$('#divMenuTop').height());
        	this.initIScroll();
        },
        handleMenu:function(obj){
        	if($(obj).attr('class') =='el-icon-caret-left'){
        		$(obj).attr('class','el-icon-caret-right');
        		$('.divMenuText').css('display','none');
        		vm.isCollapse=true;
        	}else{
        		$(obj).attr('class','el-icon-caret-left');
        		$('.divMenuText').css('display','');
        		vm.isCollapse=false;
        		
        	}
        	vm.resizePage();
        },
        resizePage:function(){
        	var iLeft = vm.isCollapse?64:parseInt(vm.divLeftWidth);
        	$('#left').width(iLeft+1);
    		$('#content').width($(window).width()-iLeft-1);
        },
        initIScroll:function(){
        	// 左侧菜单加上滚动条
        	this.myScroll = new IScroll('#divMenu', {
            	scrollbars: true,
                mouseWheel: true,
                interactiveScrollbars: true,        // 是否可拖动滚动条，配合fadeScrollbars使用
                fadeScrollbars: true,              // 是否自动隐藏显示滚动条
                shrinkScrollbars: 'scale',
                resizeScrollbars:true
            });
        },
        handleOpen:function(){
        	setTimeout(function(){vm.myScroll.refresh();},200)
        }
        ,
        handleClose:function(){
        	setTimeout(function(){vm.myScroll.refresh();},200)
        },
        
        //------------------系统消息通知------------------------------
        //后台实体返回date,进行格式化展示
		formatDate :function(row, column){
        	if (row[column.property] != null) {
        		//return  ak.date.format('yyyy-MM-dd HH:mm:ss',new Date(row[column.property].time));
        		var dateTimeStamp = row[column.property].time;
        		var minute = 1000 * 60;
				var hour = minute * 60;
				var day = hour * 24;
				var halfamonth = day * 15;
				var month = day * 30;
				var now = new Date().getTime();
				var diffValue = now - dateTimeStamp;
				if(diffValue < 0){return;}
				var monthC =diffValue/month;
				var weekC =diffValue/(7*day);
				var dayC =diffValue/day;
				var hourC =diffValue/hour;
				var minC =diffValue/minute;
				if(monthC>=1){
					result="" + parseInt(monthC) + "月前";
				}
				else if(weekC>=1){
					result="" + parseInt(weekC) + "周前";
				}
				else if(dayC>=1){
					result=""+ parseInt(dayC) +"天前";
				}
				else if(hourC>=1){
					result=""+ parseInt(hourC) +"小时前";
				}
				else if(minC>=1){
					result=""+ parseInt(minC) +"分钟前";
				}else
				result="刚刚";
				return result;
        	}	
        },
        
        openDetail :function(row, event, column){
		    var url = "common/SysMsgSendLogDetail.html"
       		url = "forward.do?viewPath=" + url+"&id="+row.id;
       	   	window.open(url);
       	   	//刷新 ,已读过滤
	        setTimeout(function(){this.loadUnreadMsg();},1000);
        },
        openList :function(column, event){
		    var url = "common/SysMsgSendLogMain.html"
       		url = "forward.do?viewPath=" + url+"&loginUser="+this.loginUser;
       	   	window.open(url);
        },
        initWebSocket:function(){
    		if(ak.isNullOrEmpty(this.loginUser)){
        		return;
        	}
        	if ('WebSocket' in window) {
        		websocket = new WebSocket("ws://"+this.websocketUtl+"/ws?token="+this.loginUser);
        	} else if ('MozWebSocket' in window) {
        		websocket = new MozWebSocket("ws://"+this.websocketUtl+"/ws?token="+this.loginUser);
        	} else {
        		websocket = new SockJS("http://"+this.websocketUtl+"/ws?token="+this.loginUser);
        	}
        	
        	websocket.onopen = function(event) {
         		console.log("WebSocket:已连接");
         	};
         	
         	websocket.onmessage = function(ev) {
         		var obj=JSON.parse(ev.data);
         		if(!obj || obj == undefined) {
         			return false;
         		}
         	 	if(vm.msgnum >= vm.pageSize){
         	 		var arrayTemp = new Array; 
         	 		$.each(vm.msgData,function(i,n){
		        		if(i == vm.msgnum-1){
		        			arrayTemp.push($.extend({},obj));
		        		}else{
		        			arrayTemp.push($.extend({},n));
		        		}
		        	});
         	 		vm.msgData = arrayTemp;
         	 	}else{
         	 		vm.msgData.push(obj);
         			vm.msgnum = vm.msgnum  + 1 ;
         	 	}
 
         	};
     		websocket.onerror = function(event) {
     			console.log("WebSocket:发生错误 ");
     		};
     		
     		websocket.onclose = function(event) {
     			console.log("WebSocket:已关闭");
     		} ;
        },
        //-----------------end--------------------------
        //开始加载,页面进入初始化
        loadLoginUser : function(){  
        	var result = null;
        	//先获取用户信息
        	ak.ajax({
        		url : "/baseRest/getLoginUser",
        		async : false,
        		success : function(res, textStatus) {
        			if(ak.isNullOrEmpty(res)){
        				ak.error("获取用户信息失败.");
        			}else if(res.code == 0){
        				var data =  res.data;
        				if(ak.isNotNullOrEmpty(data)){
        					$("#userDisplayName").text(data.userDisplayName);
        					$("#organizationName").text(data.organizationName);
							if(null == data.avatarUrl || "" == data.avatarUrl){
								$("#userDisplayName").attr('style','display: inline-block;padding-left: 70px;background:url(common/css/img/头像_u46.png) no-repeat left center;background-size: 60px;');
							}else{
								$("#userDisplayName").attr('style','display: inline-block;padding-left: 70px;background:url('+data.avatarUrl+') no-repeat left center;background-size: 60px;');
							}
        					//document.getElementById("userDisplayName").style.backgroundImage="url("+data.avatarUrl+") no-repeat left center";
        					result = data;
        				}
        			}else{
        				ak.warning(res.msg);
        			}
        		}
            });
        	return result;
        },
        //加载未读消息
        loadUnreadMsg:function(){
        	var result = null; 
        	if(ak.isNullOrEmpty(this.loginUser)){
        		return;
        	}
        	var para = {
        		receiverId : this.loginUser,
        		state:"0",
    			currentPage : 1,
    			pageSize :  this.pageSize,
				orders : [{orderField : "createTime",orderType : "desc"}]
        	}

        	var result = ak.msService("sys","msgLogApi/queryList",para);
        	if(null != result && result.code == 0){//操作成功
           		//分页数据
           		var data = result.data;
           		if(null != data){
           			result = data;
           		}
           	}
           	return result;
        },
        //加载头部的各个子项目的链接地址
        loadApps:function(){
			//加载应用数据
			var appData = ak.msService('sys','loginApi/getApp',{appIds : this.applicationIds}); 
			if(null != appData && appData.code == 0){//操作成功
				var html = '';
				$.each(appData.data, function(idx, bean) {
					if(location.href.indexOf(bean.domain) != -1){
						var url = bean.domain + 'forward.do?viewPath=' + bean.custom_index_view;
						html += '<a href="#" title="'+bean.name+'"  class="cur" >';
						html += '<img src="'+bean.logo+'" />';
						html += '</a>';
					}else{
						var url = bean.domain + 'forward.do?viewPath=' + bean.custom_index_view+ '&' + bean.id;
						html += '<a href="'+url+'" title="'+bean.name+'" target="_blank">';
						html += '<img src="'+bean.logo+'" />';
						html += '</a>';
					}
				});
				$("#headDiv").html(html); 
			}
        },
        //加载菜单
        loadMenu:function(){
        	var result = null;
        	var para = {
        		"roleIds" :this.userRoleIds ,
        		"applicationId" :this.applicationId	
        	}
        	ak.ajax({
        		url : "/baseRest/getMenu",
        		data : para,
        		async : false,
        		success : function(res, textStatus) {
        			if(ak.isNullOrEmpty(res)){
        				ak.error("获取菜单信息失败.");
        			}else if(res.code == 0){	
        				result = ak.ArrayToTreeData(res.data);
        			}else{
        				ak.warning(res.msg);
        			}
        		}
        	});
        	return result;
        },
        loadCount:function(){
        	ak.ajax({
        		url : "/baseRest/getSessionCount",
        		async : false,
        		type : "post",
        		dataType : 'json',
        		success : function(data, textStatus) {
        			if(ak.isNullOrEmpty(data)){
        				ak.error("获取在线人数失败");
        			}else if(data.code == 0){
        				$("#userCount").attr("title","在线人数"+data.data+"人");
        			}else{
        				ak.warning(data.msg);
        			}
        		}
        	});
        }
    },
    
    
    mounted: function () {
    	//当前应用id
    	var query = location.search.substring(1);  
    	var values= query.split("&"); 
    	this.applicationId = values[1];
 
    	var data = this.loadLoginUser();
		//用户拥有权限的应用系统
		this.applicationIds = data.applicationIds;
		this.loginUser = data.userName;
		this.userRoleIds = data.userRoleIds
 
    	this.loadApps(); 
		//加载菜单
		this.menuList = this.loadMenu();
			
			
    	this.loadCount();
    	//导航头部动态加入收缩展开图表
    	setTimeout(function () {vm.initMenuTop();},100);
    	//查询未读消息
    	var data = this.loadUnreadMsg();
    	if(null != data){
    		this.msgnum = data.totalCount;
    		this.msgData = data.data;
    	}
		
		
    	//消息系统初始化
		var configJson = ak.msService('sys','sysConfigApi/getWebsocketUrl',null); 
		if(null != configJson && configJson.code == 0){//操作成功
			this.websocketUtl = configJson.data.url;
			this.initWebSocket();
		}
    	
    }	
});
 
