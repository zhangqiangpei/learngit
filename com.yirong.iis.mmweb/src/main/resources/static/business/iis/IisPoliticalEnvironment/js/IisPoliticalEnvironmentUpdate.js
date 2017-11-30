var dialogAttr = getDialogMergeAttr({
    data: {
        updateModel: {
            fieldName: null,
            fieldValue: null
        },
        //前端验证
        updateRules: {
            fieldName: [
                {required: true, message: '请输入字段名称', trigger: 'blur'}
            ],
            fieldValue: [
                {required: true, message: '请输入字段内容', trigger: 'blur'}
            ]

        }
    },
    methods: {
        update: function () {
            this.$refs["updateModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var result = ak.msService("mm", "IisPoliticalEnvironmentApi/update", updateVue.updateModel);
                    if (result.code === 0) {//操作成功
                        //提示
                        ak.success(result.msg);
                        //关闭窗口
                        updateVue.dialogClose();
                        //刷新表格
                        detailVue.tableRefresh();
                    } else {
                        //提示
                        ak.warning(result.msg);
                    }
                }
            });
        },
        reset: function () {
            this.$refs["updateModel"].resetFields();
        }
    }
});

//主属性
var mainAttr = {
    el: '#update',
    mounted: function () {
        var result = ak.msService("mm", "IisPoliticalEnvironmentApi/get", {id: detailVue.updateSurveyId});
        if (result.code === 0) {
            this.updateModel.id = result.data.id;
            this.updateModel.fieldName = result.data.FIELD_NAME;
            this.updateModel.fieldValue = result.data.FIELD_VALUE;
        }
    }
};

//生成vue对象
var updateVue = ak.getMergeVue(mainAttr, dialogAttr);