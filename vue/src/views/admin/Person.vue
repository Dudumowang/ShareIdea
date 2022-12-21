<template>
  <div style="width: 500px; padding: 20px; border: 1px solid #ccc">
    <el-form label-width="80px" size="small">
      <el-form-item label="账号ID">
        <el-input v-model="adminDto.id"></el-input>
      </el-form-item>
      <el-form-item label="管理员名">
        <el-input v-model="adminDto.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="adminDto.email"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="adminDto.mobile" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="输入密码">
        <el-input show-password v-model:label="password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Person",
  data(){
    return{
      adminDto:{},
      password:'',
      form:{},

    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.adminDto=JSON.parse(localStorage.getItem("admin"))
      if(this.adminDto==={})
        this.$router.push("/login")
      console.log(this.form)
    },
    save(){
      this.form={}
      this.form.id=this.adminDto.id
      this.form.password=this.password
      this.form.email=this.adminDto.email
      this.form.mobile=this.adminDto.mobile
      console.log(this.form)
      console.log(this.form.password)
      this.request.post("/admin/save",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("修改成功")
          //更新LocalStorage
          localStorage.removeItem("admin")
          localStorage.setItem("admin", JSON.stringify(this.adminDto)) //存储信息到浏览器
        }
        else{
          this.$message.error(res.msg);
        }
      })
    }
  }

}
</script>

<style>

</style>
