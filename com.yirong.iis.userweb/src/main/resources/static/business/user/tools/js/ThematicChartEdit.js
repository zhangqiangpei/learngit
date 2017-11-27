var vm_chart = new Vue({
    el: '#chartMain',
    data: {   
        curStep:0,//当前步骤
        chartList:[],//图表列表
        chartId:'0',
        myChart:'',//图表实例
        chartData:'',//图表实例数据
        orginData:'',//字段值数据
        dataBaseSourceDef:'',//数据来源为数据库时默认的数据库
        dataBaseSourceOption:[{value:'ma',label:'挖掘子系统'},{value:'pm',label:'采集子系统'}],
        fieldTreeProps: {
            children: 'children',
            label: 'fieldName',
            id:'fieldCode',
            field:'field'
        },
        treeData:[]
    },
    methods: {
        //上下步骤
        fnStep:function(step){
            if(step){
                this.curStep ++;
            }else{
                this.curStep --;
            };
            if(this.curStep>0){
                $('#btnPrev').show();
            }else{
                $('#btnPrev').hide();
            }
            if(this.curStep>2){
                //debugger;
                parent.vm.curItemCnt = this.chartData;
                if(typeof(parent.fnAfterSaveEdit)=='function')parent.fnAfterSaveEdit();
            }
            $('.optContent').hide();
            $('.content_'+this.curStep).show();
            if(this.curStep==1)this.fnInitDemo();
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
            this.chartId = id;
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
                        'data-field':b.node.data[this.fieldTreeProps.field]
                    }
                }, b.node.data[this.fieldTreeProps.label])
            ])
        },
        //初始化demo数据
        fnInitDemo:function(){
            var sURL = '/common/json/chart_tree_data.json';
            z.get(sURL,null,function(r){
                if(r.code==0&&r.data.length>0){
                    var data = r.data;
                    for(var i=0;i<data.length;i++){
                        if(vm_chart.chartId==data[i].fieldCode){
                            vm_chart.treeData=data[i];
                            setTimeout(function(){vm_chart.fnInitDrag();},100)   //初始化树拖拽
                            if(vm_chart.chartId=='2'){
                                 //此饼图只有一维
                                $('.xFieldInput').prev().hide();
                                $('.xFieldInput').hide();
                                $('.yFieldInput').prev().html('显示字段');
                            }
                            sURL = '/common/json/echarts_format_data.json';
                             z.ajax({
                                url : sURL,
                                async : false,
                                type : "GET",
                                dataType : "json",
                                success : function(res, textStatus) {
                                    if(res.code==0&&res.data.length>0){
                                        for(var j=0;j<res.data.length;j++){
                                            if(vm_chart.chartId==res.data[j].id){
                                                vm_chart.chartData = res.data[j];
                                                vm_chart.fnShowChart();
                                                break;
                                            }
                                        }
                                    }
                                }
                            })
                            break;
                        }
                    }
                }
            })
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
        },
        //处理图表实例数据
        fnDealChartData:function(){
            //获取字段对应数据
            //0/1/2为demo数据
            if(vm_chart.chartId=='0'||vm_chart.chartId=='1'||vm_chart.chartId=='2'){
                var sURL = '/common/json/chart_database.json';
                z.ajax({
                    url : sURL,
                    async : false,
                    type : "GET",
                    dataType : "json",
                    success : function(r, textStatus) {
                        var data = r.data;
                        for(var i=0;i<data.length;i++){
                            if(vm_chart.chartId==data[i].id){
                                vm_chart.orginData = data[i].data;
                                break;
                            }
                        }
                    }
                })
            }
            
            //获取对应图表格式数据
            var sURL = '/common/json/echarts_format_data.json';
            z.ajax({
                url : sURL,
                async : false,
                type : "GET",
                dataType : "json",
                success : function(r, textStatus) {
                    var data = r.data;
                    if(data.length>0){
                        for(var i=0;i<data.length;i++){
                            if(data[i].id == vm_chart.chartId){
                                var jo = data[i];

                                //此处暂时用来识别是否为饼图
                                if(this.chartId==2){
                                    //此饼图只有一维
                                    $('.xFieldInput').hide();
                                    $('.yFieldInput').prev().html('显示字段');
                                    var yArr = [];
                                    var yObj = $('.yFieldInput div.ui-drag-widget');
                                    var yLen = yObj.length;
                                    for(var i=0;i<yLen;i++){
                                        var o = {};
                                        o.id = $(yObj[i]).attr('sId');
                                        o.text = $(yObj[i]).attr('sText');
                                        o.field = $(yObj[i]).attr('sField');
                                        yArr.push(o);
                                    }

                                    var dataArr = [];var legendData = [];
                                    //按y轴字段获取对应的值
                                    for(var i=0;i<yArr.length;i++){
                                        var o = {};
                                        legendData.push(yArr[i].text);
                                        o.name = yArr[i].text;
                                        o.value = vm_chart.orginData[0][yArr[i].field];
                                        dataArr.push(o);
                                    }


                                    jo.series[0].data = dataArr;
                                    jo.legend.data = legendData;

                                }else{
                                    //todoxy轴转置
                                    
                                    var xArr = [];//存放x轴字段
                                    var yArr = [];//存放y轴字段

                                    var xObj = $('.xFieldInput div.ui-drag-widget');
                                    var yObj = $('.yFieldInput div.ui-drag-widget');

                                    var xLen = xObj.length;
                                    var yLen = yObj.length;
                                    if(xLen==0||yLen==0)return;
                                    for(var i=0;i<xLen;i++){
                                        var o = {};
                                        o.id = $(xObj[i]).attr('sId');
                                        o.text = $(xObj[i]).attr('sText');
                                        o.field = $(xObj[i]).attr('sField');
                                        xArr.push(o);
                                    }
                                    for(var i=0;i<yLen;i++){
                                        var o = {};
                                        o.id = $(yObj[i]).attr('sId');
                                        o.text = $(yObj[i]).attr('sText');
                                        o.field = $(yObj[i]).attr('sField');
                                        yArr.push(o);
                                    }
                                    //x轴做分类，y轴做值
                                    var xOption = [];var seriesData = [];var xAxisData = [];var legendData=[];
                                    //按x轴字段获取对应分类值
                                    for(var i=0;i<xArr.length;i++){
                                        for(var j=0;j<vm_chart.orginData.length;j++){
                                            var o = {};
                                            o.field = xArr[i].field;
                                            o.value = vm_chart.orginData[j][xArr[i].field];
                                            xAxisData.push(vm_chart.orginData[j][xArr[i].field]);
                                            xOption.push(o);
                                        }
                                    }

                                    //按y轴字段获取按x对应分类的值
                                    for(var i=0;i<yArr.length;i++){
                                        var o = {};
                                        o.name = yArr[i].text;
                                        o.stack = yArr[i].text;
                                        o.type = vm_chart.chartId=="0"?"bar":"line";//todo不同图表类型不同
                                        legendData.push(yArr[i].text);
                                        var arr = [];
                                        var sField = yArr[i].field;
                                        for(var j=0;j<xOption.length;j++){
                                            for(var k=0;k<vm_chart.orginData.length;k++){
                                                var sKey = xOption[j].field;
                                                var sVal = xOption[j].value;
                                                if(vm_chart.orginData[k][sKey]==sVal)arr.push(vm_chart.orginData[k][sField]);
                                            }
                                        }
                                        o.data = arr;
                                        seriesData.push(o);

                                    }

                                    jo.xAxis.data = xAxisData;
                                    jo.series = seriesData;
                                    jo.legend.data = legendData;
                                }
                                vm_chart.chartData = jo;
                                vm_chart.fnShowChart(); 
                                break;
                            }

                        }
                    }

                }
            })
        },
        //显示图表
        fnShowChart:function(){
            // 基于准备好的dom，初始化echarts实例
            if(typeof(this.myChart.clear)=='function')this.myChart.clear();
            this.myChart = echarts.init(document.getElementById('divChartContent'));
            this.myChart.setOption(this.chartData);
        }
	},
    mounted: function () {
        this.initChartList();
    }
})