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

import com.annaru.queue.model.JSmallScreenTemplate;
import com.annaru.queue.service.IJSmallScreenTemplateService;
import javax.validation.Valid;



/**
 * 小屏模板表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "小屏模板表接口", tags = {"小屏模板表接口"})
@RestController
@RequestMapping("queue/jSmallScreenTemplate")
public class JSmallScreenTemplateController extends BaseController {
    @Autowired
    private IJSmallScreenTemplateService jSmallScreenTemplateService;

    @ApiOperation(value = "小屏模板表列表", notes = "小屏模板表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jSmallScreenTemplate/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jSmallScreenTemplateService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取小屏模板表", notes = "获取小屏模板表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jSmallScreenTemplate/info")
    public Object info(@PathVariable("id") Integer id){
        JSmallScreenTemplate jSmallScreenTemplate = jSmallScreenTemplateService.getById(id);
        return ResultMap.ok().put("jSmallScreenTemplate",jSmallScreenTemplate);
    }

    @ApiOperation(value = "保存小屏模板表", notes = "保存小屏模板表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jSmallScreenTemplate/save")
    public Object save(@Valid @RequestBody JSmallScreenTemplate jSmallScreenTemplate) {
        try {
            jSmallScreenTemplateService.save(jSmallScreenTemplate);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改小屏模板表", notes = "修改小屏模板表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jSmallScreenTemplate/update")
    public Object update(@Valid @RequestBody JSmallScreenTemplate jSmallScreenTemplate) {
        try {
            jSmallScreenTemplateService.updateById(jSmallScreenTemplate);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除小屏模板表", notes = "删除小屏模板表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jSmallScreenTemplate/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jSmallScreenTemplateService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
