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

import com.annaru.queue.model.JSmallScreenProtection;
import com.annaru.queue.service.IJSmallScreenProtectionService;
import javax.validation.Valid;



/**
 * 小屏屏保表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "小屏屏保表接口", tags = {"小屏屏保表接口"})
@RestController
@RequestMapping("queue/jSmallScreenProtection")
public class JSmallScreenProtectionController extends BaseController {
    @Autowired
    private IJSmallScreenProtectionService jSmallScreenProtectionService;

    @ApiOperation(value = "小屏屏保表列表", notes = "小屏屏保表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jSmallScreenProtection/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jSmallScreenProtectionService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取小屏屏保表", notes = "获取小屏屏保表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jSmallScreenProtection/info")
    public Object info(@PathVariable("id") Integer id){
        JSmallScreenProtection jSmallScreenProtection = jSmallScreenProtectionService.getById(id);
        return ResultMap.ok().put("jSmallScreenProtection",jSmallScreenProtection);
    }

    @ApiOperation(value = "保存小屏屏保表", notes = "保存小屏屏保表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jSmallScreenProtection/save")
    public Object save(@Valid @RequestBody JSmallScreenProtection jSmallScreenProtection) {
        try {
            jSmallScreenProtectionService.save(jSmallScreenProtection);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改小屏屏保表", notes = "修改小屏屏保表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jSmallScreenProtection/update")
    public Object update(@Valid @RequestBody JSmallScreenProtection jSmallScreenProtection) {
        try {
            jSmallScreenProtectionService.updateById(jSmallScreenProtection);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除小屏屏保表", notes = "删除小屏屏保表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jSmallScreenProtection/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jSmallScreenProtectionService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
