package com.annaru.queue.mapper;

import com.annaru.queue.model.JBigScreenConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @Description 大屏配置表
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
public interface JBigScreenConfigMapper extends BaseMapper<JBigScreenConfig> {

    /**
    * 多表页面信息查询
    * @param page
    * @return
    */
    IPage<JBigScreenConfig> selectDataPage(Page page);

}
