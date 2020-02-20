package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JBigScreenConfigMapper;
import com.annaru.queue.model.JBigScreenConfig;
import com.annaru.queue.service.IJBigScreenConfigService;


/**
 * 大屏配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
@Service
public class JBigScreenConfigServiceImpl extends ServiceImpl<JBigScreenConfigMapper, JBigScreenConfig> implements IJBigScreenConfigService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JBigScreenConfig> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
