"use strict";(self["webpackChunkShareIdea_Admin"]=self["webpackChunkShareIdea_Admin"]||[]).push([[235],{9235:function(s,e,o){o.r(e),o.d(e,{default:function(){return l}});var a=function(){var s=this,e=s._self._c;return e("div",{staticStyle:{width:"500px",padding:"20px",border:"1px solid #ccc"}},[e("el-form",{attrs:{"label-width":"80px",size:"small"}},[e("el-form-item",{attrs:{label:"原密码"}},[e("el-input",{attrs:{"show-password":"","auto-complete":"off"},model:{value:s.oldPassword,callback:function(e){s.oldPassword=e},expression:"oldPassword"}})],1),e("el-form-item",{attrs:{label:"新密码"}},[e("el-input",{attrs:{"show-password":"","auto-complete":"off"},model:{value:s.newPassword,callback:function(e){s.newPassword=e},expression:"newPassword"}})],1),e("el-form-item",{attrs:{label:"确认密码"}},[e("el-input",{attrs:{"show-password":"","auto-complete":"off"},model:{value:s.confirmPassword,callback:function(e){s.confirmPassword=e},expression:"confirmPassword"}})],1),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:s.changeAdminPassword}},[s._v("确 定")])],1)],1)],1)},r=[],t=(o(7658),{name:"AdminChangePassword",data(){return{adminDto:{},oldPassword:"",newPassword:"",confirmPassword:"",form:{}}},created(){this.adminDto=JSON.parse(localStorage.getItem("admin")),this.admin==={}&&this.$router.push("/login")},methods:{changeAdminPassword(){this.newPassword===this.confirmPassword?(this.form.id=this.adminDto.id,this.form.oldPassword=this.oldPassword,this.form.newPassword=this.newPassword,this.request.post("/admin/changePassword",this.form).then((s=>{"200"===s.code?(this.$message.success("修改成功"),localStorage.removeItem("admin"),this.$router.push("/AdminLogin")):this.$message.error(s.msg)}))):this.$message.error("两次输入密码不一致")}}}),i=t,d=o(1001),n=(0,d.Z)(i,a,r,!1,null,"6c4fe9af",null),l=n.exports}}]);
//# sourceMappingURL=235.2e6f76e4.js.map