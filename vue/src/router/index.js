import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Mange',
    component: ()=>import('../views/admin/Mange.vue'),
    redirect:"home",
    children:[
      {path:'home', name:'首页', component:()=>import('../views/admin/Home.vue')},
      {path:'person',name:'个人信息',component:()=>import('../views/admin/Person.vue')},
      {path:'changePassword',name:'修改密码',component:()=>import('../views/admin/ChangePassword.vue')},
      {path:'user', name:'用户管理', component:()=>import('../views/admin/User')},
      {path:'admin', name:'管理员管理', component:()=>import('../views/admin/Admin.vue')},
      {path:'idea',name:'Idea管理',component:()=>import('../views/admin/Idea.vue')},
      {path:'comment',name:'Comment管理',component:()=>import('../views/admin/Comment.vue')},
      {path: 'file', name: '文件管理', component: () => import('../views/admin/File.vue')}
    ]
  },
  {
    path: '/about',
    name: 'about',

    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/AdminLogin',
    name:'Login',
    component:()=>import('../views/admin/Login')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath")  // 触发store的数据更新
  next()  // 放行路由
})

export default router
