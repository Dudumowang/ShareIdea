<template>
  <el-card style="width: 400px">

    <el-form label-width="80px" size="small">
      <el-form-item label="登录ID">
        <el-input v-model="form.id" autocomplete="off" readonly=true></el-input>
      </el-form-item>
      <el-form-item label="用户名" >
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>

    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "UserInfo",
  data(){
    return {
      form:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    this.form = Object.assign({},this.user)
  },
  methods:{
    save(){
      this.request.post("http://localhost:9090/user/save",this.form).then(res => {
        if (res.code == '200'){
          this.$message.success("保存成功！")
        }else {
          this.$message.error(res.msg)
        }
      })
    },
  },
}
</script>

<style scoped>

</style>