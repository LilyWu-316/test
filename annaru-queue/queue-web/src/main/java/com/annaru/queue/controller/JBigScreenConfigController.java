package com.annaru.queue.controller;

import java.util.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.utils.UUIDGenerator;
import com.annaru.queue.shiro.ShiroKit;
import com.annaru.queue.result.ResultMap;

import com.annaru.queue.model.JBigScreenConfig;
import com.annaru.queue.service.IJBigScreenConfigService;
import javax.validation.Valid;



/**
 * 大屏配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
@Api(value = "大屏配置表接口", tags = {"大屏配置表接口"})
@RestController
@RequestMapping("queue/jBigScreenConfig")
public class JBigScreenConfigController extends BaseController {
    @Autowired
    private IJBigScreenConfigService jBigScreenConfigService;

    @ApiOperation(value = "大屏配置表列表", notes = "大屏配置表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jBigScreenConfig/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jBigScreenConfigService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取大屏配置表", notes = "获取大屏配置表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jBigScreenConfig/info")
    public Object info(@PathVariable("id") Integer id){
        JBigScreenConfig jBigScreenConfig = jBigScreenConfigService.getById(id);
        return ResultMap.ok().put("jBigScreenConfig",jBigScreenConfig);
    }

    @ApiOperation(value = "保存大屏配置表", notes = "保存大屏配置表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jBigScreenConfig/save")
    public Object save(@Valid @RequestBody JBigScreenConfig jBigScreenConfig) {
        try {
            jBigScreenConfigService.save(jBigScreenConfig);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改大屏配置表", notes = "修改大屏配置表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jBigScreenConfig/update")
    public Object update(@Valid @RequestBody JBigScreenConfig jBigScreenConfig) {
        try {
            jBigScreenConfigService.updateById(jBigScreenConfig);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除大屏配置表", notes = "删除大屏配置表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jBigScreenConfig/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jBigScreenConfigService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
