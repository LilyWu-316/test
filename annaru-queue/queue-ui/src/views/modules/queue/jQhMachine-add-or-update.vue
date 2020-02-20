<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="科目ID" prop="subjectId">
      <el-input v-model="dataForm.subjectId" placeholder="科目ID"></el-input>
    </el-form-item>
    <el-form-item label="取号机编号" prop="machineNo">
      <el-input v-model="dataForm.machineNo" placeholder="取号机编号"></el-input>
    </el-form-item>
    <el-form-item label="取号机ip" prop="machineIp">
      <el-input v-model="dataForm.machineIp" placeholder="取号机ip"></el-input>
    </el-form-item>
    <el-form-item label="取号机名称" prop="machineName">
      <el-input v-model="dataForm.machineName" placeholder="取号机名称"></el-input>
    </el-form-item>
    <el-form-item label="取号机开放状态（0：关闭；1开放；2：强制开放）" prop="machineOpenStatus">
      <el-input v-model="dataForm.machineOpenStatus" placeholder="取号机开放状态（0：关闭；1开放；2：强制开放）"></el-input>
    </el-form-item>
    <el-form-item label="开放时间段" prop="openHours">
      <el-input v-model="dataForm.openHours" placeholder="开放时间段"></el-input>
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
          subjectId: '',
          machineNo: '',
          machineIp: '',
          machineName: '',
          machineOpenStatus: '',
          openHours: '',
          isDelete: '',
          createTime: '',
          updateTime: '',
        },
        dataRule: {
          subjectId: [
            { required: true, message: '科目ID不能为空', trigger: 'blur' }
          ],
          machineNo: [
            { required: true, message: '取号机编号不能为空', trigger: 'blur' }
          ],
          machineIp: [
            { required: true, message: '取号机ip不能为空', trigger: 'blur' }
          ],
          machineName: [
            { required: true, message: '取号机名称不能为空', trigger: 'blur' }
          ],
          machineOpenStatus: [
            { required: true, message: '取号机开放状态（0：关闭；1开放；2：强制开放）不能为空', trigger: 'blur' }
          ],
          openHours: [
            { required: true, message: '开放时间段不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/queue/jQhMachine/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.subjectId = data.jQhMachine.subjectId
                this.dataForm.machineNo = data.jQhMachine.machineNo
                this.dataForm.machineIp = data.jQhMachine.machineIp
                this.dataForm.machineName = data.jQhMachine.machineName
                this.dataForm.machineOpenStatus = data.jQhMachine.machineOpenStatus
                this.dataForm.openHours = data.jQhMachine.openHours
                this.dataForm.isDelete = data.jQhMachine.isDelete
                this.dataForm.createTime = data.jQhMachine.createTime
                this.dataForm.updateTime = data.jQhMachine.updateTime
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
              url: this.$http.adornUrl(`/queue/jQhMachine/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'subjectId': this.dataForm.subjectId,
                'machineNo': this.dataForm.machineNo,
                'machineIp': this.dataForm.machineIp,
                'machineName': this.dataForm.machineName,
                'machineOpenStatus': this.dataForm.machineOpenStatus,
                'openHours': this.dataForm.openHours,
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
