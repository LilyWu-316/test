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

import com.annaru.queue.model.JRelScreenConfVideoInfo;
import com.annaru.queue.service.IJRelScreenConfVideoInfoService;
import javax.validation.Valid;



/**
 * screen_conf与video_info的关联表
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "screen_conf与video_info的关联表接口", tags = {"screen_conf与video_info的关联表接口"})
@RestController
@RequestMapping("queue/jRelScreenConfVideoInfo")
public class JRelScreenConfVideoInfoController extends BaseController {
    @Autowired
    private IJRelScreenConfVideoInfoService jRelScreenConfVideoInfoService;

    @ApiOperation(value = "screen_conf与video_info的关联表列表", notes = "screen_conf与video_info的关联表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jRelScreenConfVideoInfo/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jRelScreenConfVideoInfoService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取screen_conf与video_info的关联表", notes = "获取screen_conf与video_info的关联表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jRelScreenConfVideoInfo/info")
    public Object info(@PathVariable("id") Integer id){
        JRelScreenConfVideoInfo jRelScreenConfVideoInfo = jRelScreenConfVideoInfoService.getById(id);
        return ResultMap.ok().put("jRelScreenConfVideoInfo",jRelScreenConfVideoInfo);
    }

    @ApiOperation(value = "保存screen_conf与video_info的关联表", notes = "保存screen_conf与video_info的关联表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jRelScreenConfVideoInfo/save")
    public Object save(@Valid @RequestBody JRelScreenConfVideoInfo jRelScreenConfVideoInfo) {
        try {
            jRelScreenConfVideoInfoService.save(jRelScreenConfVideoInfo);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改screen_conf与video_info的关联表", notes = "修改screen_conf与video_info的关联表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jRelScreenConfVideoInfo/update")
    public Object update(@Valid @RequestBody JRelScreenConfVideoInfo jRelScreenConfVideoInfo) {
        try {
            jRelScreenConfVideoInfoService.updateById(jRelScreenConfVideoInfo);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除screen_conf与video_info的关联表", notes = "删除screen_conf与video_info的关联表")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jRelScreenConfVideoInfo/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jRelScreenConfVideoInfoService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
