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

import com.annaru.queue.model.JUserInfo;
import com.annaru.queue.service.IJUserInfoService;
import javax.validation.Valid;



/**
 * 机构人员信息
 *
 * @Author jyehui
 * @Date  2020-02-03 18:46:28
 */
@Api(value = "机构人员信息接口", tags = {"机构人员信息接口"})
@RestController
@RequestMapping("queue/jUserInfo")
public class JUserInfoController extends BaseController {
    @Autowired
    private IJUserInfoService jUserInfoService;

    @ApiOperation(value = "机构人员信息列表", notes = "机构人员信息列表")
    @GetMapping("/list")
    @RequiresPermissions("queue/jUserInfo/list")
    public Object list(@RequestParam int page, @RequestParam int limit){
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        PageParams pageParams = new PageParams(params);
        PageParams pageList = jUserInfoService.selectDataPage(pageParams);
        return ResultMap.ok().put("page",pageList);
    }


    @ApiOperation(value = "获取机构人员信息", notes = "获取机构人员信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("queue/jUserInfo/info")
    public Object info(@PathVariable("id") Integer id){
        JUserInfo jUserInfo = jUserInfoService.getById(id);
        return ResultMap.ok().put("jUserInfo",jUserInfo);
    }

    @ApiOperation(value = "保存机构人员信息", notes = "保存机构人员信息")
    @PostMapping("/save")
    @RequiresPermissions("queue/jUserInfo/save")
    public Object save(@Valid @RequestBody JUserInfo jUserInfo) {
        try {
            jUserInfoService.save(jUserInfo);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    @ApiOperation(value = "修改机构人员信息", notes = "修改机构人员信息")
    @PostMapping("/update")
    @RequiresPermissions("queue/jUserInfo/update")
    public Object update(@Valid @RequestBody JUserInfo jUserInfo) {
        try {
            jUserInfoService.updateById(jUserInfo);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

    @ApiOperation(value = "删除机构人员信息", notes = "删除机构人员信息")
    @PostMapping("/delete")
    @RequiresPermissions("queue/jUserInfo/delete")
    public Object delete(@RequestBody Integer[]ids) {
        try {
            jUserInfoService.removeByIds(Arrays.asList(ids));
            return ResultMap.ok("删除成功！");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }

    }

}
