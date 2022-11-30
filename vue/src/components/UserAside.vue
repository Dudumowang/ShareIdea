<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">创意分享平台</b>
    </div>
    <el-menu-item index="/UserHome">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>



    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name:"UserAside",
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean
  }
}
</script>


<style scoped>

</style>