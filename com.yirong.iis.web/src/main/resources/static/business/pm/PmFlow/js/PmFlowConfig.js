//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#config',
	data : {
	},
	methods : {
		//确定按钮
		configSubmit : function(){
			//先测试流程
			var testTesult = this.testSubmit(false);
			if(testTesult){
				//再保存流程
				var saveResult = this.configSave(false);
				if(saveResult){//两个操作均成功提示错误无需提示，各自提示）
					//请求后台保存测试成功及保存明细
					var ids = mainVue.tableGetCheckIds();
					var result = ak.msService("pm","PmFlowApi/update",{ id : ids[0], testIsSuccess : 1});
					if(result.code == 0){
						ak.info("流程保存及测试成功");
						//关闭窗口
	                   	configVue.dialogClose();
	                    //刷新表格
	                    mainVue.tableRefresh();
					}
				}
			}
		},		
		//保存按钮
		configSave : function(isPrompt){
			//定义保存对象
			var jsonObj = {
                divs: [],//元素界面元素
                conns: [],//连接线信息
                fields : {},//元素字段
            	lineObjects : {},//元素连线
                datas : {},//元素数据
                sorts : [],//元素排序,
                input : {},//输入对象
                output : {} //输出对象
            };
            /**处理div元素**/
			var nodes = $('#flow .node');
			for (var i = 0; i < nodes.length; i++) {
                var node = $(nodes.eq(i));
                var nodeType = node.data('type');
                //处理元素信息
                jsonObj.divs.push({
                	id : node.attr("id"),
					nodeType : nodeType,
					code : node.attr("code"),
					oid : node.attr("oid"),
					left : node.css("left"),
					top : node.css("top"),
					name : node.html(),
					esindex : node.attr("esindex"),
					estype : node.attr("estype")
                });
                //输入信息
                if(nodeType == "input"){
                	jsonObj.input = {
                		id : node.attr("id"),
                		oid : node.attr("oid"),
                		esindex : node.attr("esindex"),
						estype : node.attr("estype")
                	};
                }
                //输出信息
                if(nodeType == "output"){
                	jsonObj.output = {
                		id : node.attr("id"),
                		oid : node.attr("oid")
                	};
                }
			}
			/**处理连接线**/
			$.each(instance.getAllConnections(), function (idx, conn) {
                var source = conn.endpoints[0].anchor;
                var target = conn.endpoints[1].anchor;
                jsonObj.conns.push({
                    sourceUUID: source.elementId + source.type,
                    targetUUID: target.elementId + target.type
                });
            });
            /**处理元素字段信息*/
            jsonObj.fields = objectField;
            /**处理元素连线信息*/
            jsonObj.lineObjects = objectLineObject;
            /**处理元素数据信息*/
            jsonObj.datas = objectData;
            /**处理元素排序信息*/
            jsonObj.sorts = objectSort;
            //请求后台保存
			var ids = mainVue.tableGetCheckIds();
            var result = ak.msService("pm","PmFlowApi/update",{ id : ids[0], visualJson : JSON.stringify(jsonObj) });
        	if(result.code == 0){//操作成功
            	//提示
        		if(isPrompt){
        			ak.info(result.msg);
        		}
        		return true;
            }else{
            	return false;
            }
		},
		//取消按钮
		configCancel : function(){
			//关闭窗口
			configVue.dialogClose();
		},
		//测试流程按钮
		testSubmit : function(isPrompt){
			var nodes = $('#flow .node');
			//先判断是否只有一个输入元素
			var inputs = [];
			for (var i = 0; i < nodes.length; i++) {
                var node = $(nodes.eq(i));
                var nodeType = node.data('type');
                if(nodeType == "input"){//输入框
                	inputs.push(node.attr("id"));
                }
			}
			if(inputs.length != 1){
				ak.info("流程中必须有且仅有一个输入元素");
				return;
			}else{
				//判断所有元素是否均有连线
				var lineObj = null;
				var isNotCheckName = null;
				for (var i = 0; i < nodes.length; i++) {
	                var node = $(nodes.eq(i));
	                var nodeType = node.data('type');
	                var id = node.attr("id");
	                var name = node.html();
	                /**验证连线及数据情况**/
	                if(nodeType == "input"){//输入框
	                	//获取本元素输出线
	                	lineObj = instance.select({ source: id });
	                	if (lineObj.length == 0) {
	                        isNotCheckName = name;
	                        break;
	                    }
	                }else if(nodeType == "rule"){//规则
	                	/**判断连线情况*/
	                	//获取本元素输出线
	                	lineObj = instance.select({ source: id });
	                	//获取本元素输入线
	                	var targetLineObj = instance.select({ target: id });
	                	if (lineObj.length == 0 || targetLineObj.length == 0) {
	                        isNotCheckName = name;
	                        break;
	                    }
	                    /**验证数据情况**/
	                    var data = objectData[id];
	                    if(null == data || data.length == 0){
	                    	ak.info('元素：' + name + '参数未配置');
	                		return false;
	                    }
	                }else{//输出框
	                	//获取本元素输入线
	                	lineObj = instance.select({ target: id });
	                	if (lineObj.length == 0) {
	                        isNotCheckName = name;
	                        break;
	                    }
	                }
	                
				}
				if(null != isNotCheckName){//未验证通过
					ak.info('元素：' + isNotCheckName + '未连接');
	                return false;
				}else{//验证通过，需对元素进行排序
					var id = inputs[0];
					objectSort = [];//初始化为空
					//循环找出
					while(undefined != objectLineObject[id]){
						objectSort.push({
							id : id,
							oid : $("#" + id).attr("oid")
						});
						id = objectLineObject[id].outId;
					};
					if(isPrompt){
						ak.info("流程配置正确！");
					}
					return true;
				}
			}
		},
		//统一渲染事件
		treeCommonRender : function(createElement,b,typeStr){
			var className = "";
			var data = b.node.data;
			var ab = data.atttibute;
			if(ab.drag == 1){//允许拖拽
				className = "el-tree-node_label";//自定义名称（用于识别允许拖拽的元素）
			}
            // 渲染树节点内容区
            return createElement('span', {
                class: className,
                attrs: {
                    'data-type': typeStr,	
                    "code" : ab.code,
                    "oid" : data.id,
                    "esindex" : ab.esindex,
                    "estype" : ab.estype
                }
            }, b.node.data.name);
		}
	},
	//页面初始化事件
    mounted: function () {
    	/**由于拖拽插件执行顺序问题，必须页面初始化的时候加载所有要素信息（不允许操作时加载）*/
    	//初始化输入树
    	this.treeInit("ct","VeCtObjectApi/tree");
    	this.treeSearch(null);
    	//初始化规则树
    	this.ruleTreeSearch("pm","PmRuleApi/tree");
    }
});

//输入树属性
var treeAttr = getTreeMergeAttr({
	methods : {
		//输入树界面元素处理
		treeRender: function (createElement, b) {
			return this.treeCommonRender(createElement,b,"input");//默认为输入
        }
	}
});

//规则树属性
var ruleTreeAttr = {
	data: {
		//数据
		ruleTreeData: [],
		//属性
		ruleTreeProps: {
			children: 'children',
		    label: 'name'
		}
	},
	//方法
	methods: {
		//查询树数据
		ruleTreeSearch : function(serviceName,pathName){
			//请求后台
			var result = ak.msService(serviceName,pathName,null);
			//处理数据
			this.ruleTreeData = ak.ArrayToTreeData(result.data);
		},
		//输入（采集输入）树界面元素处理
		ruleTreeRender: function (createElement, b) {
			return this.treeCommonRender(createElement,b,"rule");//默认为规则
        }
	}
};

//输出树属性
var outputTreeAttr = {
	data: {
		//数据（默认输出，直接前台编辑数据）
		outputTreeData: [{
			id : "",
			name : "默认输出",
			pid : "",
			atttibute : {
				drag : 1
			}
		}],
		//属性
		outputTreeProps: {
			children: 'children',
		    label: 'name'
		}
	},
	//方法
	methods: {
		//查询树数据
		outputTreeSearch : function(serviceName,pathName){
			//请求后台
			var result = ak.msService(serviceName,pathName,null);
			//处理数据
			this.outputTreeData = ak.ArrayToTreeData(result.data);
		},
		//输入（采集输入）树界面元素处理
		outputTreeRender: function (createElement, b) {
			return this.treeCommonRender(createElement,b,"output");//默认为输出
        }
	}
};

//生成vue对象
var configVue = ak.getMergeVue(dialogAttr,treeAttr,ruleTreeAttr,outputTreeAttr);
/*********************以下为右键代码**************/
//右键菜单属性
var rightClickMenu = [[{
	text: '删除',
	func: function () {
		instance.remove($(this).attr("id"));
	}
}]];

/**********************以下为拖拽区代码**********/
/****拖拽区变量***/
//选中对象名称
var checkObjectName = null;
//选对对象ID（对象原始ID）
var checkObjectId = null;
//选中ID
var checkId = null;
//对象字段信息
var objectField = {};
//元素连接对应关系
var objectLineObject = {};
//元素数据
var objectData = {};
//元素顺序
var objectSort = [];
//输入对象
var objectInput = {};
//输出对象
var objectOutput = {};
/********拖拽样式*******/
// 连接线样式
var connectorPaintStyle = {
    strokeWidth: 2,
    stroke: "#61B7CF",
    joinstyle: "round",
    outlineStroke: "white",
    outlineWidth: 2
};

// 连接线移入样式
var connectorHoverStyle = {
    strokeWidth: 3,
    stroke: "#216477",
    outlineWidth: 5,
    outlineStroke: "white"
};

// 节点移入样式
var endpointHoverStyle = {
    fill: "#216477",
    stroke: "#216477"
};

// 源节点
var sourceEndpoint = {
    endpoint: "Dot",                        // 节点的形状
    paintStyle: {                           // 节点的颜色样式
        stroke: "#7AB02C",
        fill: "transparent",
        radius: 7,
        strokeWidth: 1
    },
    hoverPaintStyle: endpointHoverStyle,
    isSource: true,                         // 是否可以作为起点连接其他点
    isTarget: false,                        // 是否可以被连接
    connector: ["Flowchart", {
        stub: [40, 60],
        gap: 10,
        cornerRadius: 5,
        alwaysRespectStubs: true
    }],
    connectorStyle: connectorPaintStyle,
    connectorHoverStyle: connectorHoverStyle,
    dragOptions: {},
    overlays: [
        ["Label", {                         // 节点名称
            location: [-1, 1.5],            // 节点位置
            label: "源点",                  // 节点内容
            cssClass: "endpointSourceLabel",
            visible: false				//不显示连接线点名称
        }]
    ],
    scope: 'a'                              // 设置同一类型scope的两个节点才能互相连接
};

// 目标节点
var targetEndpoint = {
    endpoint: "Dot",
    paintStyle: {
        fill: "#7AB02C",
        radius: 7
    },
    hoverPaintStyle: endpointHoverStyle,
    maxConnections: 1,                      // 设置连接点最多可以连接几条线
    dropOptions: { hoverClass: "hover", activeClass: "active" },
    isSource: false,
    isTarget: true,
    overlays: [
        ["Label", {
            location: [-2, -1],
            label: "目标点",
            cssClass: "endpointTargetLabel",
            visible: false	//不显示连接线点名称
        }]
    ],
    scope: 'a'
};

// 连接线样式
var basicType = {
    connector: "StateMachine",
    paintStyle: { stroke: "red", strokeWidth: 4 },
    hoverPaintStyle: { stroke: "blue" },
    overlays: [
        "Arrow"
    ]
};

/*****拖拽区事件*****/
// 创建连接点
var addEndpoints = function (toId, sourceAnchors, targetAnchors) {
    for (var i = 0; i < sourceAnchors.length; i++) {
        var sourceUUID = toId + sourceAnchors[i];
        instance.addEndpoint(toId, sourceEndpoint, {
            anchor: sourceAnchors[i],
            uuid: sourceUUID
        });
    }
    for (var j = 0; j < targetAnchors.length; j++) {
        var targetUUID = toId + targetAnchors[j];
        instance.addEndpoint(toId, targetEndpoint, {
            anchor: targetAnchors[j],
            uuid: targetUUID
        });
    }
};

//构建元素
var buildHtml = function(data){
	var id =data.id;
	//拼装元素信息	
    var html = "<div id='";
    html += id;
    html += "' data-type='";
    html += data.nodeType;
    html += "' code='";
    html += data.code;
    html += "' oid='";
    html += data.oid;
    html += "' esindex='";
    html += data.esindex;
    html += "' estype='";
    html += data.estype;
    html += "' class='node ";
    html += data.nodeType;
    html += "' style='position: absolute;left:";
    html += data.left;
    html += ";top:";
    html += data.top;
    html += "'>";
    html += data.name;
    html += "</div>";
    //创建元素
    $('#flow').append(html);
	/**绑定元素事件**/
    //绑定双击事件
    $("#" + id).bind("dblclick",divDbClick);
    //绑定右击事件
    $("#" + id).smartMenu(rightClickMenu);
    /**处理变量信息信息**/
    //处理字段
    var datas = [];
    if(data.nodeType == "input"){//输入需初始化字段信息
    	var result = ak.msService("ct","CtEsFieldApi/listByObjectId",{ctObjectId : data.oid});
    	if(result.code == 0){//请求成功
    		//循环处理数据
    		$.each(result.data,function(i,n){
    			var field = {
    				code : n[0],
    				name : n[1],
    				typeName : n[2],
    				type : n[3],
    				nodeName:n[4]
    			};
    			datas.push(field);
    		});
    	}
    };
    //不管任何类型均需初始化字段参数
	objectField[id] = {
		inField : [],//输入字段
		outField : datas//输出字段
	};
	//不管任何类型均需初始化连接线参数
	objectLineObject[id] = {
		inId : null,//输入对象ID
		outId : null//输出对象ID
	};
	 /**创建节点类型创建不同连接点**/
    var sourceAnchors = [];
    var targetAnchors = [];
    switch (data.nodeType) {
        case 'input':
            sourceAnchors = ['BottomCenter'];
            targetAnchors = [];
            break;
        case 'rule':
            sourceAnchors = ['BottomCenter'];
            targetAnchors = ['TopCenter'];
            break;
        case 'output':
//            sourceAnchors = ['BottomCenter'];//暂不允许多输出
        	sourceAnchors = []
            targetAnchors = ['TopCenter'];
            break;
        default:
    }
    //创建连接点
    addEndpoints(id, sourceAnchors, targetAnchors);
    instance.draggable(jsPlumb.getSelector('#' + id), { grid: [20, 20] });
}

//拖拽div双击事件
var divDbClick = function(){
	/**获取基础属性**/
	var code = $(this).attr("code");//编码
	var type = $(this).attr("data-type");//类型
	var oid = $(this).attr("oid");//对象ID
	var name = $(this).text();//文本
	var id = $(this).attr("id");//id
	/**初始化字段信息**/
	//判断是否初始化
	if(!objectField[id]){
		objectField[id] = {
	    	inField : [],
	    	outField : []
	    };
	}
    var field = objectField[id];
    //每次打开清空本元素输入字段
    field.inField = [];
    var objLine = objectLineObject[id];
    if(objLine && objLine.inId){//存在输入线
    	//将源元素的输出字段信息复制给本元素的输入字段信息
	    $.each(objectField[objLine.inId].outField,function(i,n){
	    	field.inField[i] = n;
	    });
    }
    /**处理打开页面信息**/
	//全局基础
	checkObjectName = name;
	checkId = id;
	if(type == "input"){//输入
		checkObjectId = oid;
		ak.dialog("inputDb","business/pm/PmFlow/PmFlowInput.html");
	}else if(type == "rule"){//规则
		switch (code) {
			case '101'://数据过滤
				ak.dialog("rule101","business/pm/PmFlow/PmFlowRule101.html",addOutputObjectFieldByRule);
				break;
			case '102'://数据去重
				ak.dialog("rule102","business/pm/PmFlow/PmFlowRule102.html",addOutputObjectFieldByRule);
				break;
			case '200'://属性过滤
				ak.dialog("rule200","business/pm/PmFlow/PmFlowRule200.html",addOutputObjectFieldByRule);
				break;
			case '201'://数据转换
				ak.dialog("rule201","business/pm/PmFlow/PmFlowRule201.html",addOutputObjectFieldByRule);
				break;
			case '300'://分组统计
				ak.dialog("rule300","business/pm/PmFlow/PmFlowRule300.html",addOutputObjectFieldByRule);
				break;
			case '900'://招投标信息提取
				ak.dialog("rule900","business/pm/PmFlow/PmFlowRule900.html",addOutputObjectFieldByRule);
				break;
			case '901'://关注分类
				ak.dialog("rule901","business/pm/PmFlow/PmFlowRule901.html",addOutputObjectFieldByRule);
				break;
			case '202'://时间格式转换
				ak.dialog("rule202","business/pm/PmFlow/PmFlowRule202.html",addOutputObjectFieldByRule);
				break;
			case '203'://价格格式转换
				ak.dialog("rule203","business/pm/PmFlow/PmFlowRule203.html",addOutputObjectFieldByRule);
				break;
			case '204'://天数转换
				ak.dialog("rule204","business/pm/PmFlow/PmFlowRule204.html",addOutputObjectFieldByRule);
				break;
		}
	}else{//输出
		ak.dialog("output","business/pm/PmFlow/PmFlowOutput.html");
	}
}
//删除传入元素的相关数据 
var deleteThisObjectAfterObj = function(id){
	//删除字段信息
//	deleteThisObjectAfterField(id);
	//删除数据信息
	deleteThisObjectAfterData(id);
}

//删除传入的元素往后的所有元素字段信息
var deleteThisObjectAfterField = function(id){
	//循环找出
	while(objectLineObject[id] && null != objectLineObject[id].outId){
		//重置
		objectField[id] = {
		    inField : [],
			outField : []
		};
		id = objectLineObject[id].outId;
	};
};

//删除传入的元素往后的所有元素数据信息
var deleteThisObjectAfterData = function(id){
	//循环找出
	while(objectLineObject[id] && objectData[id]){
		//重置数据
		objectData[id] = null;
		id = objectLineObject[id].outId;
	};
};

//根据当前ID添加输出对象字段信息
var addOutputObjectFieldByRule = function(id){
	if(undefined == id){
		id = checkId;
	}
	//获取下一个元素信息
	var outId = objectLineObject[id].outId;
	if(null != outId){//有下一个元素
		if($("#" + outId).data("type") == "output"){//下一个为输出元素，需要拷贝字段信息
			//重置
			objectField[outId] = {
				outField : [],
				inField : []
			}
			//获取本元素输出字段对象
			var outField = objectField[id].outField;
			var nextField = objectField[outId];
			$.each(outField,function(i,n){
				nextField.inField.push(n);//输出输入赋值
				nextField.outField.push(n);//输出输出赋值
			});
		}
	}
	//删除本元素后所有元素的相关信息（需重新配置）
	deleteThisObjectAfterObj(outId);
}

/**初始化驱动**/
//必须使用$在元素加载之后再进行初始化
$(function(){
	//初始化
	function initFlow(){
		//流程插件初始化
		jsPlumb.ready(function () {
	        // 设置可拖拽的节点
	        $('#left_el .el-tree-node_label').parent().draggable({
	            helper: 'clone'
	        });
	
	    	$('#flow').droppable({
	    		//拖拽鼠标放开后事件
	        	drop: function (event, ui) {
	                /**创建节点元素**/
	        		//获取节点属性
	                var _this = $(this);
	                var left = ui.offset.left - _this.offset().left;
	                var top = ui.offset.top - _this.offset().top;
	                var id = 'node_' + new Date().getTime();
	                var el = ui.draggable.find('.el-tree-node_label');
	                var nodeType = el.data('type');
	                var code = el.attr("code");
	                var oid = el.attr("oid");
	                var esindex = el.attr("esindex");
	                var estype = el.attr("estype");
	                var name = el.html();
					//构建元素
					buildHtml({
						id : id,
						nodeType : nodeType,
						code : code,
						oid : oid,
						left : left + "px",
						top : top + "px",
						name : name,
						esindex : esindex,
						estype : estype
					});
	            }
	        });
	
			//绑定删除事件
	        $('#flow').on('click', '.el-icon-delete', function () {
	            var id = $(this).data('id');
	            instance.removeAllEndpoints(id);
	            $('#' + id).remove();
	        });
	
	        instance = window.jsp = jsPlumb.getInstance({
                DragOptions: { cursor: 'pointer', zIndex: 2000 },
                ConnectionOverlays: [
                    ["Arrow", {
                        location: 1,
                        visible: true,
                        width: 11,
                        length: 11,
                        id: "ARROW",
                        events: {
                            click: function () {
                                // 连接线箭头点击事件
                            }
                        }
                    }],
                    ["Label", {                 // 连接线标签
                        location: 0.5,
                        id: "label",
                        cssClass: "aLabel el-icon-circle-cross",
                        events: {
                        	//删除连接线事件
                            click: function (params, e) {
                            	/**处理元素与线的对应关系数据**/
					            //获取元素
					            var conn = params.component;
					            var sourceId = conn.sourceId;
					            var targetId = conn.targetId;
					            //处理源元素（源元素的输出线应该为空）
					            objectLineObject[sourceId].outId = null;
					            //处理目标元素（目标元素的输出线应该为空）
					            objectLineObject[targetId].inId = null;
					            //处理目标及之后元素信息
					            deleteThisObjectAfterObj(targetId);
					            /**删除连接线**/
					            instance.deleteConnection(conn);
                            }
                        }
                    }]
                ],
                Container: "canvas"
            });
	
			//连接线注册        
	        instance.registerConnectionType("basic", basicType);
	
	        // 创建连接线事件
	        instance.bind("connection", function (connInfo, originalEvent) {
	            /**处理元素与线的对应关系数据**/
	            //获取元素
	            var conn = connInfo.connection;
	            var sourceId = conn.sourceId;
	            var targetId = conn.targetId;
	            
	            //处理源元素（源元素的输出线应该为目标元素）
	            objectLineObject[sourceId].outId = targetId;
	            //处理目标元素（目标元素的输出线应该为源元素）
	            objectLineObject[targetId].inId = sourceId;
	            //如果下一个元素是输出元素，需处理字段信息
	            if($("#" + targetId).data("type") == "output"){//下一个为输出元素，需要拷贝字段信息
	            	addOutputObjectFieldByRule(sourceId);
	            }
	        });
	
	        //初始化事件
	        instance.batch(function () {
	        	//请求后台获取数据
				var ids = mainVue.tableGetCheckIds();
	            var result = ak.msService("pm","PmFlowApi/get",{ id : ids[0]});
	        	if(result.code == 0){//操作成功
	            	var jsonObj = result.data.visualJson;
	            	if(jsonObj){//有保存过流程
	            		// 批量添加元素和节点
		                for (var i = 0; i < jsonObj.divs.length; i++) {
		                    buildHtml(jsonObj.divs[i]);
		                }
		                // 添加连接线
		                for (var i = 0; i < jsonObj.conns.length; i++) {
		                    var item = jsonObj.conns[i];
		                    instance.connect({ uuids: [item.sourceUUID, item.targetUUID], editable: true });
		                }
		                /**处理数据信息*/
		                //字段信息
			            objectField = jsonObj.fields;
			            //连线信息
			            objectLineObject = jsonObj.lineObjects;
			            //数据信息
			            objectData = jsonObj.datas;
			            //处理排序信息
			            objectSort = jsonObj.sorts;
			            //输入信息
			            objectInput = jsonObj.input;
			            //输出信息
			            objectOutput = jsonObj.output;
	            	}
	            }
	        });
	
	        jsPlumb.fire("jsPlumbDemoLoaded", instance);
		});
	};
	
	initFlow();
});


