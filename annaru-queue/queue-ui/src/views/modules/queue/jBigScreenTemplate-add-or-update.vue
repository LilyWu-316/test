<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    width="40%"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
    <el-form-item label="模板名称" prop="btemplateName">
      <el-input v-model="dataForm.btemplateName" placeholder="模板名称"></el-input>
    </el-form-item>
    <el-form-item label="模板图" prop="btemplatePicture">
      <el-upload class="avatar-uploader" :action="this.$http.adornUrl('/api/fileUpload')" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
        <img v-if="dataForm.btemplatePicture" :src="this.$http.adornUrl(dataForm.btemplatePicture)" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="模板状态" prop="status">
      <el-radio-group v-model="dataForm.status">
        <el-radio :label=1>禁用</el-radio>
        <el-radio :label=0>启用</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="是否删除" prop="isDelete">
      <el-radio-group v-model="dataForm.isDelete">
        <el-radio :label=1>已删除</el-radio>
        <el-radio :label=0>未删除</el-radio>
      </el-radio-group>
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
          btemplateName: '',
          btemplatePicture: '',
          status: 1,
          isDelete: 0
        },
        dataRule: {
          btemplateName: [
            { required: true, message: '模板名称不能为空', trigger: 'blur' }
          ],
          btemplatePicture: [
            { required: true, message: '模板图不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '模板状态（0：启用；1禁用）不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除（0：未删除；1：已删除；）不能为空', trigger: 'blur' }
          ]
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
              url: this.$http.adornUrl(`/queue/jBigScreenTemplate/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.btemplateName = data.jBigScreenTemplate.btemplateName
                this.dataForm.btemplatePicture = data.jBigScreenTemplate.btemplatePicture
                this.dataForm.status = data.jBigScreenTemplate.status
                this.dataForm.isDelete = data.jBigScreenTemplate.isDelete
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
              url: this.$http.adornUrl(`/queue/jBigScreenTemplate/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'btemplateName': this.dataForm.btemplateName,
                'btemplatePicture': this.dataForm.btemplatePicture,
                'status': this.dataForm.status,
                'isDelete': this.dataForm.isDelete
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
      },
        handleAvatarSuccess (res, file) {
            console.log(res, file)
            if (res && res.code === 0) {
                this.$message.success('图片上传成功')
                this.dataForm.btemplatePicture = res.filename
            } else {
                this.$message.error('图片上传失败')
            }
        },
        beforeAvatarUpload (file) {
            const isJPG = file.type === 'image/jpeg'
            const isGIF = file.type === 'image/gif'
            const isPNG = file.type === 'image/png'
            const isBMP = file.type === 'image/bmp'
            const isLt2M = file.size / 1024 / 1024 < 2

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!')
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M
        }
    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
