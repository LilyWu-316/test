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

import com.annaru.queue.model.JTitle;
import com.annaru.queue.service.IJTitleService;
import javax.validation.Valid;



/**
 * 职称表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "职称表接口", tags = {"职称表接口"})
@RestController
@RequestMapping("queue/jTitle")
public class JTitleController extends BaseController {
    @Autowired
    private IJTitleService jTitleService;

    @ApiOperation(value = "职称表列表", notes = "职称表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jTitle/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jTitleService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取职称表", notes = "获取职称表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jTitle/info")
    public Object info(@PathVariable("id") Integer id){
        JTitle jTitle = jTitleService.getById(id);
        return ResultMap.ok().put("jTitle",jTitle);
    }

    @ApiOperation(value = "保存职称表", notes = "保存职称表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jTitle/save")
    public Object save(@Valid @RequestBody JTitle jTitle) {
        try {
            jTitleService.save(jTitle);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改职称表", notes = "修改职称表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jTitle/update")
    public Object update(@Valid @RequestBody JTitle jTitle) {
        try {
            jTitleService.updateById(jTitle);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除职称表", notes = "删除职称表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jTitle/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jTitleService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
