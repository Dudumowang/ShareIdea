<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <el-menu-item index="/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>


      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item index="/user">
          <i class="el-icon-s-custom"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>

        <el-menu-item index="/admin">
          <i class="el-icon-s-custom"></i>
          <span slot="title">管理员管理</span>
        </el-menu-item>

        <el-menu-item index="/idea">
          <i class="el-icon-chat-line-round"></i>
          <span slot="title">创意管理</span>
        </el-menu-item>

        <el-menu-item index="/comment">
          <i class="el-icon-chat-dot-round"></i>
          <span slot="title">评论管理</span>
        </el-menu-item>

        <el-menu-item index="/file">
          <i class="el-icon-document"></i>
          <span slot="title">文件管理</span>
        </el-menu-item>

  <!--      <el-menu-item index="/role">
          <i class="el-icon-document"></i>
          <span slot="title">角色管理</span>
        </el-menu-item>-->
      </el-submenu>

      <el-menu-item index="/changePassword">
        <template slot="title">
          <i class="el-icon-lock"></i>
          <span slot="title">修改密码</span>
        </template>
      </el-menu-item>

      <el-menu-item index="/AdminLogin">
        <template slot="title">
          <i class="el-icon-switch-button"></i>
          <span slot="title">退出登录</span>
        </template>
      </el-menu-item>

    </el-menu>
  </template>

  <script>
  export default {
    name:"Aside",
    props:{
      isCollapse:Boolean,
      logoTextShow:Boolean
    }
  }
  </script>


  <style scoped>

  </style>