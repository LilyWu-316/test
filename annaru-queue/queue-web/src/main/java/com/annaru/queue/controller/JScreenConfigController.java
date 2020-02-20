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

import com.annaru.queue.model.JScreenConfig;
import com.annaru.queue.service.IJScreenConfigService;
import javax.validation.Valid;



/**
 * 屏幕信息配置表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "屏幕信息配置表接口", tags = {"屏幕信息配置表接口"})
@RestController
@RequestMapping("queue/jScreenConfig")
public class JScreenConfigController extends BaseController {
    @Autowired
    private IJScreenConfigService jScreenConfigService;

    @ApiOperation(value = "屏幕信息配置表列表", notes = "屏幕信息配置表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jScreenConfig/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jScreenConfigService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取屏幕信息配置表", notes = "获取屏幕信息配置表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jScreenConfig/info")
    public Object info(@PathVariable("id") Integer id){
        JScreenConfig jScreenConfig = jScreenConfigService.getById(id);
        return ResultMap.ok().put("jScreenConfig",jScreenConfig);
    }

    @ApiOperation(value = "保存屏幕信息配置表", notes = "保存屏幕信息配置表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jScreenConfig/save")
    public Object save(@Valid @RequestBody JScreenConfig jScreenConfig) {
        try {
            jScreenConfigService.save(jScreenConfig);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改屏幕信息配置表", notes = "修改屏幕信息配置表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jScreenConfig/update")
    public Object update(@Valid @RequestBody JScreenConfig jScreenConfig) {
        try {
            jScreenConfigService.updateById(jScreenConfig);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除屏幕信息配置表", notes = "删除屏幕信息配置表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jScreenConfig/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jScreenConfigService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
