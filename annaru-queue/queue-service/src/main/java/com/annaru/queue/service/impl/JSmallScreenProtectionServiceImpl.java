package com.annaru.queue.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.annaru.queue.result.PageParams;

import com.annaru.queue.mapper.JSmallScreenProtectionMapper;
import com.annaru.queue.model.JSmallScreenProtection;
import com.annaru.queue.service.IJSmallScreenProtectionService;


/**
 * 小屏屏保表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Service
public class JSmallScreenProtectionServiceImpl extends ServiceImpl<JSmallScreenProtectionMapper, JSmallScreenProtection> implements IJSmallScreenProtectionService {

    @Override
    public PageParams selectDataPage(PageParams pageParams){
        IPage<JSmallScreenProtection> iPage = this.baseMapper.selectDataPage(pageParams);
        return new PageParams(iPage);
    }

}
