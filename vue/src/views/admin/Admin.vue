<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入ID" suffix-icon="el-icon-search" v-model="adminId"></el-input>
      <el-input style="width: 200px" placeholder="请输入管理员名" suffix-icon="el-icon-message" v-model="adminName" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入手机号" suffix-icon="el-icon-position" v-model="adminMobile" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-position" v-model="adminEmail" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
      <el-table-column prop="id" label="ID" width="120"></el-table-column>
      <el-table-column prop="name" label="用户名" width="140"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="140"></el-table-column>
      <el-table-column prop="mobile" label="电话" width="140"></el-table-column>
      <el-table-column prop="address" label="地址" width="140"></el-table-column>
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
    <el-dialog title="管理员信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="ID">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.adminName" autocomplete="off"></el-input>
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
  </div>
</template>

<script>
export default {
  name: "Admin",
  data(){
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      adminName: "",
      adminId:"",
      adminMobile:"",
      adminEmail:"",
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
      this.request.get("http://localhost:9090/admin/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          id:this.adminId,
          name:this.adminName,
          mobile:this.adminMobile,
          email:this.adminEmail
        }
      }).then(res=>{
       // console.log(res);
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
      this.request.post("http://localhost:9090/admin/insert",this.form).then(res=>{
        if(res.code==='200'){
          this.$message.success("保存成功")
          console.log(res)
          this.dialogFormVisible=false
        }else{
          this.$message.error(res.msg)
        }
      })
    },

    handleAdd(){
      this.dialogFormVisible=true;
      this.form={}
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
