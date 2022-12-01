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



  </div>
</template>

<script>
export default {
  name: "IdeaDetail",
  data(){
    return {
      idea : {},
    }
  },
  created() {
    this.load();
  },
  methods : {

    load(){
      const id = this.$route.query.id
      this.request.get("http://localhost:9090/idea/" + id)
          .then(res=>{
        if(res.code==='200'){
          this.idea=res.data;
        }
        else{
          this.$message.error('获取创意详情失败！');
        }
      })
    },


  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>