package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JRelScreenConfVideoInfoMapper;
import com.annaru.queue.model.JRelScreenConfVideoInfo;
import com.annaru.queue.service.IJRelScreenConfVideoInfoService;


/**
 * screen_conf与video_info的关联表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JRelScreenConfVideoInfoServiceImpl extends ServiceImpl<JRelScreenConfVideoInfoMapper, JRelScreenConfVideoInfo> implements IJRelScreenConfVideoInfoService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JRelScreenConfVideoInfo> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
