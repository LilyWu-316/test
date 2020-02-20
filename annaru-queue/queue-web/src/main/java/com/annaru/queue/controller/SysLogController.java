package com.annaru.queue.controller;


import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.result.ResultMap;
import com.annaru.queue.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author TuMinglong
 * @description 日志管理
 * @date 2018年3月6日 上午9:42:00
 */

@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/list")
    public ResultMap dataGrid(@RequestParam Map<String, Object> params) {

        PageParams page = sysLogService.selectDataGrid(params);
        return ResultMap.ok().put("page", page);
    }
}
