/*******awaken核心（用于aw.**开头所有方法编写）*******/
//本类自己使用，不提供给外界

//定义ak为方法前缀
window.ak = {
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
			notice : false
		}, opts);
		// 遮盖
		if (opts.notice) {
			ak.ld();
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
					setTimeout(function(){ak.lc();},100);
				}
			},
			error : function(errors, textStatus) {
				ak.error(textStatus);
				// 解除遮盖
				if (opts.notice) {
					setTimeout(function(){ak.lc();},100);
				}
			}
		});
	},
	// 微服务请求
	msService : function msService(serviceName, methodName, param) {
		debugger;
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
		this.ajax({
			url : url,
			async : false,
			data : data,
			type : "post",
			dataType : 'json',
			success : function(data, textStatus) {
				//判断系统级
				if(data.code == 0){//成功
					//判断业务级
					var bdata = data.data;
					if(bdata.code!= 0){//业务异常
						ak.warning(bdata.msg);
					}
					//不管失败与否均要返回数据
					result = bdata;
				}else if(data.code == 2){//超时
					awakenCommonVue.$alert(data.msg, '超时提示', {
			            confirmButtonText: '确定',
			            type: 'warning',
			            callback: function (action, instance) {
			            	top.window.location.href = "/login.do";
			            }
			        });
				}else{//异常
					ak.error(data.msg);
				}
			}
		});
		return result;
	},
	msServiceFile : function msServiceFile(methodName, fileId) {
        var result = null;
        var data = {
        	"fileId":fileId
        }

        var url = '/file/'+methodName;
        this.ajax({
            url : url,
            async : false,
            data : data,
            type : "post",
            dataType : 'json',
            async : true,
            cache : false,
            contentType: false,
            processData: false,
            success : function(data, textStatus) {
                //调用微服务失败。（直接提示）
                if(0 !== data.code){
                    z.tips(data.msg);
                }
                //不管失败与否均要返回数据
                result = data;

            }
        });
        return result;
    },
	//加载中遮盖显示
    ld: function () {
        if(!top.awakenCommonVue.isLoad){
        	top.awakenCommonVue.isLoad = true;
        	top.awakenCommonVue.loadingObj = Vue.prototype.$loading({ fullscreen: true });
        }
    },
    //加载中遮盖关闭
    lc: function () {
    	if(top.awakenCommonVue.isLoad){
    		top.awakenCommonVue.loadingObj.close();
    		top.awakenCommonVue.isLoad = false;
    	}
    },
    //成功提示（2秒后自动关闭）
    success: function (msg) {
        Vue.prototype.$message.success({
            message: msg,
            showClose: true,
            duration: 2000
        });
    },
    //警告提示（3秒后自动关闭）
    warning: function (msg) {
        Vue.prototype.$message.warning({
            message: msg,
            showClose: true,
            duration: 3000
        });
    },
    //提示（2秒后自动关闭）
    info: function (msg) {
        Vue.prototype.$message.info({
            message: msg,
            showClose: true,
            duration: 2000
        });
    },
    //错误提示（3秒后自动关闭）
    error: function (msg) {
        Vue.prototype.$message.error({
            message: msg,
            showClose: true,
            duration: 3000
        });
    },
    //弹出确认框
    confirm : function(msg,callback){
    	commonVue.$confirm(msg, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            callback: function (action, instance) {
                if (action == 'confirm') {//确定按钮
                    callback(instance);
                }
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
            if (ak.isNotNullOrEmpty(tmpMap[data.pid]) && data.id != data.pid) {//其他节点
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
    //获取合并属性后的vue对象
    getMergeVue : function(attr1,attr2,attr3,attr4,attr5){
    	var attr = $.extend(true,attr1,attr2,attr3,attr4,attr5);
    	setTimeout(function (){
			awakenCommonVue.initMenu();
		},1);
    	return new Vue(attr);
    },
    // 加载html内容
	getHtml : function(url) {
		var result = null;
		ak.ajax({
			url : url,
			async : false,
			type : "GET",
			dataType : "html",
			success : function(data, textStatus) {
				result = data;
			}
		});
		return result;
	},
	//弹出窗口（居中）
	dialog : function(id,url,closeFn){
		url = "forward.do?viewPath=" + url;
		//获取元素
		var html = ak.getHtml(url);
		//判断该窗口之前是否打开过
		var idObj = $("#" + id);
		if(ak.isNotNullOrEmpty(idObj)){//已经存在该对象，需删除
			idObj.remove();
		}
		$("body").append(html);
		if(closeFn){//有回调函数需要设置
			if(!awakenCommonVue.dialogCloseFn){//不存在需创建
				awakenCommonVue.dialogCloseFn = [];
			}
			awakenCommonVue.dialogCloseFn[id] = closeFn;
		}
	},
	/**以下信息为公用工具类，可单独提取使用，本框架为awaken，所以合并至aw使用**/
	//地址路径参数获取
    url: function (url) {
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
    //时间类型方法处理
    date: {
    	//格式化
        format: function (format, date) {
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
        //时间比较
        difference: function (d1, d2) {
            var date1, date2, date3;
            if (typeof (d1) == 'string') {
                date1 = new Date(d1);
            } else {
                date1 = d1;
            }
            if (typeof (d2) == 'string') {
                date2 = new Date(d2);
            } else {
                date2 = d2;
            }

            date3 = date2.getTime() - date1.getTime()  //时间差的毫秒数

            if (date3 <= 0) {
                return '已过期';
            }

            //计算出相差天数
            var days = Math.floor(date3 / (24 * 3600 * 1000))

            //计算出小时数
            var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000))
            //计算相差分钟数
            var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
            var minutes = Math.floor(leave2 / (60 * 1000))
            //计算相差秒数
            var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
            var seconds = Math.round(leave3 / 1000)

            return days + '天' + hours + '小时' + minutes + '分钟' + seconds + '秒';
        },
        //获取当前时间，格式YYYY-MM-DD
        getNowFormatDate : function() {
            var date = new Date();
            var seperator1 = "-";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate;
            return currentdate;
        },
        //获取 日期格式YYYYMMDD （昨天：AddDayCount=-1，今天AddDayCount=0，明天AddDayCount=1）
        getDateStr : function (AddDayCount) {  
        	 var dd = new Date();
        	 dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
        	 return this.format("yyyyMMdd",dd);
        } ,
        //获得本周的开始日期 
        getWeekStartDate :  function() { 
        	/**
        	* 获取本周、本季度、本月、上月的开始日期、结束日期
        	*/
        	var now = new Date(); //当前日期 
        	var nowDayOfWeek = now.getDay(); //今天本周的第几天 
        	var nowDay = now.getDate(); //当前日 
        	var nowMonth = now.getMonth(); //当前月 
        	var nowYear = now.getYear(); //当前年 
        	nowYear += (nowYear < 2000) ? 1900 : 0; //
        	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek); 
        	return this.format("yyyyMMdd",weekStartDate);
        } ,

        //获得本周的结束日期 
        getWeekEndDate : function () { 
        	/**
        	* 获取本周、本季度、本月、上月的开始日期、结束日期
        	*/
        	var now = new Date(); //当前日期 
        	var nowDayOfWeek = now.getDay(); //今天本周的第几天 
        	var nowDay = now.getDate(); //当前日 
        	var nowMonth = now.getMonth(); //当前月 
        	var nowYear = now.getYear(); //当前年 
        	nowYear += (nowYear < 2000) ? 1900 : 0; //
        	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek)); 
        	return this.format("yyyyMMdd",weekEndDate);
        } 
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
    //两个对象属性复制（只支持一级）
    copyObjValue : function(sourceObj,targetObj){
    	$.each(sourceObj,function(i,n){
    		for (name in targetObj) {   
	            if(i == name){//如果名称相同，则将旧属性复制给新属性
    				targetObj[name] = n;
    				return;
    			}
	        }   
    	});
    	return targetObj;
    },
    /////将JSON转为字符串
    Obj2str : function (o) {
       if (o == undefined) {
           return "";
       }
       var r = [];
       if (typeof o == "string") return "\"" + o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
       if (typeof o == "object") {
           if (!o.sort) {
               for (var i in o)
                   r.push("\"" + i + "\":" + ak.Obj2str(o[i]));
               if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
                   r.push("toString:" + o.toString.toString());
               }
               r = "{" + r.join() + "}"
           } else {
               for (var i = 0; i < o.length; i++)
                   r.push(ak.Obj2str(o[i]))
               r = "[" + r.join() + "]";
           }
           return r;
       }
       return o.toString().replace(/\"\:/g, '":""');
   },
   
   getUrlParam : function (name) {
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
       var r = window.location.search.substr(1).match(reg); //匹配目标参数
       if (r != null) return unescape(r[2]); return null; //返回参数值
     }
}

