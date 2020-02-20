<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="小屏地址" prop="xpdz">
      <el-input v-model="dataForm.xpdz" placeholder="小屏地址"></el-input>
    </el-form-item>
    <el-form-item label="医生姓名" prop="ysxm">
      <el-input v-model="dataForm.ysxm" placeholder="医生姓名"></el-input>
    </el-form-item>
    <el-form-item label="医生工号" prop="ysgh">
      <el-input v-model="dataForm.ysgh" placeholder="医生工号"></el-input>
    </el-form-item>
    <el-form-item label="房间名称" prop="fjmc">
      <el-input v-model="dataForm.fjmc" placeholder="房间名称"></el-input>
    </el-form-item>
    <el-form-item label="医生格言" prop="ysgy">
      <el-input v-model="dataForm.ysgy" placeholder="医生格言"></el-input>
    </el-form-item>
    <el-form-item label="排队号码" prop="pdhm">
      <el-input v-model="dataForm.pdhm" placeholder="排队号码"></el-input>
    </el-form-item>
    <el-form-item label="病人姓名" prop="brxm">
      <el-input v-model="dataForm.brxm" placeholder="病人姓名"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="修改时间"></el-input>
    </el-form-item>
    <el-form-item label="数据来源（0：第三方；1：本地）" prop="dataSource">
      <el-input v-model="dataForm.dataSource" placeholder="数据来源（0：第三方；1：本地）"></el-input>
    </el-form-item>
    <el-form-item label="状态(0：正常、1：失效)" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态(0：正常、1：失效)"></el-input>
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
          xpdz: '',
          ysxm: '',
          ysgh: '',
          fjmc: '',
          ysgy: '',
          pdhm: '',
          brxm: '',
          createTime: '',
          updateTime: '',
          dataSource: '',
          status: '',
        },
        dataRule: {
          xpdz: [
            { required: true, message: '小屏地址不能为空', trigger: 'blur' }
          ],
          ysxm: [
            { required: true, message: '医生姓名不能为空', trigger: 'blur' }
          ],
          ysgh: [
            { required: true, message: '医生工号不能为空', trigger: 'blur' }
          ],
          fjmc: [
            { required: true, message: '房间名称不能为空', trigger: 'blur' }
          ],
          ysgy: [
            { required: true, message: '医生格言不能为空', trigger: 'blur' }
          ],
          pdhm: [
            { required: true, message: '排队号码不能为空', trigger: 'blur' }
          ],
          brxm: [
            { required: true, message: '病人姓名不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ],
          dataSource: [
            { required: true, message: '数据来源（0：第三方；1：本地）不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态(0：正常、1：失效)不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/queue/jLcdShow/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.xpdz = data.jLcdShow.xpdz
                this.dataForm.ysxm = data.jLcdShow.ysxm
                this.dataForm.ysgh = data.jLcdShow.ysgh
                this.dataForm.fjmc = data.jLcdShow.fjmc
                this.dataForm.ysgy = data.jLcdShow.ysgy
                this.dataForm.pdhm = data.jLcdShow.pdhm
                this.dataForm.brxm = data.jLcdShow.brxm
                this.dataForm.createTime = data.jLcdShow.createTime
                this.dataForm.updateTime = data.jLcdShow.updateTime
                this.dataForm.dataSource = data.jLcdShow.dataSource
                this.dataForm.status = data.jLcdShow.status
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
              url: this.$http.adornUrl(`/queue/jLcdShow/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'xpdz': this.dataForm.xpdz,
                'ysxm': this.dataForm.ysxm,
                'ysgh': this.dataForm.ysgh,
                'fjmc': this.dataForm.fjmc,
                'ysgy': this.dataForm.ysgy,
                'pdhm': this.dataForm.pdhm,
                'brxm': this.dataForm.brxm,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'dataSource': this.dataForm.dataSource,
                'status': this.dataForm.status,
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
