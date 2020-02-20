package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JScreenContentMapper;
import com.annaru.queue.model.JScreenContent;
import com.annaru.queue.service.IJScreenContentService;


/**
 * 提示内容表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JScreenContentServiceImpl extends ServiceImpl<JScreenContentMapper, JScreenContent> implements IJScreenContentService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JScreenContent> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
