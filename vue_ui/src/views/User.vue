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
          <el-input v-model="name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="region" />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="region" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <br><br><br><br><br><br><br><br><br>
  </div>

  <el-dialog v-model="LoginForm" title="登录" width="30%">
    <el-form label-width="100px" style="max-width: 460px">
      <el-form-item label="用户名" >
        <el-input v-model="name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="LoginForm = false">取消</el-button>
        <el-button type="primary" @click="">登录</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {Vue} from "vue-class-component";
import { reactive, ref } from 'vue'
export default class User extends Vue  {
  name
  region
  type
  LoginForm = ref(false)

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
