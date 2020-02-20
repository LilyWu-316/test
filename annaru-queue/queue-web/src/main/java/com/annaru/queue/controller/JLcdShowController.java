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

import com.annaru.queue.model.JLcdShow;
import com.annaru.queue.service.IJLcdShowService;
import javax.validation.Valid;



/**
 * 叫号表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:29
 */
@Api(value = "叫号表接口", tags = {"叫号表接口"})
@RestController
@RequestMapping("queue/jLcdShow")
public class JLcdShowController extends BaseController {
    @Autowired
    private IJLcdShowService jLcdShowService;

    @ApiOperation(value = "叫号表列表", notes = "叫号表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jLcdShow/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jLcdShowService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取叫号表", notes = "获取叫号表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jLcdShow/info")
    public Object info(@PathVariable("id") Integer id){
        JLcdShow jLcdShow = jLcdShowService.getById(id);
        return ResultMap.ok().put("jLcdShow",jLcdShow);
    }

    @ApiOperation(value = "保存叫号表", notes = "保存叫号表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jLcdShow/save")
    public Object save(@Valid @RequestBody JLcdShow jLcdShow) {
        try {
            jLcdShowService.save(jLcdShow);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改叫号表", notes = "修改叫号表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jLcdShow/update")
    public Object update(@Valid @RequestBody JLcdShow jLcdShow) {
        try {
            jLcdShowService.updateById(jLcdShow);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除叫号表", notes = "删除叫号表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jLcdShow/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jLcdShowService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
