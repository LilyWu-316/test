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

import com.annaru.queue.model.JScreenContent;
import com.annaru.queue.service.IJScreenContentService;
import javax.validation.Valid;



/**
 * 提示内容表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "提示内容表接口", tags = {"提示内容表接口"})
@RestController
@RequestMapping("queue/jScreenContent")
public class JScreenContentController extends BaseController {
    @Autowired
    private IJScreenContentService jScreenContentService;

    @ApiOperation(value = "提示内容表列表", notes = "提示内容表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jScreenContent/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jScreenContentService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取提示内容表", notes = "获取提示内容表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jScreenContent/info")
    public Object info(@PathVariable("id") Integer id){
        JScreenContent jScreenContent = jScreenContentService.getById(id);
        return ResultMap.ok().put("jScreenContent",jScreenContent);
    }

    @ApiOperation(value = "保存提示内容表", notes = "保存提示内容表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jScreenContent/save")
    public Object save(@Valid @RequestBody JScreenContent jScreenContent) {
        try {
            jScreenContentService.save(jScreenContent);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改提示内容表", notes = "修改提示内容表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jScreenContent/update")
    public Object update(@Valid @RequestBody JScreenContent jScreenContent) {
        try {
            jScreenContentService.updateById(jScreenContent);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除提示内容表", notes = "删除提示内容表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jScreenContent/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jScreenContentService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
