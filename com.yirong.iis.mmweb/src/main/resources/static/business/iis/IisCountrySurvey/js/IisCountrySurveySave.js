var dialogAttr = getDialogMergeAttr({
    data: {
        saveModel: {
            countryEngName: detailVue.countryEngName,
            fieldName: null,
            fieldValue: null
        },
        //前端验证
        saveRules: {
            fieldName: [
                {required: true, message: '请输入字段名称', trigger: 'blur'}
            ],
            fieldValue: [
                {required: true, message: '请输入字段内容', trigger: 'blur'}
            ]

        }
    },
    methods:{
        save :function () {
            this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("mm","IisCountrySurveyApi/save",saveVue.saveModel);
                    if(result.code === 0){//操作成功
                        //提示
                        ak.success(result.msg);
                        //关闭窗口
                        saveVue.dialogClose();
                        //刷新表格
                        detailVue.tableRefresh();
                    }else{
                        //提示
                        ak.warning(result.msg);
                    }
                }
            });
        },
        reset :function () {
            this.$refs["saveModel"].resetFields();
        }
    }
});

//主属性
var mainAttr = {
    el: '#save'
};

//生成vue对象
var saveVue = ak.getMergeVue(mainAttr, dialogAttr);