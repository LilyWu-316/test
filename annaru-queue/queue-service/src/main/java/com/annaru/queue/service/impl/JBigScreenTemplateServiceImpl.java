package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JBigScreenTemplateMapper;
import com.annaru.queue.model.JBigScreenTemplate;
import com.annaru.queue.service.IJBigScreenTemplateService;


/**
 * 大屏模板表
 *
 * @Author jyehui
 * @Date  2020-02-03 10:11:28
 */
@Service
public class JBigScreenTemplateServiceImpl extends ServiceImpl<JBigScreenTemplateMapper, JBigScreenTemplate> implements IJBigScreenTemplateService {

    @Override
    public PageParams selectDataPage(PageParams pageParams, JBigScreenTemplate jBigScreenTemplate){
        IPage<JBigScreenTemplate> iPage = this.baseMapper.selectDataPage(pageParams, jBigScreenTemplate);
        return new PageParams(iPage);
    }

}
