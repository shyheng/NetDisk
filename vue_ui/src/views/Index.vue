<template>

  <div v-if="token">
    <h4>
      <el-link :underline="false" @click="LoginForm = true">请登录</el-link>
      <router-link to="/reg">没有账户，点击注册</router-link>
    </h4>
  </div>
  <div v-else>
    <el-upload
        class="upload-demo"
        drag
        action="http://127.0.0.1:8010/file"
        :data="{'token':id}"

    >
      <el-icon class="el-icon--upload">
        <upload-filled/>
      </el-icon>
      <div class="el-upload__text">
        文件可拖到此处 <em>点击上传</em>
      </div>
    </el-upload>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed prop="fileName" label="文件名"/>
      <el-table-column prop="date" label="上传时间"/>
      <el-table-column prop="size" label="文件大小"/>
      <el-table-column prop="type" label="文件类型"/>
      <el-table-column fixed="right" label="操作">
        <template #default="scope">

          <el-button text size="small" ><a :href="'http://127.0.0.1:8010/dow?id='+this.tableData[scope.$index].id">下载</a></el-button>
          <el-button text size="small" @click="del(scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="LoginForm" title="登录" width="30%">
    <el-form label-width="100px" style="max-width: 460px">
      <el-form-item label="用户名">
        <el-input v-model="name" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="pwd" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="LoginForm = false">取消</el-button>
        <el-button type="primary" @click="login">登录</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {Vue} from "vue-class-component";
import {ref} from "vue";
import {ElMessage} from "element-plus";

export default class Index extends Vue {
  name = ""
  pwd = ""
  token
  id

  beforeCreate() {
    let token = localStorage.getItem("token");
    if (token === null) {
      this.token = true
    } else {
      this.id = token
      this.token = false
      this.axios.get('/sel', {
        params: {
          id: token
        }
      }).then((response) => {
        this.tableData = response.data
      })
    }

  }
  del(i){
    this.axios.post('/del', {
      id:this.tableData[i].id,
    }).then((response) => {
      location.reload()
    })
  }

  login() {
    this.axios.post('/login', {
      name: this.name,
      pass: this.pwd
    }).then((response) => {
      if (response.data.flg) {
        localStorage.setItem("token", response.data.token)
        ElMessage.success(response.data.msg)
        location.reload()
      } else {
        ElMessage.error(response.data.msg)
      }
    })
  }

  LoginForm = ref(false)
  tableData = []
}
</script>

<style scoped>

</style>
