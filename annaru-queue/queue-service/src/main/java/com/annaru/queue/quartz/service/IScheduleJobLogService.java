package com.annaru.queue.quartz.service;


import com.annaru.queue.quartz.model.ScheduleJobLogEntity;
import com.annaru.queue.result.PageParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description 定时任务日志
 * @Author TuMinglong
 * @Date 2019/1/18 15:59
 **/
public interface IScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageParams queryPage(Map<String, Object> params);

}
