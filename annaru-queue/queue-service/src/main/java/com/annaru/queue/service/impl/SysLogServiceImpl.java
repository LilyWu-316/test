package com.annaru.queue.service.impl;

import com.annaru.queue.mapper.SysLogMapper;
import com.annaru.queue.model.SysLog;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.service.ISysLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public PageParams selectDataGrid(Map<String, Object> params) {
        PageParams pageParams = new PageParams(params);
        IPage<SysLog> iPage = this.baseMapper.selectSysLogPage(pageParams, pageParams.getRequestMap());
        return new PageParams(iPage);
    }

    @Override
    public List<SysLog> selectSysLogList(Map<String, Object> par) {

        return this.baseMapper.selectSysLogList(par);
    }

}
