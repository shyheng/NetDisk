import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)


app.use(ElementPlus)
app.use(ElementPlus, {
    locale: zhCn,
})
app.use(VueAxios, axios)
axios.defaults.baseURL = 'http://127.0.0.1:8010/'
app.use(router).mount('#app')
