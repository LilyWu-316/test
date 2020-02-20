package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JBigScreenTemplate;

/**
 * 大屏模板表
 *
 * @Author jyehui
 * @Date  2020-02-03 10:11:28
 */
public interface IJBigScreenTemplateService extends IService<JBigScreenTemplate> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams, JBigScreenTemplate jBigScreenTemplate);


}

