<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="姓名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="照片" prop="headPicture">
      <el-input v-model="dataForm.headPicture" placeholder="照片"></el-input>
    </el-form-item>
    <el-form-item label="职称(title表中的id)" prop="titleId">
      <el-input v-model="dataForm.titleId" placeholder="职称(title表中的id)"></el-input>
    </el-form-item>
    <el-form-item label="简介" prop="introduction">
      <el-input v-model="dataForm.introduction" placeholder="简介"></el-input>
    </el-form-item>
    <el-form-item label="工号" prop="jobNumber">
      <el-input v-model="dataForm.jobNumber" placeholder="工号"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="createUser">
      <el-input v-model="dataForm.createUser" placeholder="创建人"></el-input>
    </el-form-item>
    <el-form-item label="是否删除（0：未删除；1：已删除）" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除（0：未删除；1：已删除）"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userName: '',
          headPicture: '',
          titleId: '',
          introduction: '',
          jobNumber: '',
          createUser: '',
          isDelete: '',
          createTime: '',
          updateTime: '',
        },
        dataRule: {
          userName: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          headPicture: [
            { required: true, message: '照片不能为空', trigger: 'blur' }
          ],
          titleId: [
            { required: true, message: '职称(title表中的id)不能为空', trigger: 'blur' }
          ],
          introduction: [
            { required: true, message: '简介不能为空', trigger: 'blur' }
          ],
          jobNumber: [
            { required: true, message: '工号不能为空', trigger: 'blur' }
          ],
          createUser: [
            { required: true, message: '创建人不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除（0：未删除；1：已删除）不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/queue/jUserInfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.jUserInfo.userName
                this.dataForm.headPicture = data.jUserInfo.headPicture
                this.dataForm.titleId = data.jUserInfo.titleId
                this.dataForm.introduction = data.jUserInfo.introduction
                this.dataForm.jobNumber = data.jUserInfo.jobNumber
                this.dataForm.createUser = data.jUserInfo.createUser
                this.dataForm.isDelete = data.jUserInfo.isDelete
                this.dataForm.createTime = data.jUserInfo.createTime
                this.dataForm.updateTime = data.jUserInfo.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/queue/jUserInfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userName': this.dataForm.userName,
                'headPicture': this.dataForm.headPicture,
                'titleId': this.dataForm.titleId,
                'introduction': this.dataForm.introduction,
                'jobNumber': this.dataForm.jobNumber,
                'createUser': this.dataForm.createUser,
                'isDelete': this.dataForm.isDelete,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
