<template>
  <div style="font-size: 12px;line-height: 60px;display: flex">
    <div style="flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="display: inline-block">
      <router-link to="/person">
      <img :src="admin.avatarUrl" alt=""
           style="width: 34px; height:34px; display:flex; border-radius: 50%; position: relative; top: 10px; right: 10px"></router-link>
    </div>
    <el-dropdown style="width: 70px; cursor: pointer">
      <span>{{this.admin.id}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/person">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/changePassword">修改密码</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",

  data(){
    return {
      id:"",
      admin:{},
    }
  },
  methods:{
    logout(){
      this.$router.push("/AdminLogin")
      localStorage.removeItem("admin")
      this.id=""
    }
  },
  created() {
    if(localStorage.getItem("admin"))
        this.admin=JSON.parse(localStorage.getItem("admin"))
    else{
      this.$router.push("/AdminLogin")
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