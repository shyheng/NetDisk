<template>
  <div></div>
    <el-upload
        class="upload-demo"
        drag
        action="http://127.0.0.1:8010/test"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        文件可拖到此处 <em>点击上传</em>
      </div>
    </el-upload>
</template>

<script>
import {Vue} from "vue-class-component";
export default class Index extends Vue{

}
</script>

<style scoped>

</style>
