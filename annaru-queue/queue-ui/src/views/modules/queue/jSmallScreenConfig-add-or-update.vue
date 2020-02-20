<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="小屏编号" prop="sScreenNo">
      <el-input v-model="dataForm.sScreenNo" placeholder="小屏编号"></el-input>
    </el-form-item>
    <el-form-item label="小屏ip" prop="sScreenIp">
      <el-input v-model="dataForm.sScreenIp" placeholder="小屏ip"></el-input>
    </el-form-item>
    <el-form-item label="小屏名称" prop="sScreenName">
      <el-input v-model="dataForm.sScreenName" placeholder="小屏名称"></el-input>
    </el-form-item>
    <el-form-item label="小屏模板Id(small_screen_template表中id)" prop="sstId">
      <el-input v-model="dataForm.sstId" placeholder="小屏模板Id(small_screen_template表中id)"></el-input>
    </el-form-item>
    <el-form-item label="小屏屏保id(small_screen_protection表中id)" prop="sspId">
      <el-input v-model="dataForm.sspId" placeholder="小屏屏保id(small_screen_protection表中id)"></el-input>
    </el-form-item>
    <el-form-item label="屏保状态（0：启用；1禁用；）" prop="protectionStatus">
      <el-input v-model="dataForm.protectionStatus" placeholder="屏保状态（0：启用；1禁用；）"></el-input>
    </el-form-item>
    <el-form-item label="叫号声音状态（0：启用；1：禁用；）" prop="voiceStatus">
      <el-input v-model="dataForm.voiceStatus" placeholder="叫号声音状态（0：启用；1：禁用；）"></el-input>
    </el-form-item>
    <el-form-item label="视频状态（0：启用；1：禁用；）" prop="videoStatus">
      <el-input v-model="dataForm.videoStatus" placeholder="视频状态（0：启用；1：禁用；）"></el-input>
    </el-form-item>
    <el-form-item label="视频id(video_info表中id)" prop="videoInfoId">
      <el-input v-model="dataForm.videoInfoId" placeholder="视频id(video_info表中id)"></el-input>
    </el-form-item>
    <el-form-item label="屏幕提示内容Id(screen_content表中id)" prop="screenContentId">
      <el-input v-model="dataForm.screenContentId" placeholder="屏幕提示内容Id(screen_content表中id)"></el-input>
    </el-form-item>
    <el-form-item label="小屏logo" prop="sScreenLogo">
      <el-input v-model="dataForm.sScreenLogo" placeholder="小屏logo"></el-input>
    </el-form-item>
    <el-form-item label="小屏抬头" prop="sScreenTitle">
      <el-input v-model="dataForm.sScreenTitle" placeholder="小屏抬头"></el-input>
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
          sScreenNo: '',
          sScreenIp: '',
          sScreenName: '',
          sstId: '',
          sspId: '',
          protectionStatus: '',
          voiceStatus: '',
          videoStatus: '',
          videoInfoId: '',
          screenContentId: '',
          sScreenLogo: '',
          sScreenTitle: '',
          institutionName: '',
          isDelete: '',
          createTime: '',
          updateTime: '',
        },
        dataRule: {
          sScreenNo: [
            { required: true, message: '小屏编号不能为空', trigger: 'blur' }
          ],
          sScreenIp: [
            { required: true, message: '小屏ip不能为空', trigger: 'blur' }
          ],
          sScreenName: [
            { required: true, message: '小屏名称不能为空', trigger: 'blur' }
          ],
          sstId: [
            { required: true, message: '小屏模板Id(small_screen_template表中id)不能为空', trigger: 'blur' }
          ],
          sspId: [
            { required: true, message: '小屏屏保id(small_screen_protection表中id)不能为空', trigger: 'blur' }
          ],
          protectionStatus: [
            { required: true, message: '屏保状态（0：启用；1禁用；）不能为空', trigger: 'blur' }
          ],
          voiceStatus: [
            { required: true, message: '叫号声音状态（0：启用；1：禁用；）不能为空', trigger: 'blur' }
          ],
          videoStatus: [
            { required: true, message: '视频状态（0：启用；1：禁用；）不能为空', trigger: 'blur' }
          ],
          videoInfoId: [
            { required: true, message: '视频id(video_info表中id)不能为空', trigger: 'blur' }
          ],
          screenContentId: [
            { required: true, message: '屏幕提示内容Id(screen_content表中id)不能为空', trigger: 'blur' }
          ],
          sScreenLogo: [
            { required: true, message: '小屏logo不能为空', trigger: 'blur' }
          ],
          sScreenTitle: [
            { required: true, message: '小屏抬头不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/queue/jSmallScreenConfig/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sScreenNo = data.jSmallScreenConfig.sScreenNo
                this.dataForm.sScreenIp = data.jSmallScreenConfig.sScreenIp
                this.dataForm.sScreenName = data.jSmallScreenConfig.sScreenName
                this.dataForm.sstId = data.jSmallScreenConfig.sstId
                this.dataForm.sspId = data.jSmallScreenConfig.sspId
                this.dataForm.protectionStatus = data.jSmallScreenConfig.protectionStatus
                this.dataForm.voiceStatus = data.jSmallScreenConfig.voiceStatus
                this.dataForm.videoStatus = data.jSmallScreenConfig.videoStatus
                this.dataForm.videoInfoId = data.jSmallScreenConfig.videoInfoId
                this.dataForm.screenContentId = data.jSmallScreenConfig.screenContentId
                this.dataForm.sScreenLogo = data.jSmallScreenConfig.sScreenLogo
                this.dataForm.sScreenTitle = data.jSmallScreenConfig.sScreenTitle
                this.dataForm.institutionName = data.jSmallScreenConfig.institutionName
                this.dataForm.isDelete = data.jSmallScreenConfig.isDelete
                this.dataForm.createTime = data.jSmallScreenConfig.createTime
                this.dataForm.updateTime = data.jSmallScreenConfig.updateTime
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
              url: this.$http.adornUrl(`/queue/jSmallScreenConfig/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sScreenNo': this.dataForm.sScreenNo,
                'sScreenIp': this.dataForm.sScreenIp,
                'sScreenName': this.dataForm.sScreenName,
                'sstId': this.dataForm.sstId,
                'sspId': this.dataForm.sspId,
                'protectionStatus': this.dataForm.protectionStatus,
                'voiceStatus': this.dataForm.voiceStatus,
                'videoStatus': this.dataForm.videoStatus,
                'videoInfoId': this.dataForm.videoInfoId,
                'screenContentId': this.dataForm.screenContentId,
                'sScreenLogo': this.dataForm.sScreenLogo,
                'sScreenTitle': this.dataForm.sScreenTitle,
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
