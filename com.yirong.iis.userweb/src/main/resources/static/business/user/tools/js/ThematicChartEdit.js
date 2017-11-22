var vm_chart = new Vue({
    el: '#chartMain',
    data: {   
        curStep:0,//当前步骤
        chartList:[],//图表列表
        dataBaseSourceDef:'',//数据来源为数据库时默认的数据库
        dataBaseSourceOption:[{value:'ma',label:'挖掘子系统'},{value:'pm',label:'采集子系统'}],
        fieldTreeProps: {
            children: 'children',
            label: 'fieldName',
            id:'fieldCode'
        },
        treeData:[],
        treeDataSet: [
            {
                "fieldCode": '297e49065d0d6997015d0d83eb290000',
                "fieldName": "堆叠折线图",
                "type": "root",
                "children": [
                    {
                        "fieldCode": 11,
                        "fieldName": "字符",
                        "type": "char",
                        "children":[
                            {
                                "fieldCode": 111,
                                "fieldName":"姓名",
                                "field":"name",
                                "type":"char"
                            }
                        ]
                    },
                    {
                        "fieldCode": 12,
                        "fieldName": "数值",
                        "type": "value",
                        "children":[
                            {
                                "fieldCode": 121,
                                "fieldName":"序号",
                                "field":"no",
                                "type":"value"
                            },
                            {
                                "fieldCode":122,
                                "fieldName":"成绩",
                                "field":"score",
                                "type":"value"
                            }
                        ]
                    }
                ]
            },
            {
                "fieldCode": '297e49065d1ca822015d1cde12aa0000',
                "fieldName": "双向柱图",
                "type": "root",
                "children": [
                    {
                        "fieldCode": 11,
                        "fieldName": "字符",
                        "type": "char",
                        "children":[
                            {
                                "fieldCode": 111,
                                "fieldName":"时间",
                                "field":"date",
                                "type":"char"
                            }
                        ]
                    },
                    {
                        "fieldCode": 12,
                        "fieldName": "数值",
                        "type": "value",
                        "children":[
                            {
                                "fieldCode": 121,
                                "fieldName":"利润",
                                "field":"profit",
                                "type":"value"
                            },
                            {
                                "fieldCode":122,
                                "fieldName":"收入",
                                "field":"income",
                                "type":"value"
                            },
                            {
                                "fieldCode":123,
                                "fieldName":"支出",
                                "field":"expenditure",
                                "type":"value"
                            }
                        ]
                    }
                ]
            },
            {
                "fieldCode": '297e49065d1ca822015d1cde7c7c0002',
                "fieldName": "传统饼图",
                "type": "root",
                "children": [
                    {
                        "fieldCode": 12,
                        "fieldName": "数值",
                        "type": "value",
                        "children":[
                            {
                                "fieldCode": 121,
                                "fieldName":"直接访问",
                                "field":"directAccess",
                                "type":"value"
                            },
                            {
                                "fieldCode":122,
                                "fieldName":"邮件营销",
                                "field":"emailMarketing",
                                "type":"value"
                            },
                            {
                                "fieldCode":123,
                                "fieldName":"联盟广告",
                                "field":"unionAd",
                                "type":"value"
                            },
                            {
                                "fieldCode":124,
                                "fieldName":"视频广告",
                                "field":"videoAd",
                                "type":"value"
                            },
                            {
                                "fieldCode":125,
                                "fieldName":"搜索引擎",
                                "field":"srhEngine",
                                "type":"value"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    methods: {
        //下一步骤
        fnNextStep:function(){
            if(this.curStep ++ >2)alert('完成');
            $('.optContent').hide();
            $('.content_'+this.curStep).show();
            if(this.curStep==1)this.fnInitTree();
        },
        //初始化图表列表
        initChartList:function(){
            var sURL = '/common/json/chartList.json';
            z.get(sURL,null,function(r){
                if(r.code==0)vm_chart.chartList = r.data.list;
            })
        },
        //选择图表
        fnSelChart:function(id){
            $('.nav-img').removeClass('active');
            $('#'+id).find('img.nav-img').addClass('active');
        },
        //选择数据源
        fnSelDatabase:function(){
            
        },
        // 渲染树节点内容区
        fnRenderTreeNode: function (createElement, b) {
            var exClass = '';
            if(!b.data.children){
                if(b.data.type == 'char')exClass = 'fa fa-wordpress';
                if(b.data.type == 'value')exClass = 'fa fa-database ';
            };
            return createElement('span', [
                createElement('i', {class:exClass}),
                createElement('span',{
                    class: 'el-tree-node__label',
                    attrs: {
                        'data-type': b.node.data.type,
                        'data-id': b.node.data[this.fieldTreeProps.id],
                        'data-label': b.node.data[this.fieldTreeProps.label],
                        'data-field':b.node.data[this.fieldTreeProps.id]
                    }
                }, b.node.data[this.fieldTreeProps.label])
            ])
        },
        //初始化树数据(demo数据筛选)
        fnInitTree:function(){
            for(var i=0;i<this.treeDataSet.length;i++){
                if(!i){
                    this.treeData.push(this.treeDataSet[i]);
                    setTimeout(function(){vm_chart.fnInitDrag();},100)   //初始化树拖拽
                    
                    if(z.getUrlParam('chartName')=='传统饼图'){
                         //此饼图只有一维
                        $('.xFieldInput').prev().hide();
                        $('.xFieldInput').hide();
                        $('.yFieldInput').prev().html('显示字段');
                    }
                                       
                    break;
                }
            }
        },
        // 左侧拖拽排序
        fnInitSortable:function(){
            $(".divFieldInput").sortable({
                update: function (event, ui) {
                    // 拖拽完更新排序信息到服务端
                    return;
                }
            });
        },
        // 设置子节点可拖拽
        fnInitDrag:function(){
            $('#divDataByDatabase .is-leaf').parent().draggable({
                helper: function( event ) {
                    var obj = $(this).find('span.el-tree-node__label');
                    return $( "<div class='ui-drag-widget' sType='"+obj.attr('data-type')+"' sId='"+obj.attr('data-id')+"' sField ='"+obj.attr('data-field')+"'>"+obj.attr('data-label')+"</div>" );
                }
            });

            $('.divFieldInput').droppable({
                drop: function (event, ui) {
                    if(ui.helper.hasClass('ui-drag-widget-sort'))return;
                    var sHTML = '';
                    sHTML +='<div class="ui-drag-widget ui-drag-widget-sort" sType="'+ui.helper.attr('sType');
                    sHTML +='"  sId="'+ui.helper.attr('sId')+'" sText="'+ui.helper.html()+'" sField="'+ui.helper.attr('sField');
                    sHTML +='">'+ui.helper.html()+'<i class="el-icon-close" onclick="vm_chart.fnDelField(this)"></i></div>';
                    $(this).append(sHTML);
                    $(this).find('span.tip').hide();
                    vm_chart.fnInitSortable();
                }
            });
        },
        //删除已选字段
        fnDelField:function(obj){
            if($(obj).parent().parent().find('div.ui-drag-widget-sort').length==1)$(obj).parent().parent().find('span.tip').show();
            $(obj).parent().remove();
            //vm.funShowChart();
        }
	},
    mounted: function () {
        this.initChartList();
    }
})