window.z = top.z || {
    // 统一的ajax请求
	ajax : function(opts) {
		// 合并参数与默认
		opts = $.extend({
			// 默认异步执行（true异步，false同步）
			async : true,
			// 默认post协议请求
			type : "POST",
			// 默认json格式请求
			dataType : "json",
			// 默认不启用缓存
			cache : false,
			// 默认全屏遮盖
			notice : true
		}, opts);
		// 遮盖
		if (opts.notice) {
			z.ld();
		}
		// 请求后台
		$.ajax({
			url : opts.url,
			data : opts.data,
			async : opts.async,
			type : opts.type,
			dataType : opts.dataType,
			cache : opts.cache,
			success : function(data, textStatus) {
				opts.success(data, textStatus);
				// 解除遮盖
				if (opts.notice) {
					setTimeout(function(){z.lc();},100);
				}
			},
			error : function(errors, textStatus) {
				z.error(textStatus);
				// 解除遮盖
				if (opts.notice) {
					setTimeout(function(){z.lc();},100);
				}
			}
		});
	},
    get: function(url, data, ok, error) {
        $.ajax({
            url: url,
            data: data,
            async : true,
            dataType: 'json',
            success: ok,
            error: function(res) {
                z.error('服务器请求失败');
                if (error) {
                    error(res);
                }
            }
        });
    },
    post: function(url, data, ok, error) {
        $.ajax({
            url: url,
            data: data,
            type: 'post',
            async : true,
            dataType: 'json',
            success: ok,
            error: function(res) {
                z.error('服务器请求失败');
                if (error) {
                    error(res);
                }
            }
        });
    },
    put: function(url, data, ok, error) {
        $.ajax({
            url: url,
            data: data,
            type: 'put',
            async : true,
            dataType: 'json',
            success: ok,
            error: function(res) {
                z.error('服务器请求失败');
                if (error) {
                    error(res);
                }
            }
        });
    },
    del: function(url, data, ok, error) {
        $.ajax({
            url: url,
            data: data,
            type: 'delete',
            async : true,
            dataType: 'json',
            success: ok,
            error: function(res) {
                z.error('服务器请求失败');
                if (error) {
                    error(res);
                }
            }
        });
    },
    url: function(url) {
        if (!url) {
            url = location.search.substring(1);
        } else {
            url = url.substr(url.indexOf('?') + 1);
        }
        var args = new Object();
        var query = decodeURI(url);
        var pairs = query.split('&');
        for (var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            args[argname] = decodeURI(value);
        }
        return args;
    },
    date: {
        format: function(format, date) {
            //格式化日期 format('yyyy-MM-dd hh:mm:ss:SS 星期w 第q季度')
            if (!date) {
                date = new Date();
            } else if (typeof(date) == 'string') {
                date = new Date(date);
            }
            var Week = ['日', '一', '二', '三', '四', '五', '六'];
            var o = {
                'y+': date.getYear(), //year
                'M+': date.getMonth() + 1, //month 
                'd+': date.getDate(), //day 
                'h+': date.getHours(), //hour 
                'H+': date.getHours(), //hour
                'm+': date.getMinutes(), //minute 
                's+': date.getSeconds(), //second 
                'q+': Math.floor((date.getMonth() + 3) / 3), //quarter 
                'S': date.getMilliseconds(), //millisecond 
                'w': Week[date.getDay()]
            }
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp('(' + k + ')').test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
                }
            }
            return format;
        },
        difference: function(d1, d2) {
            var date1, date2, date3;
            if (typeof(d1) == 'string') {
                date1 = new Date(d1);
            } else {
                date1 = d1;
            }
            if (typeof(d2) == 'string') {
                date2 = new Date(d2);
            } else {
                date2 = d2;
            }

            date3 = date2.getTime() - date1.getTime() //时间差的毫秒数

            if (date3 <= 0) {
                return '已过期';
            }

            //计算出相差天数
            var days = Math.floor(date3 / (24 * 3600 * 1000))

            //计算出小时数
            var leave1 = date3 % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000))
                //计算相差分钟数
            var leave2 = leave1 % (3600 * 1000) //计算小时数后剩余的毫秒数
            var minutes = Math.floor(leave2 / (60 * 1000))
                //计算相差秒数
            var leave3 = leave2 % (60 * 1000) //计算分钟数后剩余的毫秒数
            var seconds = Math.round(leave3 / 1000)

            return days + '天' + hours + '小时' + minutes + '分钟' + seconds + '秒';
        }

    },
    ld: function() {
        if (top.z._loading_count === undefined) {
            top.z._loading_count = 0;
        }
        top.z._loading_count++;
        top.z._loading = Vue.prototype.$loading({ fullscreen: true });
    },
    lc: function() {
        if (top.z._loading_count === undefined) {
            return;
        }
        top.z._loading_count--;

        if (top.z._loading_count == 0) {
            if (top.z._loading) {
                top.z._loading.close();
                top.z._loading = null;
            }
        }
    },
    //局部（指定部分）显示加载动画
    fnLoading:function(target,bClose){
        if(z.isNullOrEmpty(target))return;
        if(bClose){
            if (z['__loading_'+target]) {
                z['__loading_'+target].close();
                z['__loading_'+target] = null;
            }
        }else{
            var obj = typeof(target)==='string'?$('#'+target).get(0):target;
            if(!z['__loading_'+target])z['__loading_'+target]= Vue.prototype.$loading({target:obj });
        }
    },
    success: function(msg, time) {
        if (time === undefined) {
            time = 2000;
        }
        Vue.prototype.$message.success({
            message: msg,
            showClose: true,
            duration: time
        });
    },
    warning: function(msg, time) {
        if (time === undefined) {
            time = 2000;
        }
        Vue.prototype.$message.warning({
            message: msg,
            showClose: true,
            duration: time
        });
    },
    info: function(msg, time) {
        if (time === undefined) {
            time = 2000;
        }
        Vue.prototype.$message.info({
            message: msg,
            showClose: true,
            duration: time
        });
    },
    error: function(msg, time) {
        if (time === undefined) {
            time = 2000;
        }
        Vue.prototype.$message.error({
            message: msg,
            showClose: true,
            duration: time
        });
    },
    confirm:function(msg,callback){
        Vue.prototype.$confirm(msg, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            callback: callback
        });
    },
     //是否为空（true是，false否）
    isNullOrEmpty : function(obj){
    	if(null == obj || undefined == obj || obj.length == 0){
    		return true;
    	}else{
    		false;
    	}
    },
     //是否不为空（true否，false是）
    isNotNullOrEmpty : function(obj){
    	return !this.isNullOrEmpty(obj);
    },
    //下载文件
    downFile : function(actionUrl,params){

        //定义一个form表单
        var form=$("<form></form>");
        //设置相关属性
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");
        form.attr("action",actionUrl);
        //将表单放置在web中
        $("body").append(form);
        //定义参数
        if(params){
            $.each(params,function(i,n){
                var input = $("<input></input>");
                input.attr("type","hidden");
                input.attr("name",n.name);
                input.attr("value",n.value);
                form.append(input);
            });
        }
        //表单提交
        form.submit();
    },
     // 微服务请求（同步）
	msService : function(serviceName, methodName, param,loadingTarget) {
        if(this.isNotNullOrEmpty(loadingTarget))this.fnLoading(loadingTarget);
		var result = null;
		if(this.isNotNullOrEmpty(param)){
			param = JSON.stringify(param);
		}
		var data = {
			serviceName : serviceName,
			methodName : methodName,
			param : param
		};
		var url = '/msService';
        $.ajax({
			url : url,
			data : data,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(data, textStatus) {
				//判断系统级
				if(data.code == 0){//成功
					//判断业务级
					var bdata = data.data;
					if(bdata.code!= 0){//业务异常
						z.warning(bdata.msg);
					}
					//不管失败与否均要返回数据
					result = bdata;
				}else if(data.code == 2){//超时
                    z.confirm(data.msg,function(action, instance){
                        if(action=='confirm')top.window.location.href = "/forward.do?viewPath=login.html";
                    });					
				}else{//异常
					z.error(data.msg);
				}
                if(z.isNotNullOrEmpty(loadingTarget))z.fnLoading(loadingTarget,true);
			}
		});
		return result;
	},
	// 微服务请求（异步）
	msServiceAsync : function(serviceName, methodName, param,callBackFun,loadingTarget) {
        if(this.isNotNullOrEmpty(loadingTarget))this.fnLoading(loadingTarget);
		if(this.isNotNullOrEmpty(param)){
			param = JSON.stringify(param);
		}
		var data = {
			serviceName : serviceName,
			methodName : methodName,
			param : param
		};
		var url = '/msService';
        $.ajax({
			url : url,
			data : data,
			async : true,
			notice : false,
			type : 'post',
			dataType : 'json',
			success : function(data, textStatus) {
				//判断系统级
				if(data.code == 0){//成功
					//判断业务级
					var bdata = data.data;
					if(bdata.code!= 0){//业务异常
						z.warning(bdata.msg);
					}
					//不管失败与否均要返回数据
					callBackFun(bdata);
				}else if(data.code == 2){//超时
                    z.confirm(data.msg,function(action, instance){
                        if(action=='confirm')top.window.location.href = "/forward.do?viewPath=login.html";
                    });					
				}else{//异常
					z.error(data.msg);
				}
                if(z.isNotNullOrEmpty(loadingTarget))z.fnLoading(loadingTarget,true);
			}
		});
	},
	//数组数据转ui能识别的树形数据（必须为awaken规定格式使用，根ID必须为""）
    ArrayToTreeData : function (arrayData){
    	//返回结果
    	var result = [];
    	//临时map
        var tmpMap = [];
        //将所有数据存入map，ID为节点id、value为节点对象
        for (i=0, l=arrayData.length; i<l; i++) {
        	//获取对象（必须操作原对象）
        	var data = arrayData[i];
        	//数组节点指向原对象
            tmpMap[data.id] = data;
        }
        //再次循环数组
        for (i=0, l=arrayData.length; i<l; i++) {
        	//获取对象（必须操作原对象）
        	var data = arrayData[i];
            if (z.isNotNullOrEmpty(tmpMap[data.pid]) && data.id != data.pid) {//其他节点
                if (!tmpMap[data.pid].children){//如果对象子节点属性不存在，需创建
                	tmpMap[data.pid].children = [];
                }
                tmpMap[data.pid].children.push(data);
            } else {//根节点
                result.push(data);
            }
        }
    	return result;
    },
    getUrlParam : function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    },
    /////将JSON转为字符串
    Obj2str:function (o) {
        if (o == undefined) {
            return "";
        }
        var r = [];
        if (typeof o == "string") return "\"" + o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
        if (typeof o == "object") {
            if (!o.sort) {
                for (var i in o)
                    r.push("\"" + i + "\":" + z.Obj2str(o[i]));
                if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                    r.push("toString:" + o.toString.toString());
                }
                r = "{" + r.join() + "}"
            } else {
                for (var i = 0; i < o.length; i++)
                    r.push(z.Obj2str(o[i]))
                r = "[" + r.join() + "]";
            }
            return r;
        }
        return o.toString().replace(/\"\:/g, '":""');
    },
    isInArray:function (arr,sItem){
        for (var i=0; i<arr.length; i++){
            if (arr[i]==sItem) return true;
        }
        return false;
    },
    getTime:function (){
        return (new Date().getTime()).toString();
    },
    getURLWithTime:function (sURL){
        return (sURL + (sURL.indexOf('?')==-1?'?':'&') + 'tmp='+z.getTime());
    },
    // 获取对象的实际Left
	getAbsLeft:function(obj, objStopAt){
		var iResult=obj.offsetLeft;
		while(obj = obj.offsetParent){
			if (obj == document.body) break;
			if (typeof(objStopAt)=='object'){
				if (obj == objStopAt) break;
			}else{
				if (/absolute|relative/i.test(obj.style.position)) break;
			};
			iResult += obj.offsetLeft;
		};
		return iResult;
	},
	//获取对象的实际Top
	getAbsTop:function(obj, objStopAt){
		var iResult=obj.offsetTop; 
		while(obj = obj.offsetParent){
			if (obj == document.body) break;
			if (typeof(objStopAt)=='object'){
				if (obj == objStopAt) break;
			}else{
				if (/absolute|relative/i.test(obj.style.position)) break;
			};
			iResult += obj.offsetTop;
		};
		return iResult;
	}
}

/********************************************网页公共部分加载start*********************************************************/
var vm_head = null;
var vm_toolbarVue = null;
var currentUseToolbarVue = null;
//加载头部导航条,工具栏,网站底部
$(function() {
    if($('#header').length){
        var sHTML = '';
        sHTML += '<div class="head-wrap">';
        sHTML += '  <div class="head">';
        sHTML += '      <div class="fl logo">';
        sHTML += '          <img src="/common/images/u253.svg" class="logImg">';
        sHTML += '      </div>';
        sHTML += '      <div class="fl logo">';
        sHTML += '           <p class="p1">国网国际发展有限公司</p>';
        sHTML += '           <p class="p2">State Grid International Development Co., Ltd</p>';
        sHTML += '      </div>';
        sHTML += '      <div class="fl clearfix">';
        sHTML += '          <el-menu id="TopNavMenu" :default-active="activeIndex"  class="el-menu-demo"  ';
        sHTML += '              mode="horizontal"active-text-color="#009bef">';
        sHTML += '              <el-submenu v-for="(item,index) in menuList" v-if="item.children&&item.children.length!=0"';
        sHTML += '              :index="item.id" :key="item.id">';
        sHTML += '                  <template slot="title"><span @click="selFirstNav(item)">{{item.name}}<span></template>';
        sHTML += '                  <el-menu-item v-for="subitem in item.children" :index="item.id" :key="item.id">';
        sHTML += '                  <a :href=\'"forward.do?viewPath="+item.url+"&idx="+item.id\'>{{subitem.name}}</a></el-menu-item>';
        sHTML += '              </el-submenu>';
        sHTML += '              <el-menu-item v-else :index="item.id" :key="item.id" class="menu-item-nosubs">';
        sHTML += '              <a :href=\'(/^http/.test(item.url)?"":"forward.do?viewPath=")+item.url+"&idx="+item.id\' :target="item.target">{{item.name}}</a></el-menu-item>';
        sHTML += '          </el-menu>';
        sHTML += '      </div>';
        sHTML += '      <div class="info">';
    //    sHTML += '          <span class="icon notice"><i>3</i></span>';
    //    sHTML += '          <span class="icon msg"><i>1</i></span>';
        sHTML += '          <img src="/common/images/face.svg" class="userface" id="userface">';
        sHTML += '          <el-popover ref="user" placement="bottom"  width="100" trigger="hover">';
        sHTML += '              <div style="border:none;" class="topOperation">';
        sHTML += '                  <a onclick="vm_head.setPwd()">修改密码</a><br/>';
        sHTML += '                  <a onclick="vm_head.setPhoto()">更改头像</a><br/>';
        sHTML += '                  <a class="btnExit" id="btnExit" onclick="vm_head.loginout()">退出</a>';
        sHTML += '              </div>';
        sHTML += '          </el-popover>';
        sHTML += '          <span class="username" v-popover:user id="username">超级管理员</span>';
        sHTML += '          <span class="logout"></span>';
        sHTML += '      </div>';
        sHTML += '  </div>';
        sHTML += '</div>';
        $('#header').html(sHTML);
        
         //公共弹框域
        if(z.isNotNullOrEmpty($('#divCommonDialog'))){
            $('#divCommonDialog').remove();
        }else{
            sHTML = '';
            sHTML += '<div id="divCommonDialog" style="display:none">';
            sHTML += '  <el-dialog title="窗口" :visible.sync="dialogVisible">';
            sHTML += '      <iframe src="" id="dialogFrame" frameborder="0" style="width:100%;height:300px"></iframe>';
            sHTML += '  </el-dialog>';
            sHTML += '</div>';
            $("#header").append(sHTML);
        }
        //识别当前选中菜单项
        var idx = z.getUrlParam('idx');
        vm_head = new Vue({
            el: '#header',
            data: {
                menuList:[],
                activeIndex:idx,
                dialogVisible:false
            },
            methods: {
                initMenuNav:function(){
                    var sURL = '/common/json/menu.json';
                    z.get(sURL,null,function(r){
                        if(r.code==0){
                            vm_head.menuList = r.data;
                            if(z.isNullOrEmpty(idx)){
                                //解决登录后首页选中问题
                                setTimeout(function (){
                                    $('#TopNavMenu .el-menu-item:first').addClass('is_active');
                                    $('#TopNavMenu .el-menu-item:first').css('color','#409eff');
                                    $('#TopNavMenu .el-menu-item:first').css('border-bottom-color','#409eff');
                                },100);
                            }
                        }
                    })
                },
                selFirstNav:function(jo){
                    var sURL = 'index.html';
                    if(z.isNotNullOrEmpty(jo))sURL = 'forward.do?viewPath='+jo.url+'&idx='+jo.id;
                    window.location.href = sURL;
                },
                setPwd:function(){
                    $('#divCommonDialog').css('display','');
                    this.dialogVisible = true;
                    setTimeout(function(){$('#dialogFrame').attr('src','forward.do?viewPath=common/SysUserPassword.html');},100)
                },
                findURLByMenu:function(id){
                
                },
                setPhoto:function(){
                    $('#divCommonDialog').css('display','');
                    this.dialogVisible = true;
                    setTimeout(function(){$('#dialogFrame').attr('src','forward.do?viewPath=common/SysUserAvatar.html');},100)
                },
                loginout:function() {
                    z.confirm('此操作将退出系统, 是否继续?',function(action, instance){
                        if(action=='confirm'){
                            z.ajax({
                                url : "/baseRest/loginout",
                                async : false,
                                success : function(res, textStatus) {
                                    window.location.href = "/login.do";
                                }
                            });
                        }
                    })
                }
            },
            mounted: function() {
                this.initMenuNav();
            }
        })
    }
    
    if($('#toolbar').length>0){
        var sHTML = '';
        sHTML += '<div class="toolbar-wrap clearfix">';
        sHTML += '    <div class="toolbar-tit fl" id="toolbarTit"></div>';
        sHTML += '    <div class="toolbar-tabs fl" id="toolbarTab">';
        sHTML += '        <el-radio-group v-model="defNav"  @change="showContentByCategory">';
        sHTML += '            <el-radio-button v-for="(item,index) in category" :label="index">';
        sHTML += '                {{item.name}}';
        sHTML += '            </el-radio-button>';
        sHTML += '        </el-radio-group>';
        sHTML += '    </div>';
        sHTML += '    <div class="toolbar-search fl" id="toolbarSrh">';
        sHTML += '        <el-autocomplete class="inline-input" v-model="keyword" :fetch-suggestions="fnAutoSrh" ';
        sHTML += '          placeholder="请输入关键字" :trigger-on-focus="false" @select="fnFullTextSrh">' +
            '<el-button slot="append" icon="el-icon-search" @click="searchNews"></el-button></el-autocomplete>';
        sHTML += '        <span class="hotkeyword-tit">搜索热词:</span>';
        sHTML += '        <ul class="hotkeyword-list">';
        sHTML += '            <li v-for="item in hotwords"><a @click="keyword=item.name;fnFullTextSrh()">{{item.name}}</a></li>';
        sHTML += '        </ul>';
        sHTML += '    </div>';
        sHTML += '</div>';
        $('#toolbar').html(sHTML);
        vm_toolbarVue = new Vue({
            el: '#toolbar',
            data: {
                defNav:'0',
                category:[{}],
                keyword:'',
                searchNewsResult:null,
                hotwords:[{name:'美元汇率',id:'1'},{name:'阿根廷大选',id:'2'},{name:'长江基建',id:'3'}]
            },
            methods: {
                initCategory:function(){
                    if(z.isNullOrEmpty(idx))return;
                    var data = vm_head.$data.menuList;
                    var result = z.msService("user","sysDictionaryApi/getCodeList",{"code":"024"});
                    if (result.code === 0){
                        result = result.data;
                    }
                    for (var i = 0; i<result.length; i++){
                        result[i].name = result[i].label;
                    }
                    for(var i=0;i<data.length;i++){
                        if(data[i].id==idx){
                            data[i].children = result;
                            this.category = data[i].children;
                            $('#toolbarTit').html(data[i].name);
                        }
                    }
                },
                searchNews:function () {
                    if (null !== currentUseToolbarVue) {
                        currentUseToolbarVue.tableSearchModel.keywords = this.keyword;
                        currentUseToolbarVue.searchClick();
                    }
                },
                showContentByCategory:function(idx){
                    if (null !== currentUseToolbarVue) {
                        currentUseToolbarVue.tableSearchModel.type = this.category[idx].key;
                        currentUseToolbarVue.currentNewsTypeName = this.category[idx].name;
                    }
                },
                fnAutoSrh:function(keyword,cb){
                    cb([{value:'智能匹配结果'},{value:'智能匹配结果'},{value:'智能匹配结果'}]);
                },
                fnFullTextSrh:function(){
                    alert(this.keyword);
                }
            },
            mounted: function() {
                setTimeout(function(){vm_toolbarVue.initCategory();},100);
            }
        })

    }
    
    if($('#footer').length>0){
        var sHTML = 'Copyright ©2017 情报信息系统&emsp;Powered By 国网国际发展有限公司&emsp;Version 1.0.0';
        $('#footer').html(sHTML);
    }
})

/********************************************网页公共部分加载end*********************************************************/
 

