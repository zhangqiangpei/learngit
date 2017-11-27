function initCountry() {
	//初始化国家搜索公共部分
	$.ajax({
		url : "forward.do?viewPath=common/CountryQueryCommon.html",
		async : false,
		type : "GET",
		dataType : "html",
		success : function(data, textStatus) {
			$("#countryQuery").html(data);
		}
	});
	//初始化国家导航栏公共部分
	$.ajax({
		url : "forward.do?viewPath=common/CountryHomeCommon.html",
		async : false,
		type : "GET",
		dataType : "html",
		success : function(data, textStatus) {
			$("#CountryNavigation").html(data);
		}
	});
}
