package com.annaru.queue.quartz.mapper;


import com.annaru.queue.quartz.model.ScheduleJobEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * @Description 定时任务
 * @Author TuMinglong
 * @Date 2018/12/13 10:44
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
