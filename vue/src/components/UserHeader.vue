<template>
  <div style="font-size: 12px;line-height: 60px;display: flex">
    <div style="flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/UserHome'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="display: inline-block">
      <router-link to="/UserInfo">
        <img :src="user.avatarUrl" alt=""
             style="width: 34px; height:34px; display:flex; border-radius: 50%; position: relative; top: 10px; right: 20px"></router-link>
    </div>
    <el-dropdown style="width: 70px; cursor: pointer">
      <span style="right: 10px; position: relative">{{this.user.username}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item style="font-size: 18px; padding: 5px 0;">
          <router-link to="/UserInfo" style="position: center">个人信息</router-link>
        </el-dropdown-item>

        <el-dropdown-item style="font-size: 18px; padding: 5px 0;position: center">
          <span style="text-decoration: none; position: center" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "UserHeader",

  data(){
    return {
      id:"",
      user:{},
    }
  },
  methods:{
    logout(){
      this.$router.push("/userLogin")
      localStorage.removeItem("user")
      this.$message.success("退出成功！")
      this.id=""
    }
  },
  created() {
    if(localStorage.getItem("user"))
      this.user=JSON.parse(localStorage.getItem("user"))
    else{
      this.$router.push("/userLogin")
    }
  },
  props:{
    collapseBtnClass:String,
    collapse:Function
  },
  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  watch: {
    currentPathName (newVal, oldVal) {
      console.log(newVal)
    }
  },
}
</script>

<style scoped>

</style>