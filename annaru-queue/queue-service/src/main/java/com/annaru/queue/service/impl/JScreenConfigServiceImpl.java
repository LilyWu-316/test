package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JScreenConfigMapper;
import com.annaru.queue.model.JScreenConfig;
import com.annaru.queue.service.IJScreenConfigService;


/**
 * 屏幕信息配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JScreenConfigServiceImpl extends ServiceImpl<JScreenConfigMapper, JScreenConfig> implements IJScreenConfigService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JScreenConfig> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
