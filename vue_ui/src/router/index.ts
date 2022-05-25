import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import User from '../views/User.vue'
import Index from '../views/Index.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: User
  },
  {
    path:'/s',
    name:'index',
    component: Index
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
