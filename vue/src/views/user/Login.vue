<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>用 户 登 录</b></div>
      <el-form :model="user" ref="userForm">
        <el-form-item prop="id">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.id"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
          <el-button type="primary" size="small"  autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {}
    }
  },
  methods: {
    login() {
      this.request.get("http://localhost:9090/user/login",{
        params:{
          id:this.user.id,
          password:this.user.password
        }
      }).then(res => {
        if(res.code === '200') {
          localStorage.setItem("user", JSON.stringify(res.data)) //存储信息到浏览器
          console.log(JSON.stringify(res.data))
          this.$message.success("登录成功")
          this.$router.push("/front")
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-color: #FFDEE9;
  background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);

  overflow: hidden;
}
</style>