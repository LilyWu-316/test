package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JTitle;

/**
 * 职称表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
public interface IJTitleService extends IService<JTitle> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams);


}

