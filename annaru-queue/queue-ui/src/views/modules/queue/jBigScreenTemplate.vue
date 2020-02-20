<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="模板名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.isDelete" placeholder="删除状态" >
          <el-option v-for="item in [{label: '未删除', value: 0},{label: '已删除', value: 1}]" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList(1)">查询</el-button>
        <el-button v-if="isAuth('queue/jBigScreenTemplate/save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="50"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="btemplateName"
        header-align="center"
        align="center"
        label="模板名称">
      </el-table-column>
      <el-table-column
        prop="btemplatePicture"
        header-align="center"
        align="center"
        label="模板图">
        <template slot-scope="scope">
          <el-popover placement="top" title=""  trigger="click">
            <img :src="fileUploadServer + scope.row.btemplatePicture" alt="加载失败" style="max-height: 768px;max-width: 1024px"/>
            <img slot="reference" :src="fileUploadServer + scope.row.btemplatePicture" alt="加载失败" style="max-height: 50px;max-width: 100px">
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="模板状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" size="small" type="success">已启用</el-tag>
          <el-tag v-else-if="scope.row.status === 1" size="small" type="danger">已禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        label="修改时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
            <el-button v-if="isAuth('queue/jBigScreenTemplate/disableOrEnabled')" type="text" size="small" @click="disableOrEnabled(scope.row.id, scope.row.status===1 ? '启用':'禁用')"><i :class="scope.row.status===1 ? 'el-icon-circle-check':'el-icon-circle-close'">{{ scope.row.status===1 ? '启用':'禁用' }}</i></el-button>
            <el-button v-if="isAuth('queue/jBigScreenTemplate/update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)"><i class="el-icon-edit"></i>修改</el-button>
            <el-button v-if="isAuth('queue/jBigScreenTemplate/deleteOrRecover')" type="text" size="small" @click="deleteOrRecover(scope.row.id, scope.row.isDelete===1 ? '恢复':'删除')"><i :class="scope.row.isDelete===1 ? 'el-icon-plus':'el-icon-delete'">{{ scope.row.isDelete===1 ? '恢复':'删除' }}</i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './jBigScreenTemplate-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: '',
          isDelete: 0
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        fileUploadServer: this.$http.adornUrl('')
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList (pageIndex) {
        if (pageIndex) {
          this.pageIndex = pageIndex
        }
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/queue/jBigScreenTemplate/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'bTemplateName': this.dataForm.key,
            'isDelete': this.dataForm.isDelete
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
        deleteOrRecover (id, name) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行【${name}】操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/queue/jBigScreenTemplate/deleteOrRecover'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
        // 禁用或启用
        disableOrEnabled (id, name) {
            var ids = id ? [id] : this.dataListSelections.map(item => {
                return item.id
            })
            this.$confirm(`确定对[ID=${ids.join(',')}]进行【${name}】操作?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http({
                    url: this.$http.adornUrl('/queue/jBigScreenTemplate/disableOrEnabled'),
                    method: 'post',
                    data: this.$http.adornData(ids, false)
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                            duration: 1500,
                            onClose: () => {
                                this.getDataList()
                            }
                        })
                    } else {
                        this.$message.error(data.msg)
                    }
                })
            })
        }
    }
  }
</script>
