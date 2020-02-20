package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JLcdShowMapper;
import com.annaru.queue.model.JLcdShow;
import com.annaru.queue.service.IJLcdShowService;


/**
 * 叫号表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
@Service
public class JLcdShowServiceImpl extends ServiceImpl<JLcdShowMapper, JLcdShow> implements IJLcdShowService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JLcdShow> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
