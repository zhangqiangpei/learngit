/**dialog公用属性继承类**/
//定义dialog公用属性
function getDialogMergeAttr(attrs){
	//第一个参数必须为true，代表深层拷贝
	attrs = $.extend(true,{
		data: {
			//默认显示
			dialogVisible: true
		},
		methods :{
			//关闭当前弹出框
			dialogClose : function(){
				this.dialogVisible = false;
				var id = $(this.$el).attr("id");
				$("#" + id).remove();
			}
		}
	},attrs);
	return attrs;
}