<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="屏幕编号" prop="screenNo">
      <el-input v-model="dataForm.screenNo" placeholder="屏幕编号"></el-input>
    </el-form-item>
    <el-form-item label="屏幕名称" prop="screenName">
      <el-input v-model="dataForm.screenName" placeholder="屏幕名称"></el-input>
    </el-form-item>
    <el-form-item label="屏幕ip" prop="screenIp">
      <el-input v-model="dataForm.screenIp" placeholder="屏幕ip"></el-input>
    </el-form-item>
    <el-form-item label="屏幕文字内容" prop="screenText">
      <el-input v-model="dataForm.screenText" placeholder="屏幕文字内容"></el-input>
    </el-form-item>
    <el-form-item label="屏幕图片" prop="screenPicture">
      <el-input v-model="dataForm.screenPicture" placeholder="屏幕图片"></el-input>
    </el-form-item>
    <el-form-item label="屏幕状态（0：启用；1：禁用；）" prop="screenStatus">
      <el-input v-model="dataForm.screenStatus" placeholder="屏幕状态（0：启用；1：禁用；）"></el-input>
    </el-form-item>
    <el-form-item label="是否删除（0：未删除；1：已删除；）" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除（0：未删除；1：已删除；）"></el-input>
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
          screenNo: '',
          screenName: '',
          screenIp: '',
          screenText: '',
          screenPicture: '',
          screenStatus: '',
          isDelete: '',
          createTime: '',
          updateTime: '',
        },
        dataRule: {
          screenNo: [
            { required: true, message: '屏幕编号不能为空', trigger: 'blur' }
          ],
          screenName: [
            { required: true, message: '屏幕名称不能为空', trigger: 'blur' }
          ],
          screenIp: [
            { required: true, message: '屏幕ip不能为空', trigger: 'blur' }
          ],
          screenText: [
            { required: true, message: '屏幕文字内容不能为空', trigger: 'blur' }
          ],
          screenPicture: [
            { required: true, message: '屏幕图片不能为空', trigger: 'blur' }
          ],
          screenStatus: [
            { required: true, message: '屏幕状态（0：启用；1：禁用；）不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除（0：未删除；1：已删除；）不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/queue/jScreenConfig/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.screenNo = data.jScreenConfig.screenNo
                this.dataForm.screenName = data.jScreenConfig.screenName
                this.dataForm.screenIp = data.jScreenConfig.screenIp
                this.dataForm.screenText = data.jScreenConfig.screenText
                this.dataForm.screenPicture = data.jScreenConfig.screenPicture
                this.dataForm.screenStatus = data.jScreenConfig.screenStatus
                this.dataForm.isDelete = data.jScreenConfig.isDelete
                this.dataForm.createTime = data.jScreenConfig.createTime
                this.dataForm.updateTime = data.jScreenConfig.updateTime
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
              url: this.$http.adornUrl(`/queue/jScreenConfig/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'screenNo': this.dataForm.screenNo,
                'screenName': this.dataForm.screenName,
                'screenIp': this.dataForm.screenIp,
                'screenText': this.dataForm.screenText,
                'screenPicture': this.dataForm.screenPicture,
                'screenStatus': this.dataForm.screenStatus,
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
