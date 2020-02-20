package com.annaru.queue.controller;

import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.constants.CommonConstants;
import com.annaru.queue.model.SysRole;
import com.annaru.queue.model.SysRoleResource;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.result.ResultMap;
import com.annaru.queue.result.TreeNode;
import com.annaru.queue.service.ISysRoleResourceService;
import com.annaru.queue.service.ISysRoleService;
import com.annaru.queue.shiro.ShiroKit;
import com.annaru.queue.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色管理
 * @Author TuMinglong
 * @Date 2018/12/13 15:12
 */
@ApiIgnore
@Api(value = "角色管理接口", tags = {"角色管理接口"})
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys/role/list")
    public ResultMap list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (ShiroKit.getUser().getId() != CommonConstants.SUPER_ADMIN) {
            params.put("createUserId", ShiroKit.getUser().getId());
        }
        PageParams page = sysRoleService.selectDataGrid(params);
        return ResultMap.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    @RequiresPermissions("sys/role/select")
    public ResultMap select() {
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (ShiroKit.getUser().getId() != CommonConstants.SUPER_ADMIN) {
            map.put("createUserId", ShiroKit.getUser().getId());
        }
        List<SysRole> list = sysRoleService.selectSysRoleList(map);

        return ResultMap.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys/role/info")
    public ResultMap info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> resourceIdList = sysRoleResourceService.selectResourceIdListByRoleId(roleId);
        role.setResourceIdList(resourceIdList);
        List<SysRoleResource> roleResourceList=sysRoleResourceService.selectResourceNodeListByRoleId(roleId);
        List<TreeNode> treeNodeList= Lists.newArrayList();
        if(!roleResourceList.isEmpty()){
            roleResourceList.forEach(roleResource ->{
                TreeNode treeNode=new TreeNode();
                treeNode.setId(roleResource.getResourceId().toString());
                treeNode.setLabel(roleResource.getResource().getName());
                treeNodeList.add(treeNode);
            });
        }
        role.setResourceNodeList(treeNodeList);
        return ResultMap.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @PostMapping("/save")
    @RequiresPermissions("sys/role/save")
    public ResultMap save(@RequestBody SysRole role) {
        ValidatorUtils.validateEntity(role);
        role.setCreateTime(new Date());
        role.setCreateUserId(ShiroKit.getUser().getId());
        sysRoleService.saveByVo(role);

        return ResultMap.ok();
    }

    /**
     * 修改角色
     */
    @PostMapping("/update")
    @RequiresPermissions("sys/role/update")
    public ResultMap update(@RequestBody SysRole role) {
        ValidatorUtils.validateEntity(role);
        role.setUpdateTime(new Date());
        role.setCreateUserId(ShiroKit.getUser().getId());
        sysRoleService.updateByVo(role);

        return ResultMap.ok();
    }

    /**
     * 删除角色
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys/role/delete")
    public ResultMap delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return ResultMap.ok();
    }

}
