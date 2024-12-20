<template>
    <el-row>
      <el-col :span="10" justify="center">
        <el-upload
          class="avatar-uploader"
          action="E:/Code/.idea/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <p style="margin-top: 10px; color: #999; text-align: center;">请点击上方区域修改头像</p>
        <el-form ref="form" :model="employer" label-width="80px">
          <el-form-item label="手机号">
            <el-input v-model="employer.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱号">
            <el-input v-model="employer.email"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onUpdate">修改信息</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </template>
  
  <script>
  export default {
        data() {
      return {
        employer: {
          phone: '',
          email: '',
          imageUrl: '',
        },
        avatarFile: null, // 初始化头像文件
      };
    },
    methods: {
      async onUpdate() {
        const formData = new FormData();
        formData.append('phone', this.employer.phone);
        formData.append('email', this.employer.email);
        if (this.avatarFile) {
          formData.append('avatar', this.avatarFile);
        }

        try {
          const res = await this.$http.post('updateEmpInfo', formData);

          if (res.data.success) {
            this.$message({
              message: '信息修改成功',
              type: 'success',
            });
            this.resetForm();
          } else {
            this.$message({
              message: '信息修改失败',
              type: 'error',
            });
          }
        } catch (error) {
          this.$message({
            message: '更新过程中发生错误',
            type: 'error',
          });
        }
      },
      resetForm() {
        this.employer = { phone: '', email: '', imageUrl: '' };
        this.avatarFile = null;
      },
      handleAvatarSuccess(res, file) {
        this.avatarFile = file.raw; // 保存文件对象，用于上传
        this.employer.imageUrl = URL.createObjectURL(file.raw); // 修正绑定
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
    },


  }
  </script>
  
  <style>
  .avatar-uploader {
  margin: auto; /* 灵活居中 */
  display: flex;
  flex-direction: column;
  align-items: center;
    }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  </style>
  