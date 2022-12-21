<template>
  <div class="wrapper" >
    <audio autoplay="autoplay" loop="loop" src="/assets/music/xinqiang.mp3"></audio>
  <!--  <div style="color: rgba(255,255,255,0.7);margin-bottom: 5px;font-family:'STXingkai';font-size: 40px;">你是天上的星星 最亮的那一颗</div>-->
    <div style="margin: 200px auto; background-color: rgba(255,255,255,0.08); width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <h1 style="margin: 20px 0; text-align: center; color: rgba(255,255,255,0.9);font-size: 28px"><b>Welcome Admins</b></h1>
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
      this.request.get("/admin/login",{
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
          this.$message.success("登陆成功")
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
  background: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fc%2F584e0d5a73e10.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669887083&t=9b6ddb0fcebdff56c5dde0c5635aaea7") no-repeat;
  background-size:100%;
  overflow: hidden;
}
</style>