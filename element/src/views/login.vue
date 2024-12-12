<template>
  <el-form class="login-container" label-position="left"
           label-width="0px" >
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      loginForm: {
        account: '',
        password: ''
      }
    };
  },
  methods: {
    submitClick() {
      const _this = this;
      if (_this.loginForm.account === '' || _this.loginForm.password === '') {
        this.$alert('账号或密码不能为空', '提示', {
          confirmButtonText: '确定'
        });
      } else {
        // 使用 POST 请求发送登录信息
        _this.$http.post('/login', _this.loginForm).then(response => {
          const data = response.data;
          if (data.status === 'success') {
            _this.$alert('登录成功', '提示', {
              confirmButtonText: '确定'
            });
            // 保存用户信息到 sessionStorage
            const userData = data.data;
            window.sessionStorage.setItem('empId', userData.empId);
            window.sessionStorage.setItem('account', userData.account);
            window.sessionStorage.setItem('name', userData.name);
            window.sessionStorage.setItem('role', userData.role);
            // 跳转到主页
            _this.$router.replace({ path: '/home/hello' });
          } else {
            _this.$alert(data.message || '登录失败', '提示', {
              confirmButtonText: '确定'
            });
          }
        }).catch(error => {
          console.error(error);
          _this.$alert('服务器错误，请稍后再试', '提示', {
            confirmButtonText: '确定'
          });
        });
      }
    }
  }
};
</script>
