package com.annaru.queue.mapper;

import com.annaru.queue.model.JBigScreenTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @Description 大屏模板表
 * @Author jyehui
 * @Date  2020-02-03 10:11:28
 */
public interface JBigScreenTemplateMapper extends BaseMapper<JBigScreenTemplate> {

    /**
    * 多表页面信息查询
    * @param page
    * @return
    */
    IPage<JBigScreenTemplate> selectDataPage(Page page,@Param("jBigScreenTemplate")JBigScreenTemplate jBigScreenTemplate);

}
