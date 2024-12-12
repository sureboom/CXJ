<template>
  <div>
    <el-descriptions class="margin-top" :title="page.name" :column="3" border style="height: 300px;justify-content:center">
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          头像
        </template>
        <span>
          <el-row class="demo-avatar demo-basic">
            <el-col :span="12">
              <div class="demo-basic--circle">
                <div class="block">
                  <!-- 显示头像 -->
                  <el-avatar shape="square" :size="80" :fit="fits" :src="avatarUrl"></el-avatar>
                </div>
              </div>
            </el-col>
          </el-row>
        </span>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label" >
          <i class="el-icon-user"></i>
          empId
        </template>
        <span>{{page.empId}}</span>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        <span>{{page.name}}</span>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone" ></i>
          手机号
        </template>
        <span >{{page.phone}}</span>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-time"></i>
          入职时间
        </template>
        <span>{{formatDate(page.createTime)}}</span>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          角色
        </template>
        <el-tag size="small">{{formatRole(page.role)}}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          邮箱
        </template>
        {{page.email}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-male"></i>
          性别
        </template>
        {{formatGender(page.gender)}}
      </el-descriptions-item>
      <!--
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-loading"></i>
          密码
        </template>
        {{page.password}}
      </el-descriptions-item>-->
    </el-descriptions>
  </div>
</template>

<script>
export default {
  data() {
    return {
      seach: {
        account: '0',
      },
      page: {},
      squareUrl: require("@/assets/main.jpg"), // 默认头像
      avatarUrl: '', // 头像URL
      size: 'large',
      fits: 'contain',
    };
  },
  created() {
    this.loaddate();
  },
  methods: {
    loaddate() {
      this.seach.account = window.sessionStorage.getItem('empId');
      this.$http.get(`/queryEmpInfoById/${this.seach.account}`).then(res => {
        this.page = res.data;
        if (this.page.avatar) {
          // 如果后端返回了头像数据，将其转换为Base64
          this.avatarUrl = `data:image/jpg;base64,${this.arrayBufferToBase64(this.page.avatar)}`;
        }
      });
    },

    // 将字节数组（arrayBuffer）转换为Base64字符串
    arrayBufferToBase64(buffer) {
      let binary = '';
      const bytes = new Uint8Array(buffer);
      const len = bytes.byteLength;
      for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i]);
      }
      return window.btoa(binary);
    },

    formatDate(startTime) {
      var date = new Date(startTime);
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      return year + '-' + month + '-' + day;
    },
    formatRole(role) {
      switch (role) {
        case 0:
          return '员工';
        case 1:
          return '部门经理';
        case 2:
          return '副总经理';
        case 3:
          return '总经理';
        case 4:
          return '人事人员';
        case 5:
          return '财务人员';
        default:
          return '未知角色';
      }
    },
    formatGender(gender) {
      return gender === 0 ? '女' : '男';
    },
  },
};
</script>
