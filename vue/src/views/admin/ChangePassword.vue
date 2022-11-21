<template>
  <div style="width: 500px; padding: 20px; border: 1px solid #ccc">
    <el-form label-width="80px" size="small">

      <el-form-item label="原密码">
        <el-input show-password v-model:label="oldPassword" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input show-password v-model:label="newPassword" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input show-password v-model:label="confirmPassword" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="changeAdminPassword">确 定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "AdminChangePassword",
  data(){
    return{
      adminDto:{},
      oldPassword:'',
      newPassword:'',
      confirmPassword:'',
      form:{}

    }
  },
  created() {
    this.adminDto=JSON.parse(localStorage.getItem("admin"))
    if(this.admin==={}){
      this.$router.push("/login")
    }
  },
  methods :{
    changeAdminPassword(){
      if(this.newPassword===this.confirmPassword) {
        this.form.id=this.adminDto.id
        this.form.oldPassword=this.oldPassword
        this.form.newPassword=this.newPassword
        this.request.post("http://localhost:9090/admin/changePassword", this.form).then(res => {
          if(res.code==='200'){
            this.$message.success("修改成功")
            //退回到登录页面
            localStorage.removeItem("admin")
            this.$router.push("/AdminLogin")
          }
          else{
            this.$message.error(res.msg)
          }
        })
      }
      else{
        this.$message.error("两次输入密码不一致")
      }
    }
  }
}
</script>


<style scoped>

</style>