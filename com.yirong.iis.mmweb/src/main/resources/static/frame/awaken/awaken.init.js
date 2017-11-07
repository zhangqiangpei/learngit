//初始化头部配置
function initHeadConfig() {
	$.ajax({
		url : "/common/html/HeadConfig.html",
		async : false,
		type : "GET",
		dataType : "html",
		success : function(data, textStatus) {
			$("head").append(data);
		}
	});
}