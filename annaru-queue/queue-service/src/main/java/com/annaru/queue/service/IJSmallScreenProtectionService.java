package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JSmallScreenProtection;

/**
 * 小屏屏保表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
public interface IJSmallScreenProtectionService extends IService<JSmallScreenProtection> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams);


}

