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
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="ID">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model:label="form.password" auto-complete="off"></el-input>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible1" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model:label="form.password" auto-complete="off"></el-input>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelUpdate">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="是否删除" :visible.sync="deleteDialogFormVisible" width="30%">
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="deleteUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
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
      this.request.get("http://localhost:9090/user/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          id:this.userid,
          username:this.username,
          mobile:this.userMobile,
          email:this.userEmail
        }
      }).then(res=>{

        if(res.code==='200'){
          console.log(res)
          this.tableData=res.data.data;
          this.total=res.data.total;
        }
        else{
          this.$message.error(res.msg);
        }
      })
    },
    save(){
      console.log(this.form);
      this.request.post("http://localhost:9090/user/insert",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("保存成功")
          console.log(res)
          this.dialogFormVisible=false
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    update(){
      console.log(this.form);
      this.request.post("http://localhost:9090/user/adminUpdate",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("修改成功")
          console.log(res)
          this.dialogFormVisible1=false
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    cancelUpdate(){
      this.dialogFormVisible1=false
      this.load();
    },
    deleteUser(){
      console.log(this.form);
      this.request.post("http://localhost:9090/user/delete",this.form).then(res=>{
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
    handleAdd(){
      this.dialogFormVisible=true;
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible1=true;
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
