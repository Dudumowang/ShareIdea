<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>管 理 员 登 录</b></div>
      <el-form :model="admin" ref="userForm">
        <el-form-item prop="id">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="admin.id"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="admin.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
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
      admin: {}
    }
  },
  methods: {
    login() {
      this.request.get("http://localhost:9090/admin/login",{
        params:{
          id:this.admin.id,
          password:this.admin.password
        }
      }).then(res => {
        console.log(res)
        if(res.code==='200') {
          localStorage.removeItem("admin")
          localStorage.setItem("admin", JSON.stringify(res.data)) //存储信息到浏览器
          console.log(JSON.stringify(res.data))
          this.$message.success("登录成功")
          this.$router.push("/home")
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, rgba(252, 70, 107, 0.02), #ffffff);
  overflow: hidden;
}
</style>