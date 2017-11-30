var ue = UE.getEditor('container', {
    autoHeightEnabled: false
});

//主属性
var mainAttr = {
    el: '#main',
    //页面初始化事件
    mounted: function () {
        this.engName = ak.url().engName;
        this.chnName = ak.url().chnName;
        var result = ak.msService("mm", "IisDiplomaticSituationApi/list", {englishName: this.engName});
        if (result.code === 0) {
            this.displomaticSituationInfo = result.data.data;
        }
    },
    data: {
        displomaticSituationInfo: [],
        engName: null,
        chnName : null
    },
    methods: {
        updateClick : function () {
            var param = {
                id : this.displomaticSituationInfo[0].id,
                content : ue.getContent(),
                englishName : this.engName
            };
            var result = ak.msService("mm","IisDiplomaticSituationApi/update",param);
            if (result.code === 0){
                ak.success(result.msg);
                top.vm.url = "/forward.do?viewPath=business/iis/IisCountryInfo/IisCountryInfoMain.html";
            }
        },
        resetClick: function () {
            ak.confirm("重置将会丢失所有未保存的内容，确定要重置吗？", this.resetCallback);
        },
        resetCallback : function (instance) {
            ue.setContent(updateVue.displomaticSituationInfo[0].CONTENT);
        }
    }
};

//生成vue对象
var updateVue = ak.getMergeVue(mainAttr);

ue.ready(function () {
    $('#main').height($(window).height()-$("#footer").height()-130);
    //设置编辑器的高度
    ue.setHeight($("#main").height());
    //设置编辑器的内容
    ue.setContent(updateVue.displomaticSituationInfo[0].CONTENT);
});

