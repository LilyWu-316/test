package com.annaru.queue.quartz.service.impl;

import com.annaru.queue.quartz.mapper.ScheduleJobLogMapper;
import com.annaru.queue.quartz.model.ScheduleJobLogEntity;
import com.annaru.queue.quartz.service.IScheduleJobLogService;
import com.annaru.queue.result.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements IScheduleJobLogService {

    @Override
    public PageParams queryPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        PageParams pageParams = new PageParams(params);
        QueryWrapper<ScheduleJobLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(jobId), "job_id", jobId)
                .orderByDesc("log_id");
        IPage<ScheduleJobLogEntity> iPage = this.page(pageParams, queryWrapper);
        return new PageParams(iPage);
    }

}
