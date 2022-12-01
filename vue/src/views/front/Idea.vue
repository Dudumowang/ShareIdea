<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-message" v-model="userid" class="ml-5"></el-input>
      <el-input style="width: 200px; margin-left: 10px" placeholder="请输入标题" suffix-icon="el-icon-position" v-model="ideaTitle" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load" style="margin-left: 10px" size="small">搜索</el-button>
      <el-button class="ml-5" type="info" @click="reset" style="margin-left: 10px" size="small">重置</el-button>
    </div>
<!--    -->
<!--    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">-->
<!--      <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
<!--      <el-table-column prop="uid" label="用户名" width="140"></el-table-column>-->
<!--      <el-table-column prop="title" label="标题"></el-table-column>-->
<!--      <el-table-column prop="type" label="类型"></el-table-column>-->
<!--      <el-table-column prop="pubTime" label="发布时间"></el-table-column>-->
<!--      <el-table-column prop="finTime" label="最后修改时间"></el-table-column>-->
<!--      <el-table-column label="操作"  width="200" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button type="success" @click="handleContent(scope.row)">查看内容 <i class="el-icon-edit"></i></el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

    <div style="margin: 10px 0">

      <div style="padding: 20px 0; border-bottom: 1px dashed #666" v-for="item in tableData" :key="item.id">

        <div style="font-size: 20px; color:#1E90EF;font-weight: bold; cursor:pointer;" @click="$router.push('/front/ideaDetail?id=' + item.id)">
          {{ item.title }}
        </div>

        <div style="font-size: 14px; margin-top: 20px">
          <i class="el-icon-user"></i> <span> {{ item.uid }}</span>
          <i class="el-icon-time" style="margin-left: 10px"></i> <span> {{ item.finTime }}</span>
        </div>

      </div>

    </div>


    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
export default {
  name: "Idea",
  data(){
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 9,
      userid:"",
      ideaTitle:"",
      form:{},
      dialogFormVisible:false,
      deleteDialogFormVisible:false,
      headerBg: 'headerBg'
    }
  },
  created() {
    this.load();
  },
  methods : {
    load(){
      this.request.get("http://localhost:9090/idea/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          userid:this.userid,
          title:this.ideaTitle
        }
      }).then(res=>{
        if(res.code==='200'){
          this.tableData=res.data.data;
          this.total=res.data.total;
        }
        else{
          this.$message.error(res.msg);
        }
      })
    },
    reset(){
      this.userid = ""
      this.ideaTitle = ""
      this.pageNum = 1
      this.pageSize = 10
      this.load()
    },

    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },

    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },

  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>