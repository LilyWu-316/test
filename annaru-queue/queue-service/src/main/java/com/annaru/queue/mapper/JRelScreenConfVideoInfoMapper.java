package com.annaru.queue.mapper;

import com.annaru.queue.model.JRelScreenConfVideoInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @Description screen_conf与video_info的关联表
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
public interface JRelScreenConfVideoInfoMapper extends BaseMapper<JRelScreenConfVideoInfo> {

    /**
    * 多表页面信息查询
    * @param page
    * @return
    */
    IPage<JRelScreenConfVideoInfo> selectDataPage(Page page);

}
