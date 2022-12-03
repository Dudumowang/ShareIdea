<template>
  <div style="color: #666666">

    <div style="margin: 20px 0">

      <div style="font-size: 20px; color:#1E90EF;font-weight: bold; cursor: pointer" @click="$router.push('/front/idea')">
        {{ idea.title }}
      </div>

      <div style="font-size: 14px; margin-top: 20px">
        <i class="el-icon-user"></i> <span> {{ idea.uid }}</span>
        <i class="el-icon-time" style="margin-left: 10px"></i> <span> {{ idea.finTime }}</span>
      </div>

    </div>


    <div style="margin: 20px 0">

      <mavon-editor
          class="md"
          :value="idea.content"
          :subfield="false"
          :default-open="'preview'"
          :toolbars-flag="false"
          :editable="false"
          :scroll-style="true"
          :ishljs="true"
      />

    </div>

    <div style="margin: 30px 0">

      <div style="margin: 10px 0">


      <div style="border-bottom: 1px solid #bdc3c7; padding: 10px 0; font-size: 20px">评论</div>

      <div style="padding: 10px 0">
        <el-input type="textarea" v-model="commentForm.content"  placeholder="请输入评论" :rows="6"></el-input>
      </div>

      <div style="padding: 10px 0; text-align: right">
        <el-button type="primary" size="small" @click="save">评论</el-button>
      </div>

      </div>

      <!-- 评论列表 -->
      <div>
        <div v-for="item in comments" :key = "item.id" style="border-bottom: 1px solid #ccc; padding: 10px 0; display: flex">

          <div style="width:100px; text-align: center">

            <el-image :src="item.avatarUrl" style="width: 50px; height: 50px; border-radius: 50%"></el-image>

          </div>

          <div style="flex:1; font-size: 14px; padding: 5px 0; line-height: 25px">

            <b> {{ item.username }} :</b>
            <span> {{ item.content }}</span>

            <div style="display: flex; line-height: 20px; margin-top: 5px">

              <div style="text-align: left">
                <i class="el-icon-time" style="margin-right: 10px"></i><span>{{item.pubTime}}</span>
              </div>

              <div style="text-align: right; flex: 1">
                <el-button type="danger" @click="del(item.id)" v-if="user.id.toString()===item.pubId">删除</el-button>
              </div>

            </div>



          </div>

        </div>
      </div>


    </div>



  </div>
</template>

<script>
export default {
  name: "IdeaDetail",
  data(){
    return {
      idea : {},
      comments: [],
      commentForm:{},
      reId :this.$route.query.id,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load();
    this.loadComment()
  },
  methods : {

    load(){
      this.request.get("http://localhost:9090/idea/" + this.reId)
          .then(res=>{
        if(res.code==='200'){
          this.idea=res.data;
        }
        else{
          this.$message.error('获取创意详情失败！');
        }
      })

    },

    loadComment() {
      this.request.get("http://localhost:9090/comment/tree/" + this.reId)
          .then(res => {
            this.comments = res.data
          })
    },

    save() {
      this.commentForm.reId = this.reId
      this.commentForm.pubId = this.user.id
      console.log(this.commentForm)
      this.request.post("http://localhost:9090/comment/save", this.commentForm)
          .then( res => {
            if (res.code === '200') {
              this.$message.success("评论成功！")
              this.commentForm = {}
              this.loadComment()
            } else {
              this.$message.error(res.msg)
            }
          })
    },

    del(commentId) {
      this.request.delete("http://localhost:9090/comment/delete?id="+commentId)
          .then(res => {
            if ( res.code === '200' ) {
              this.$message.success("删除成功！")
              this.loadComment()
            } else {
              this.$message.error("删除失败！")
            }
          })
    }


  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>