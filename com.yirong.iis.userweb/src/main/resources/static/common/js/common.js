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
		}
	}
});