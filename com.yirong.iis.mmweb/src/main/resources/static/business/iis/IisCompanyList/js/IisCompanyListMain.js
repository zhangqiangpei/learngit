var utilAttr = getUtilMergeAttr();
//获取table属性
var tableAttr = getTableMergeAttr({
    data: {
        //查询对象
        tableSearchModel: {
        	companyName: "",
        	companyChnName: "",
            type: ""
        },
        typeList: [],
        id : null
    },
    methods: {
        //查询按钮
        searchClick: function () {
            var param = this.tableSearchModel;
            // 当前页切换成第一页
            param.page = 1;
            //查询后台
            this.tableSearch(param);
        },
        resetClick: function () {
            this.tableResetCondition();
        },
        //新增按钮
        addClick: function () {
            ak.dialog("save", "business/iis/IisCompanyList/IisCompanyListSave.html");
        },
      //删除按钮
        delClick: function () {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            
            /*var idsStr = "";
            $.each(ids,function(i,n){
              idsStr += n;
              if(i != ids.length){
                idsStr += ",";
              }
            });
            alert(idsStr);*/
            
            if (ids.length === 0) {
                ak.warning("请选择需要删除的公司");
            } else {
                ak.confirm("确定要删除选中的" + ids.length + "个公司吗？", this.delCallback);
            }
        },
        //确认删除的回调函数
        delCallback: function (instance) {
            //获取表格选中的id集
            var ids = this.tableGetCheckIds();
            var str=ids.join(",");
            var result = ak.msService("mm","IisCompanyListApi/delete",{"ids" : str});
            if (null!=result && result.code === 0){
                ak.success(result.msg);
                //刷新表格
            	mainVue.tableRefresh();
            }
        },
        /*detailClick : function (row) {
            this.id = row.ID;
            ak.dialog("detail", "business/iis/IisCompanyList/IisCompanyListDetail.html");
        }*/
    }
});

//主属性
var mainAttr = {
    el: '#main',
    //页面初始化事件
    mounted: function () {
        //初始化table
        this.tableInit("mm", "IisCompanyListApi/list");
        //默认排序
        this.tableInitSort("create_time", "desc");
        this.searchClick();
        this.selectInit("mm");
        this.typeList = this.selectSearch("025");
    }
};

//生成vue对象
var mainVue = ak.getMergeVue(mainAttr, tableAttr, utilAttr);