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

import com.annaru.queue.model.JVideoInfo;
import com.annaru.queue.service.IJVideoInfoService;
import javax.validation.Valid;



/**
 * 视频表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:27
 */
@Api(value = "视频表接口", tags = {"视频表接口"})
@RestController
@RequestMapping("queue/jVideoInfo")
public class JVideoInfoController extends BaseController {
    @Autowired
    private IJVideoInfoService jVideoInfoService;

    @ApiOperation(value = "视频表列表", notes = "视频表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jVideoInfo/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jVideoInfoService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取视频表", notes = "获取视频表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jVideoInfo/info")
    public Object info(@PathVariable("id") Integer id){
        JVideoInfo jVideoInfo = jVideoInfoService.getById(id);
        return ResultMap.ok().put("jVideoInfo",jVideoInfo);
    }

    @ApiOperation(value = "保存视频表", notes = "保存视频表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jVideoInfo/save")
    public Object save(@Valid @RequestBody JVideoInfo jVideoInfo) {
        try {
            jVideoInfoService.save(jVideoInfo);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改视频表", notes = "修改视频表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jVideoInfo/update")
    public Object update(@Valid @RequestBody JVideoInfo jVideoInfo) {
        try {
            jVideoInfoService.updateById(jVideoInfo);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除视频表", notes = "删除视频表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jVideoInfo/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jVideoInfoService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
