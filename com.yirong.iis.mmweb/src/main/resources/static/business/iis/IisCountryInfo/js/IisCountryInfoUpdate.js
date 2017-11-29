var utilAttr = getUtilMergeAttr();
//获取table属性
var dialogAttr = getDialogMergeAttr({
    data: {
        countryInfo: null,
        continentCodeList: [],
        flag: null,
        oldflag: null,
        col: 12,
        hasNewFlag: false
    },
    methods: {
        updateClick: function () {
            var result = ak.msService("mm", "IisCountryInfoApi/update", this.countryInfo);
            if (result.code === 0) {
                detailVue.loadCountryInfo();
            }
            if (this.hasNewFlag) {
                this.$refs.upload.submit();
            }
            this.dialogClose();
        },
        resetClick: function () {
            this.$refs['countryInfo'].resetFields();
        },
        handleChange: function (file, fileList) {
            var imgExt = new Array(".png", ".jpg", ".jpeg", ".bmp", ".gif");
            var fileExt = file.name.substring(file.name.lastIndexOf("."));
            var isImage = false;
            for (var i = 0; i < imgExt.length; i++) {
                if (imgExt[i] === fileExt) {
                    isImage = true;
                    break;
                }
            }
            if (isImage) {
                this.col = 24;
                if (fileList.length > 1) {
                    fileList.splice(0, 1);
                    ak.info("只能选择一张图片");
                }
            } else {
                ak.info("请选择一张图片");
                fileList.length = 0;
            }
            this.hasNewFlag = fileList.length > 0;
        },
        handleSuccess: function (response) {
            if (response) {
                ak.msService("mm", "IisCountryNationalFlagApi/update", {engName: mainVue.id});
            }
        }
    }
});

//主属性
var mainAttr = {
    el: '#update',
    //页面初始化事件
    mounted: function () {
        this.selectInit("mm");
        this.continentCodeList = this.selectSearch("021");
        var result = ak.msService("mm", "IisCountryInfoApi/get", {id: mainVue.id});
        if (result.code === 0) {
            this.countryInfo = result.data;
        }
        result = ak.msService("mm", "IisCountryNationalFlagApi/get", {engName: mainVue.id});
        if (result.code === 0) {
            if (ak.isNotNullOrEmpty(result.data))
                this.oldflag = result.data;
            else
                this.col = 24;
        }
    }
};

//生成vue对象
var updateVue = ak.getMergeVue(mainAttr, dialogAttr, utilAttr);

