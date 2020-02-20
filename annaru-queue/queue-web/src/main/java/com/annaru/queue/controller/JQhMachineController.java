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

import com.annaru.queue.model.JQhMachine;
import com.annaru.queue.service.IJQhMachineService;
import javax.validation.Valid;



/**
 * 取号机表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
@Api(value = "取号机表接口", tags = {"取号机表接口"})
@RestController
@RequestMapping("queue/jQhMachine")
public class JQhMachineController extends BaseController {
    @Autowired
    private IJQhMachineService jQhMachineService;

    @ApiOperation(value = "取号机表列表", notes = "取号机表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jQhMachine/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jQhMachineService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取取号机表", notes = "获取取号机表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jQhMachine/info")
    public Object info(@PathVariable("id") Integer id){
        JQhMachine jQhMachine = jQhMachineService.getById(id);
        return ResultMap.ok().put("jQhMachine",jQhMachine);
    }

    @ApiOperation(value = "保存取号机表", notes = "保存取号机表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jQhMachine/save")
    public Object save(@Valid @RequestBody JQhMachine jQhMachine) {
        try {
            jQhMachineService.save(jQhMachine);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改取号机表", notes = "修改取号机表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jQhMachine/update")
    public Object update(@Valid @RequestBody JQhMachine jQhMachine) {
        try {
            jQhMachineService.updateById(jQhMachine);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除取号机表", notes = "删除取号机表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jQhMachine/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jQhMachineService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
