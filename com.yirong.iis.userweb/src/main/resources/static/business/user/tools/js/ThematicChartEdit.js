var vm_chart = new Vue({
    el: '#chartMain',
    data: {   
        curStep:0,//当前步骤
        chartList:[],//图表列表
        chartId:'0',
        chartType:'bar',
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
        fnSelChart:function(item){
            this.chartId = item.id;
            this.chartType = item.type;
            $('.nav-img').removeClass('active');
            $('#'+item.id).find('img.nav-img').addClass('active');
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
            //置空图表、字段
            $('.divFieldInput .ui-drag-widget').remove();
            //$('#divChartContent').html('');
            try{
                var myChart = echarts.getInstanceByDom(document.getElementById('divChartContent'));  
                if(typeof(myChart.clear)=='function')myChart.clear();
            }catch(e){}
            
            var sURL = '/common/json/chart_tree_data.json';
            z.get(sURL,null,function(r){
                if(r.code==0&&r.data.length>0){
                    var data = r.data;
                    for(var i=0;i<data.length;i++){
                        if(vm_chart.chartType==data[i].chartType){
                            vm_chart.treeData = data[i].children;
                            setTimeout(function(){vm_chart.fnInitDrag();},100)   //初始化树拖拽
                            if(vm_chart.chartType=='pie'){
                                 //此饼图只有一维
                                $('.xFieldInput').prev().hide();
                                $('.xFieldInput').hide();
                                $('.yFieldInput').prev().html('显示字段');
                            }
                            //todo 显示demo echarts
                            
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
                            if(vm_chart.chartType==data[i].type){
                                vm_chart.orginData = data[i].data;
                                break;
                            }
                        }
                    }
                })
            }
            
                                
            if(this.chartType=='pie'){
                //此饼图只有一维
                $('.xFieldInput').hide();
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

                var dataArr = [];
                //按显示字段获取对应的值
                for(var i=0;i<yArr.length;i++){
                    var o = {};
                    o.name = yArr[i].text;
                    o.value = vm_chart.orginData[0][yArr[i].field];
                    dataArr.push(o);
                }
                
               this.chartData = MyECharts.ChartOptionTemplates.Pie('', '', dataArr);
            }else{
                //todoxy轴转置

                var xArr = [];//存放分组字段
                var yArr = [];//存放指标字段

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
                
                if(xArr.length>2){
                    z.error('最多支持二维分组');
                    return;
                }
                
                var dataArr = [];
                
                for(var i=0;i<vm_chart.orginData.length;i++){
                    
                        
                        for(var j=0;j<yArr.length;j++){
                            var jo = {};
                            jo.name = vm_chart.orginData[i][xArr[0].field];
                            
                            //分组有两个字段时，一个做group,一个做name即x轴分类
                            if(xArr.length==1){
                                jo.group = yArr[j].field;
                            }else{
                                jo.group = vm_chart.orginData[i][xArr[1].field]; 
                            }
                            
                            jo.value = vm_chart.orginData[i][yArr[j].field];
                            dataArr.push(jo);
                        }
                        
                    
                  
                }
                if(this.chartType=='bar'){
                    this.chartData = MyECharts.ChartOptionTemplates.Bar('', '', dataArr);
                }else if(this.chartType=='line'){
                    this.chartData = MyECharts.ChartOptionTemplates.Line('', '', dataArr);
                }
                

            }
            vm_chart.fnShowChart(); 
              
        },
        //显示图表
        fnShowChart:function(){
            MyECharts.RenderChart(this.chartData, 'divChartContent');
            
            // 基于准备好的dom，初始化echarts实例
//            if(typeof(this.myChart.clear)=='function')this.myChart.clear();
//            this.myChart = echarts.init(document.getElementById('divChartContent'));
//            this.myChart.setOption(this.chartData);
        }
	},
    mounted: function () {
        this.initChartList();
    }
})