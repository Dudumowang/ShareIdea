<template>
  <div>
    <!-- 头部  -->
    <div style="display: flex; height: 60px; line-height: 60px; border-bottom: 1px solid #ccc">

      <div style="width: 200px; display: flex; padding-left: 30px">
        <div>
          <img src="../../assets/logo.png" alt="" style="width: 30px; position: relative; top: 5px; cursor: pointer"  @click="$router.push('/front/home')">
        </div>

        <div style="flex: 1;margin-left: 20px; cursor:pointer;" @click="$router.push('/front/home')">Idea Sharing</div>
      </div>

      <!-- 导航菜单 -->
      <div style="flex: 1">
<!--        菜单-->
<!--        <ul style="list-style: none; background-color: #cccccc">-->

<!--          <li class="item"><a href="/userLogin" style="text-decoration: none">菜单1</a></li>-->

<!--          <li class="item"><a href="/userLogin" style="text-decoration: none">菜单2</a></li>-->

<!--          <li class="item"><a href="/userLogin" style="text-decoration: none">菜单3</a></li>-->

<!--          <li class="item"><a href="/userLogin" style="text-decoration: none">菜单4</a></li>-->

<!--        </ul>-->

        <el-menu :default-active="'test'" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="$router.push('/front/home')">首页</el-menu-item>
          <el-menu-item index="2" @click="$router.push('/front/idea')">创意清单</el-menu-item>
          <el-submenu index="3">
            <template slot="title">我的工作台</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
            <el-menu-item index="3-3">选项3</el-menu-item>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项1</el-menu-item>
              <el-menu-item index="3-4-2">选项2</el-menu-item>
              <el-menu-item index="3-4-3">选项3</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="4" disabled>消息中心</el-menu-item>
          <el-menu-item index="5"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
        </el-menu>
      </div>


      <div style="width: 200px">
        <div v-if="!user.username">
          <el-button type="primary" style="margin-left: 35px" @click="$router.push('/userLogin')">登录</el-button>
          <el-button type="primary" style="margin-left: 20px" @click="$router.push('/userRegister')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width: 34px; height:34px; display:flex; border-radius: 50%; position: relative; top: 10px; right: 20px">
            </div>

            <span style="right: 10px; position: relative">{{this.user.username}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown" >
              <el-dropdown-item style="font-size: 18px; padding: 5px 0; position: center; text-align: center">
                <router-link to="/front/person" style="text-decoration: none; color: #606266">个人信息</router-link>
              </el-dropdown-item>

              <el-dropdown-item style="font-size: 18px; padding: 5px 0; position: center">
                <span style="text-decoration: none; position: center; text-align: center" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </div>


      </div>

    </div>
    <div style="width: 1200px; margin: 0 auto">
      <router-view />
    </div>

  </div>
</template>

<script>
export default {
  name: "Front",
  data () {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },

  methods : {
    logout () {
      localStorage.removeItem("user")
      this.$router.push('/userLogin')
      this.$message.success('退出成功！')
    }
  }
}
</script>
<style scoped>

.item {
  display: inline-block;
  width: 100px;
  color: #1E90EF;
  text-align: center;
  cursor: pointer;
}

.item a{
  color: #1E90EF;
}

.item:hover {
  background-color: #FFDEE9;
}

</style>