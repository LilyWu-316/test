package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JSmallScreenTemplateMapper;
import com.annaru.queue.model.JSmallScreenTemplate;
import com.annaru.queue.service.IJSmallScreenTemplateService;


/**
 * 小屏模板表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JSmallScreenTemplateServiceImpl extends ServiceImpl<JSmallScreenTemplateMapper, JSmallScreenTemplate> implements IJSmallScreenTemplateService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JSmallScreenTemplate> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
