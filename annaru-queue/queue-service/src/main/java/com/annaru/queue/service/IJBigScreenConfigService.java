package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JBigScreenConfig;

/**
 * 大屏配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
public interface IJBigScreenConfigService extends IService<JBigScreenConfig> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams);


}

