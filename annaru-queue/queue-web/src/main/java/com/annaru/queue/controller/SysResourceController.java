package com.annaru.queue.controller;

import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.constants.CommonEnum;
import com.annaru.queue.exception.GlobalException;
import com.annaru.queue.model.SysResource;
import com.annaru.queue.result.ResultMap;
import com.annaru.queue.service.ISysResourceService;
import com.annaru.queue.service.ISysUserService;
import com.annaru.queue.shiro.ShiroKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @Description 资源表
 * @Author TuMinglong
 * @Date 2018/12/11 14:21
 */
@ApiIgnore
@Api(value = "资源管理接口", tags = {"资源管理接口"})
@RestController
@RequestMapping("sys/menu")
public class SysResourceController extends BaseController {
    @Autowired
    private ISysResourceService sysResourceService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 导航菜单
     */
    @ApiOperation(value = "导航菜单", notes = "导航菜单")
    @GetMapping("/nav")
    public ResultMap nav() {
        Long userId = ShiroKit.getUser().getId();
        List<SysResource> menuList = sysResourceService.selectUserResourceListByUserId(userId);
        Set<String> permissions = sysUserService.selectUserPermissions(userId);
        return ResultMap.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有菜单列表
     */
    @ApiOperation(value = "所有菜单列表", notes = "所有菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/menu/list")
    public List<SysResource> list() {
        Map<String, Object> params = new HashMap<>();
        List<SysResource> menuList = sysResourceService.selectResourceList(params);
        return menuList;
    }

    /**
     * 菜单信息
     */
    @ApiOperation(value = "获取资源表", notes = "获取资源表")
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys/menu/info")
    public ResultMap info(@PathVariable("menuId") Long menuId) {
        SysResource sysResource = sysResourceService.getById(menuId);
        return ResultMap.ok().put("menu", sysResource);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存资源表", notes = "保存资源表")
    @PostMapping("/save")
    @RequiresPermissions("sys/menu/save")
    public ResultMap save(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setCreateTime(new Date());
        sysResource.setCreateUser(ShiroKit.getUser().getLoginName());
        sysResourceService.save(sysResource);
        return ResultMap.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改资源表", notes = "修改资源表")
    @PostMapping("/update")
    @RequiresPermissions("sys/menu/update")
    public ResultMap update(@RequestBody SysResource sysResource) {
        //数据校验
        verifyForm(sysResource);
        sysResource.setUpdateTime(new Date());
        sysResource.setUpdateUser(ShiroKit.getUser().getLoginName());
        sysResourceService.updateById(sysResource);
        return ResultMap.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除资源表", notes = "删除资源表")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys/menu/delete")
    public ResultMap delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return ResultMap.error("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysResource> menuList = sysResourceService.selectListByParentId(menuId);
        if (menuList.size() > 0) {
            return ResultMap.error("请先删除子菜单或按钮");
        }
        sysResourceService.removeById(menuId);
        return ResultMap.ok();
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value = "选择菜单(添加、修改菜单)", notes = "选择菜单(添加、修改菜单)")
    @GetMapping("/select")
    @RequiresPermissions("sys/menu/select")
    public ResultMap select() {
        //查询列表数据
        List<SysResource> menuList = sysResourceService.selectNotButtonList();
        //添加顶级菜单
        SysResource root = new SysResource();
        root.setId(0L);
        root.setName("顶级菜单");
        root.setParentId(0L);
        root.setOpen(true);
        menuList.add(root);
        return ResultMap.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysResource sysResource) {
        if (StringUtils.isBlank(sysResource.getName())) {
            throw new GlobalException("菜单名称不能为空");
        }
        if (sysResource.getParentId() == null) {
            throw new GlobalException("上级菜单不能为空");
        }
        //菜单
        if (sysResource.getResourceType() == CommonEnum.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(sysResource.getUrl())) {
                throw new GlobalException("菜单URL不能为空");
            }
        }
        //上级菜单类型
        int parentType = CommonEnum.MenuType.CATALOG.getValue();
        if (sysResource.getParentId() != 0) {
            SysResource parentMenu = sysResourceService.getById(sysResource.getParentId());
            parentType = parentMenu.getResourceType();
        }
        //目录、菜单
        if (sysResource.getResourceType() == CommonEnum.MenuType.CATALOG.getValue() ||
                sysResource.getResourceType() == CommonEnum.MenuType.MENU.getValue()) {
            if (parentType != CommonEnum.MenuType.CATALOG.getValue()) {
                throw new GlobalException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (sysResource.getResourceType() == CommonEnum.MenuType.BUTTON.getValue()) {
            if (parentType != CommonEnum.MenuType.MENU.getValue()) {
                throw new GlobalException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
