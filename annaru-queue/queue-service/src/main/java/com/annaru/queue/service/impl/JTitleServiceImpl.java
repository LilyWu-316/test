package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JTitleMapper;
import com.annaru.queue.model.JTitle;
import com.annaru.queue.service.IJTitleService;


/**
 * 职称表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JTitleServiceImpl extends ServiceImpl<JTitleMapper, JTitle> implements IJTitleService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JTitle> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
