var utilAttr = getUtilMergeAttr();
//获取table属性
var dialogAttr = getDialogMergeAttr({
    data: {
        countryInfo: {
            continentCode: null,
            chineseName: null,
            englishName: null,
            iso2code: null,
            isocode: null
        },
        continentCodeList: [],
        hasFlag: false,
        imageUrl : null
    },
    methods: {
        saveClick: function () {
            var result = ak.msService("mm", "IisCountryInfoApi/save", this.countryInfo);
            if (result.code === 0) {
                ak.msService("mm", "IisCountryNationalFlagApi/save", {engName: this.countryInfo.englishName});
                mainVue.tableRefresh();
            }
            if (this.hasFlag) {
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
            this.hasFlag = fileList.length > 0;
        },
        handleSuccess: function (response) {
            if (ak.isNotNullOrEmpty(response)) {
                this.imageUrl = response;
            }
        }
    }
});

//主属性
var mainAttr = {
    el: '#save',
    //页面初始化事件
    mounted: function () {
        this.selectInit("mm");
        this.continentCodeList = this.selectSearch("021");
    }
};

//生成vue对象
var updateVue = ak.getMergeVue(mainAttr, dialogAttr, utilAttr);

