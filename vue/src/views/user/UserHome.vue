<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <UserAside :isCollapse="isCollapse"/>
    </el-aside>
    <el-container>
      <el-header style="border-bottom: 1px solid #ccc;">
        <UserHeader :collapseBtnClass="collapseBtnClass" :collapse="collapse" />
      </el-header>

      <el-main>
        <!--当前页面子路由展示区域-->
        <router-view />
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

import UserAside from "@/components/UserAside";
import Header from "@/components/Header";
import UserHeader from "@/components/UserHeader";

export default {
  name: 'UserHome',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
    }
  },
  created() {
  },
  components: {
    UserHeader,
    UserAside,
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>

