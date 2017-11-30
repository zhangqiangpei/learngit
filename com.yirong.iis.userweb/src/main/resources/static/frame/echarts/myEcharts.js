Array.prototype.contains = function (obj) {  
    var i = this.length;  
    while (i--) {  
        if (this[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
}  


//暂不支持x、y轴转置

var MyECharts = {
    //格式化数据
    ChartDataFormate: {
        FormateNOGroupData: function (data) {
            var categories = [];
            var datas = [];
            for (var i = 0; i < data.length; i++) {
                categories.push(data[i].name || '');
                var temp_series = { value: data[i].value || 0, name: data[i].name };
                datas.push(temp_series);
            }
            return { category: categories, data: datas };
        },
        //处理分组数据，数据格式：group：XXX，name：XXX，value：XXX用于折线图、柱形图（分组，堆积）
        //参数：数据、展示类型
        FormatGroupData: function (data, type) {
            var groups = new Array();
            var names = new Array();
            var series = new Array();
            for (var i = 0; i < data.length; i++) {
                if (!groups.contains(data[i].group)) {
                    groups.push(data[i].group);
                }
                if (!names.contains(data[i].name)) {
                    names.push(data[i].name);
                }
            }
            for (var i = 0; i < groups.length; i++) {
                var temp_series = {};
                var temp_data = new Array();
                for (var j = 0; j < data.length; j++) {
                    for (var k = 0; k < names.length; k++)
                        if (groups[i] == data[j].group && data[j].name == names[k])
                            temp_data.push(data[j].value);
                    }
                    var stack = '';
                    if(data[i].stack!=null)stack = data[i].stack;
                    temp_series = { name: groups[i], type: type,stack:stack, data: temp_data };
                    series.push(temp_series);
                }
                return { category: names,group:groups ,series: series };
            }
        },
    //生成图形
    ChartOptionTemplates: {
        //柱状图
        Bar: function (title, subtext, data) {
            var datas = MyECharts.ChartDataFormate.FormatGroupData(data, 'bar');
            var option = {
                title: {
                    show:"false",
                    text: title || '',
                    subtext: subtext || '',
                    x: 'left'
                },
                tooltip: {
                    show: true
                },
                legend: {
                    data: datas.group
                },
                xAxis: [
                {
                    type: 'category',
                    data: datas.category
                }
                ],
                yAxis: [
                {
                    type: 'value'
                }
                ],
                series: datas.series
            };
            return option;
        },
        //折线图
        Line: function (title, subtext, data) {
            var datas = MyECharts.ChartDataFormate.FormatGroupData(data, 'line');
            var option = {
                title: {
                    show:"false",
                    text: title || '',
                    subtext: subtext || '',
                    x: 'left'
                },
                tooltip: {
                    show: true
                },
                legend: {
                    data: datas.group
                },
                xAxis: [
                {
                    type: 'category',
                    data: datas.category
                }
                ],
                yAxis: [
                {
                    type: 'value'
                }
                ],
                series: datas.series
            };
            return option;
        },
        //饼图
        Pie: function (title, subtext, data) {
            var datas = MyECharts.ChartDataFormate.FormateNOGroupData(data);
            var option = {
                title: {
                    show:"false",
                    text: title || '',
                    subtext: subtext || '',
                    x: 'center'
                },
                tooltip: {
                    show: true,
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: datas.category
                },
                series: [
                {
                    name: title,
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: datas.data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
                ]
            };
            return option;
        },
        //散点图
        Scatter: function (title, subtext, data) {
            var markLineOpt = {
                animation: false,
                label: {
                    normal: {
                        formatter: 'y = 0.5 * x + 3',
                        textStyle: {
                            align: 'right'
                        }
                    }
                },
                lineStyle: {
                    normal: {
                        type: 'solid'
                    }
                },
                tooltip: {
                    formatter: 'y = 0.5 * x + 3'
                },
                data: [[{
                    coord: [0, 3],
                    symbol: 'none'
                }, {
                    coord: [20, 13],
                    symbol: 'none'
                }]]
            };
            var option = {
                title: {
                    text: 'Anscombe\'s quartet',
                    x: 'center',
                    y: 0
                },
                grid: [
                {x: '7%', y: '7%', width: '38%', height: '38%'},
                {x2: '7%', y: '7%', width: '38%', height: '38%'},
                {x: '7%', y2: '7%', width: '38%', height: '38%'},
                {x2: '7%', y2: '7%', width: '38%', height: '38%'}
                ],
                tooltip: {
                    formatter: 'Group {a}: ({c})'
                },
                xAxis: [
                    {gridIndex: 0, min: 0, max: 20},
                    {gridIndex: 1, min: 0, max: 20},
                    {gridIndex: 2, min: 0, max: 20},
                    {gridIndex: 3, min: 0, max: 20}
                ],
                yAxis: [
                    {gridIndex: 0, min: 0, max: 15},
                    {gridIndex: 1, min: 0, max: 15},
                    {gridIndex: 2, min: 0, max: 15},
                    {gridIndex: 3, min: 0, max: 15}
                ],
                series: [
                {
                    name: 'I',
                    type: 'scatter',
                    xAxisIndex: 0,
                    yAxisIndex: 0,
                    data: data[0],
                    markLine: markLineOpt
                },
                {
                    name: 'II',
                    type: 'scatter',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: data[1],
                    markLine: markLineOpt
                },
                {
                    name: 'III',
                    type: 'scatter',
                    xAxisIndex: 2,
                    yAxisIndex: 2,
                    data: data[2],
                    markLine: markLineOpt
                },
                {
                    name: 'IV',
                    type: 'scatter',
                    xAxisIndex: 3,
                    yAxisIndex: 3,
                    data: data[3],
                    markLine: markLineOpt
                }
                ]
            };
            return option;
        }
    },
    //图形展示
    //参数：图形option、图形显示区域id
    RenderChart: function (option, containerId) {
        var container = eval("document.getElementById('" + containerId + "');");//获取图形显示区
        var myChart = echarts.init(container);
        if(typeof(myChart.clear)=='function')myChart.clear();
        myChart.setOption(option);// 为echarts对象加载数据 
    }
};
