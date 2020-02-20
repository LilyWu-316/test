package com.annaru.queue.controller;

import java.util.*;

import com.annaru.queue.constants.queueEnum;
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

import com.annaru.queue.model.JBigScreenTemplate;
import com.annaru.queue.service.IJBigScreenTemplateService;
import javax.validation.Valid;



/**
 * 大屏模板表
 *
 * @Author jyehui
 * @Date  2020-02-03 10:11:28
 */
@Api(value = "大屏模板表接口", tags = {"大屏模板表接口"})
@RestController
@RequestMapping("queue/jBigScreenTemplate")
public class JBigScreenTemplateController extends BaseController {
    @Autowired
    private IJBigScreenTemplateService jBigScreenTemplateService;

    @ApiOperation(value = "大屏模板表列表", notes = "大屏模板表列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jBigScreenTemplate/list")
    public Object list(@RequestParam int page, @RequestParam int limit, JBigScreenTemplate jBigScreenTemplate){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jBigScreenTemplateService.selectDataPage(pageParams, jBigScreenTemplate);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取大屏模板表", notes = "获取大屏模板表")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jBigScreenTemplate/info")
    public Object info(@PathVariable("id") Integer id){
        JBigScreenTemplate jBigScreenTemplate = jBigScreenTemplateService.getById(id);
        return ResultMap.ok().put("jBigScreenTemplate",jBigScreenTemplate);
    }

    @ApiOperation(value = "保存大屏模板表", notes = "保存大屏模板表")
    @PostMapping("/save")
    @RequiresPermissions("queue/jBigScreenTemplate/save")
    public Object save(@Valid @RequestBody JBigScreenTemplate jBigScreenTemplate) {
        try {
            jBigScreenTemplate.setCreateTime(new Date());
            jBigScreenTemplate.setUpdateTime(new Date());
            jBigScreenTemplateService.save(jBigScreenTemplate);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改大屏模板表", notes = "修改大屏模板表")
    @PostMapping("/update")
    @RequiresPermissions("queue/jBigScreenTemplate/update")
    public Object update(@Valid @RequestBody JBigScreenTemplate jBigScreenTemplate) {
        try {
            jBigScreenTemplate.setUpdateTime(new Date());
            jBigScreenTemplateService.updateById(jBigScreenTemplate);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除或者恢复大屏模板表", notes = "删除或者恢复大屏模板表")
    @PostMapping("/deleteOrRecover")
    @RequiresPermissions("queue/jBigScreenTemplate/deleteOrRecover")
    public Object deleteOrRecover(@RequestBody Integer[]ids) {
        try {
            for(Integer id: ids){
                JBigScreenTemplate bigScreenTemplate = jBigScreenTemplateService.getById(id);
                if (queueEnum.deleteStatus.ISDELETE.getValue() == bigScreenTemplate.getIsDelete()) {
                    bigScreenTemplate.setIsDelete(queueEnum.deleteStatus.NOTDELETE.getValue());
                } else if(queueEnum.deleteStatus.NOTDELETE.getValue() == bigScreenTemplate.getIsDelete()) {
                    bigScreenTemplate.setIsDelete(queueEnum.deleteStatus.ISDELETE.getValue());
                }
                bigScreenTemplate.setUpdateTime(new Date());
                jBigScreenTemplateService.updateById(bigScreenTemplate);
            }
            return ResultMap.ok("操作成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "禁用或者启用模板", notes = "禁用启用")
    @PostMapping("/disableOrEnabled")
    @RequiresPermissions("queue/jBigScreenTemplate/disableOrEnabled")
    public Object disableOrEnabled(@RequestBody Integer[]ids) {
        try {
            for(Integer id: ids){
                JBigScreenTemplate bigScreenTemplate = jBigScreenTemplateService.getById(id);
                if (queueEnum.enabledStatus.ENABLED.value == bigScreenTemplate.getStatus()) {
                    bigScreenTemplate.setStatus(queueEnum.enabledStatus.DISABLE.value);
                } else if(queueEnum.enabledStatus.DISABLE.value == bigScreenTemplate.getStatus()) {
                    bigScreenTemplate.setStatus(queueEnum.enabledStatus.ENABLED.value);
                }
                bigScreenTemplate.setUpdateTime(new Date());
                jBigScreenTemplateService.updateById(bigScreenTemplate);
            }
            return ResultMap.ok("操作成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
