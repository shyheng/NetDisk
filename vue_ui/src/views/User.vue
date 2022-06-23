<template>

  <div class="bg">
    <br><br><br><br>
    <el-card class="box-card" style="background: #FFFFFF66">
      <div>
        <h1>欢迎注册</h1>
        <span>
          已有帐号？<el-link @click="LoginForm = true">登录</el-link>
        </span>
      </div>
      <div style="margin: 20px" />
      <el-form
          label-width="100px"
          style="max-width: 460px"
      >
        <el-form-item label="用户名">
          <el-input v-model="reg_name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="reg_pwd" />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input type="password" v-model="pwd_1" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="reg()">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <br><br><br><br><br><br><br><br><br>
  </div>

  <el-dialog v-model="LoginForm" title="注册" width="30%">
    <el-form label-width="100px" style="max-width: 460px">
      <el-form-item label="用户名" >
        <el-input v-model="name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="pwd" type="password" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="LoginForm = false">取消</el-button>
        <el-button type="primary" @click="login()">登录</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {Vue} from "vue-class-component";
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'

export default class User extends Vue  {
  reg_name = ""
  reg_pwd = ""
  name = ""
  pwd = ""
  pwd_1 = ""
  LoginForm = ref(false)
  reg(){
    if (this.reg_pwd === this.pwd_1){
      this.axios.post('/reg',{
          name:this.reg_name,
          pass:this.reg_pwd
        }).then((response) => {
          if(response.data.flg){
            ElMessage.success(response.data.msg)
          }else {
            ElMessage.error(response.data.msg)
          }

        })
    }else {
      ElMessage.error('密码不统一')
    }
  }
  login(){

    this.axios.post('/login',{
      name:this.name,
      pass:this.pwd
    }).then((response) => {
      if (response.data.flg){
        localStorage.setItem("token",response.data.token)
        ElMessage.success(response.data.msg)
        this.$router.push('/')
      }else {
        ElMessage.error(response.data.msg)
      }
    })
  }
}
</script>

<style scoped>
.bg{
  background-image: url("../assets/3.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.box-card {
  margin-left: 50%;
  margin-top: 5%;
  width: 30%;
}
</style>0
