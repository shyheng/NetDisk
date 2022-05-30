import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import Index from '../views/Index.vue'
import User from '../views/User.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/reg',
    component:User
  },
  {
    name:'Index',
    path:'/',
    component: Index
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})


export default router
