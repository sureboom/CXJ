<template>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" show-password autocomplete="off" style="width: auto;"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" style="width: auto;" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script>
export default {
  data() {
    return {
      ruleForm: {
        pass: '',
        checkPass: 'password'
      },
      rules: {
        pass: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: this.validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { validator: this.validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    // 如果需要初始化数据，可以在这里调用对应方法
  },
  methods: {
    
    // 表单提交逻辑
    submitForm() {
      this.$refs.ruleForm.validate().then(valid => {
        if (valid) {
          this.updatePassword(this.ruleForm.pass);
        } else {
          this.$message.error('表单验证未通过，请检查输入');
        }
      });
    },

    // 表单重置逻辑
    resetForm() {
      this.$refs.ruleForm.resetFields();
    },

    /*更新密码接口调用
    updatePassword(password) {
      this.seach.account = window.sessionStorage.getItem('empId');
      this.$http
        .post('/updatePassword'+this.seach.account+ { password })
        .then(res => {
          if (res.data.success) {
            this.$message.success('密码更新成功');
            this.resetForm(); // 清空表单
          } else {
            this.$message.error(res.data.message || '密码更新失败');
          }
        })
        .catch(() => {
          this.$message.error('请求失败，请重试');
        });
    },*/
    updatePassword(password) {
  // 获取 empId
  const empId = window.sessionStorage.getItem('empId');
  // 发起请求
  this.$http.post(`/updatePassword/${empId}/${password}`)
    .then(res => {
      if (res.data) {
        this.$message.success('密码更新成功');
        this.resetForm(); // 清空表单
      } else {
        this.$message.error(res.data.message || '密码更新失败');
      }
    })
    .catch(() => {
      this.$message.error('请求失败，请重试');
    });
},


    // 验证密码
    validatePass(rule, value, callback) {
      if (!value) {
        callback(new Error('请输入密码'));
      } else if (value.length < 6) {
        callback(new Error('密码长度不能少于6位'));
      } else {
        callback();
      }
    },

    // 验证确认密码
    validatePass2(rule, value, callback) {
      if (!value) {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
  }
};
</script>
