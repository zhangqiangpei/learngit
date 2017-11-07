//获取dialog属性
var dialogAttr = getDialogMergeAttr({
	el: '#update',
	data : {
		imageUrl: ''
	},
	methods : {
		handleAvatarSuccess:function(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload:function(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
	},
	//页面初始化事件
    mounted: function () {
    	
    }
});
//生成vue对象
var updateVue = new Vue(dialogAttr);