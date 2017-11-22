var commonVue = new Vue({
	//方法
	methods : {
		/*//根据对象ID获取要素说明
		getPmElementRemark : function(oid){
			var data = ak.msService("pm","PmElementApi/getByObjectId",{ objectId : oid});
			if(data.code == 0){
				return data.data.elementRemark;
			}
		},*/
		//根据类型，处理条件下拉变量
		doConditionListByfieldType : function(fieldType,conditionList){
			/**
			 * 所有类型都有：等于、不等于
			 * string有包含、不包含、为空、不为空，没有：大于、大于等于、小于、小于等于
			 * int、double及date有： 大于、大于等于、小于、小于等于，没有：包含、不包含、为空、不为空
			 * boolean没有包含、不包含、为空、不为空、大于、大于等于、小于、小于等于
			 * **/
			$.each(conditionList,function(i,n){
				var key = n.key
				if("017001" == fieldType){//string
					if("018007" == key || "018008" == key || "018009" == key || "018010" == key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}else if("017002" == fieldType || "017004" == fieldType || "017005" == fieldType){//int、double及date
					if("018003" == key || "018004" == key || "018005" == key || "018006" == key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}else{//boolean
					if("001701" != key || "017002" != key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}
			});
		},
		doConditionListByfieldTypeIm : function(fieldType,conditionList){
			/**
			 * 所有类型都有：等于、不等于
			 * string有包含、不包含、为空、不为空，没有：大于、大于等于、小于、小于等于
			 * int、double及date有： 大于、大于等于、小于、小于等于，没有：包含、不包含、为空、不为空
			 * boolean没有包含、不包含、为空、不为空、大于、大于等于、小于、小于等于
			 * **/
			$.each(conditionList,function(i,n){
				var key = n.key
				if("037001" == fieldType){//string
					if("027007" == key || "027008" == key || "027009" == key || "027010" == key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}else if("037002" == fieldType || "037004" == fieldType || "037005" == fieldType){//int、double及date
					if("027003" == key || "027004" == key || "027005" == key || "027006" == key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}else{//boolean
					if("003701" != key || "037002" != key){
						n.disabled = true;
					}else{
						n.disabled = false;
					}
				}
			});
		},
		 getBidDistributionPieChart: function (domId, data, isStacked) {
	            /**处理数据**/
	            var legendList = [];//类别信息
	            var xList = [];//x轴信息
	            var seriesList = [];//数据信息
	            //处理列别信息
	            for (key in data.seriesMap) {
	                legendList.push(key);
	            }
	            //处理x轴信息
	            $.each(data.xList, function (i, n) {
	                var values = n.split("-");
	                var value = values[1] + "." + values[2];
	                xList.push(value);
	            })
	            //处理数据信息
	            for (key in data.seriesMap) {
	                var series = {
	                    name: key,
	                    type: 'bar',
	                    data: data.seriesMap[key]
	                };
	                //判断是否堆叠
	                if (isStacked)
	                //添加堆名称
	                    series.stack = "stack";
	                else {
	                    //不堆叠则添加最大最小值以及平均值
	                    series.markPoint = {
	                        data: [
	                            {type: 'max', name: '最大值'},
	                            {type: 'min', name: '最小值'}
	                        ]
	                    };
	                    series.markLine = {
	                        data: [
	                            {type: 'average', name: '平均值'}
	                        ]
	                    }
	                }
	                seriesList.push(series);
	            }
	            /**处理元素**/
	                //初始化元素图表对象
	            var pieChart = echarts.init(document.getElementById(domId));
	            pieChart.clear();
	            //配置属性
	            var option = {
	                title: {
	                    text: '采集情况',
	                    subtext: '按天'
	                },
	                tooltip: {
	                    trigger: 'axis'
	                },
	                legend: {
	                    data: legendList
	                },
	                toolbox: {
	                    show: true
	                },
	                calculable: true,
	                xAxis: [
	                    {
	                        type: 'category',
	                        data: xList
	                    }
	                ],
	                yAxis: [
	                    {
	                        type: 'value'
	                    }
	                ],
	                series: seriesList
	            };

	            // 使用刚指定的配置项和数据显示图表。
	            pieChart.setOption(option);
	        }
	}
});