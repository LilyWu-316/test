package com.annaru.queue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.model.JRelScreenConfVideoInfo;

/**
 * screen_conf与video_info的关联表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
public interface IJRelScreenConfVideoInfoService extends IService<JRelScreenConfVideoInfo> {

    /**
    * 多表页面信息查询
    * @param pageParams
    * @return
    */
    PageParams selectDataPage(PageParams pageParams);


}

