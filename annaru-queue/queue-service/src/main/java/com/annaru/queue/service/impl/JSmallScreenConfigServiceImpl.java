package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JSmallScreenConfigMapper;
import com.annaru.queue.model.JSmallScreenConfig;
import com.annaru.queue.service.IJSmallScreenConfigService;


/**
 * 小屏配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JSmallScreenConfigServiceImpl extends ServiceImpl<JSmallScreenConfigMapper, JSmallScreenConfig> implements IJSmallScreenConfigService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JSmallScreenConfig> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
