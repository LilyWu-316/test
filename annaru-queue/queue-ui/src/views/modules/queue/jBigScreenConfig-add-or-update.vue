<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="大屏编号" prop="bScreenNo">
      <el-input v-model="dataForm.bScreenNo" placeholder="大屏编号"></el-input>
    </el-form-item>
    <el-form-item label="大屏ip" prop="bScreenIp">
      <el-input v-model="dataForm.bScreenIp" placeholder="大屏ip"></el-input>
    </el-form-item>
    <el-form-item label="小屏编号(small_screen_config中s_screen_no)" prop="sScreenId">
      <el-input v-model="dataForm.sScreenId" placeholder="小屏编号(small_screen_config中s_screen_no)"></el-input>
    </el-form-item>
    <el-form-item label="大屏名称" prop="bScreenName">
      <el-input v-model="dataForm.bScreenName" placeholder="大屏名称"></el-input>
    </el-form-item>
    <el-form-item label="大屏模板Id(big_screen_template表中id)" prop="bstId">
      <el-input v-model="dataForm.bstId" placeholder="大屏模板Id(big_screen_template表中id)"></el-input>
    </el-form-item>
    <el-form-item label="行数" prop="lineNumber">
      <el-input v-model="dataForm.lineNumber" placeholder="行数"></el-input>
    </el-form-item>
    <el-form-item label="屏幕提示内容Id(screen_content表中id)" prop="bcreenContentId">
      <el-input v-model="dataForm.bcreenContentId" placeholder="屏幕提示内容Id(screen_content表中id)"></el-input>
    </el-form-item>
    <el-form-item label="大屏logo" prop="bScreenLogo">
      <el-input v-model="dataForm.bScreenLogo" placeholder="大屏logo"></el-input>
    </el-form-item>
    <el-form-item label="大屏抬头" prop="bScreenTitle">
      <el-input v-model="dataForm.bScreenTitle" placeholder="大屏抬头"></el-input>
    </el-form-item>
    <el-form-item label="机构名称" prop="institutionName">
      <el-input v-model="dataForm.institutionName" placeholder="机构名称"></el-input>
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
          bScreenNo: '',
          bScreenIp: '',
          sScreenId: '',
          bScreenName: '',
          bstId: '',
          lineNumber: '',
          bcreenContentId: '',
          bScreenLogo: '',
          bScreenTitle: '',
          institutionName: '',
          isDelete: '',
          createTime: '',
          updateTime: '',
        },
        dataRule: {
          bScreenNo: [
            { required: true, message: '大屏编号不能为空', trigger: 'blur' }
          ],
          bScreenIp: [
            { required: true, message: '大屏ip不能为空', trigger: 'blur' }
          ],
          sScreenId: [
            { required: true, message: '小屏编号(small_screen_config中s_screen_no)不能为空', trigger: 'blur' }
          ],
          bScreenName: [
            { required: true, message: '大屏名称不能为空', trigger: 'blur' }
          ],
          bstId: [
            { required: true, message: '大屏模板Id(big_screen_template表中id)不能为空', trigger: 'blur' }
          ],
          lineNumber: [
            { required: true, message: '行数不能为空', trigger: 'blur' }
          ],
          bcreenContentId: [
            { required: true, message: '屏幕提示内容Id(screen_content表中id)不能为空', trigger: 'blur' }
          ],
          bScreenLogo: [
            { required: true, message: '大屏logo不能为空', trigger: 'blur' }
          ],
          bScreenTitle: [
            { required: true, message: '大屏抬头不能为空', trigger: 'blur' }
          ],
          institutionName: [
            { required: true, message: '机构名称不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/queue/jBigScreenConfig/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.bScreenNo = data.jBigScreenConfig.bScreenNo
                this.dataForm.bScreenIp = data.jBigScreenConfig.bScreenIp
                this.dataForm.sScreenId = data.jBigScreenConfig.sScreenId
                this.dataForm.bScreenName = data.jBigScreenConfig.bScreenName
                this.dataForm.bstId = data.jBigScreenConfig.bstId
                this.dataForm.lineNumber = data.jBigScreenConfig.lineNumber
                this.dataForm.bcreenContentId = data.jBigScreenConfig.bcreenContentId
                this.dataForm.bScreenLogo = data.jBigScreenConfig.bScreenLogo
                this.dataForm.bScreenTitle = data.jBigScreenConfig.bScreenTitle
                this.dataForm.institutionName = data.jBigScreenConfig.institutionName
                this.dataForm.isDelete = data.jBigScreenConfig.isDelete
                this.dataForm.createTime = data.jBigScreenConfig.createTime
                this.dataForm.updateTime = data.jBigScreenConfig.updateTime
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
              url: this.$http.adornUrl(`/queue/jBigScreenConfig/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'bScreenNo': this.dataForm.bScreenNo,
                'bScreenIp': this.dataForm.bScreenIp,
                'sScreenId': this.dataForm.sScreenId,
                'bScreenName': this.dataForm.bScreenName,
                'bstId': this.dataForm.bstId,
                'lineNumber': this.dataForm.lineNumber,
                'bcreenContentId': this.dataForm.bcreenContentId,
                'bScreenLogo': this.dataForm.bScreenLogo,
                'bScreenTitle': this.dataForm.bScreenTitle,
                'institutionName': this.dataForm.institutionName,
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
