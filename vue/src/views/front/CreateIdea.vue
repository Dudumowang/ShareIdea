<template>
  <div>
    <el-form label-width="80px" size="small" style="margin-top: 20px">

      <el-form-item label="创意标题">
        <el-input v-model="idea.title" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="创意内容">
        <mavon-editor ref="md" v-model="idea.content" :ishljs="true" @imgAdd="imgAdd" style="height: 550px"/>
      </el-form-item>

    </el-form>

    <div style="text-align: right">
      <el-button type="primary" @click="createIdea">确认发布</el-button>
    </div>



  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "CreateIdea",
  data() {
    return {
      idea : {},
      user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      param: {}
    }
  },
  methods: {
    // 绑定@imgAdd event
    imgAdd(pos, $file) {
      // 将图片上传到服务器
      const formData = new FormData();
      formData.append('file', $file);
      axios({
        url:'http://localhost:9090/file/upload',
        method:'post',
        data:formData,
        headers: {'Content-Type': 'multipart/form-data'},
      }).then(res => {
        // 将返回的url替换到文本原位置
        if (res.status === 200){
          this.$refs.md.$img2Url(pos, res.data)
        }
      })
    },

    createIdea(){
      this.request.post('http://localhost:9090/idea/save',this.param)
          .then( res => {
            if (res.code === '200') {
              this.$message.success("创意分享成功！")
              Object.assign(this.idea, {})
              Object.assign(this.param, {})
            } else {
              this.$message.error(res.msg)
            }
          })
    }
  }
}
</script>

<style scoped>

</style>