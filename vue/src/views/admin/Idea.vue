<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入ID" suffix-icon="el-icon-search" v-model="userid"></el-input>
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-message" v-model="username" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入手机号" suffix-icon="el-icon-position" v-model="userMobile" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-position" v-model="userEmail" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger">删除 <i class="el-icon-remove-outline"></i></el-button>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="uid" label="用户名" width="140"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="type" label="类型"></el-table-column>
      <el-table-column prop="pubTime" label="发布时间"></el-table-column>
      <el-table-column prop="finTime" label="最后修改时间"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleContent(scope.row)">查看内容 <i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="handleDelete(scope.row)">删除 <i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="50%">
      <el-form label-width="80px">
        <el-form-item label="内容">
          <el-input type="textarea" :autosize="{ minRows:8, maxRows:16}" v-model="form.content" autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
    <el-dialog title="是否删除" :visible.sync="deleteDialogFormVisible" width="30%">
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="deleteIdea">确 定</el-button>
      </div>
    </el-dialog>
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
      pageSize: 10,
      username: "",
      userid:"",
      userMobile:"",
      userEmail:"",
      form:{},
      dialogFormVisible:false,
      dialogFormVisible1:false,
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
        }
      }).then(res=>{

        if(res.code==='200'){
          console.log(res)
          this.tableData=res.data.data;
          console.log(this.tableData)
          this.total=res.data.total;
        }
        else{
          this.$message.error(res.msg);
        }
      })
    },
    deleteIdea(){
      console.log(this.form);
      this.request.get("http://localhost:9090/idea/delete",{
        params:{
          id:this.form.id
        }
      }).then(res=>{
        if(res.code==='200'){
          this.$message.success("删除成功")
          console.log(res)
          this.deleteDialogFormVisible=false
          this.load();
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    handleContent(row){
      this.form=row
      this.dialogFormVisible=true;
    },
    handleDelete(row){
      this.form=row
      this.deleteDialogFormVisible=true;
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
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
