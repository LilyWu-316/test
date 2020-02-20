package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JQhMachine;

/**
 * 取号机表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
public interface IJQhMachineService extends IService<JQhMachine> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams);


}

