package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JVideoInfoMapper;
import com.annaru.queue.model.JVideoInfo;
import com.annaru.queue.service.IJVideoInfoService;


/**
 * 视频表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
@Service
public class JVideoInfoServiceImpl extends ServiceImpl<JVideoInfoMapper, JVideoInfo> implements IJVideoInfoService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JVideoInfo> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
