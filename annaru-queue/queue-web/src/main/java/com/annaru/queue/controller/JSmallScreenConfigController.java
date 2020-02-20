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

import com.annaru.queue.model.JSmallScreenConfig;
import com.annaru.queue.service.IJSmallScreenConfigService;
import javax.validation.Valid;



/**
 * 小屏配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "小屏配置表接口", tags = {"小屏配置表接口"})
@RestController
@RequestMapping("queue/jSmallScreenConfig")
public class JSmallScreenConfigController extends BaseController {
    @Autowired
    private IJSmallScreenConfigService jSmallScreenConfigService;

    @ApiOperation(value = "小屏配置表列表", notes = "小屏配置表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jSmallScreenConfig/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jSmallScreenConfigService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取小屏配置表", notes = "获取小屏配置表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jSmallScreenConfig/info")
    public Object info(@PathVariable("id") Integer id){
        JSmallScreenConfig jSmallScreenConfig = jSmallScreenConfigService.getById(id);
        return ResultMap.ok().put("jSmallScreenConfig",jSmallScreenConfig);
    }

    @ApiOperation(value = "保存小屏配置表", notes = "保存小屏配置表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jSmallScreenConfig/save")
    public Object save(@Valid @RequestBody JSmallScreenConfig jSmallScreenConfig) {
        try {
            jSmallScreenConfigService.save(jSmallScreenConfig);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改小屏配置表", notes = "修改小屏配置表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jSmallScreenConfig/update")
    public Object update(@Valid @RequestBody JSmallScreenConfig jSmallScreenConfig) {
        try {
            jSmallScreenConfigService.updateById(jSmallScreenConfig);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除小屏配置表", notes = "删除小屏配置表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jSmallScreenConfig/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jSmallScreenConfigService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
