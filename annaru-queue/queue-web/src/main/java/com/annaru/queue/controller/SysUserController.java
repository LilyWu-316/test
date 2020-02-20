package com.annaru.queue.controller;

import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.constants.CommonConstants;
import com.annaru.queue.model.SysUser;
import com.annaru.queue.model.vo.UserVo;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.result.ResultMap;
import com.annaru.queue.result.SelectNode;
import com.annaru.queue.service.ISysUserRoleService;
import com.annaru.queue.service.ISysUserService;
import com.annaru.queue.shiro.ShiroKit;
import com.annaru.queue.shiro.form.PasswordForm;
import com.annaru.queue.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description com.hdw.queue.controller
 * @Author TuMinglong
 * @Date 2018/12/13 11:42
 */
@ApiIgnore
@Api(value = "用户管理接口", tags = "用户管理接口")
@RestController
@RequestMapping("sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys/user/list")
    public ResultMap list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (ShiroKit.getUser().getId() != CommonConstants.SUPER_ADMIN) {
            params.put("userId", ShiroKit.getUser().getId());
        }
        PageParams page = sysUserService.selectDataGrid(params);

        return ResultMap.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public ResultMap info() {
        return ResultMap.ok().put("user", ShiroKit.getUser());
    }

    /**
     * 修改登录用户密码
     */
    @PostMapping("/password")
    public ResultMap password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        SysUser user = sysUserService.getById(ShiroKit.getUser().getId());
        String password = ShiroKit.md5(form.getPassword(), user.getLoginName() + user.getSalt());
        if (!user.getPassword().equals(password)) {
            return ResultMap.error("原密码不正确");
        }
        String newPassword = ShiroKit.md5(form.getNewPassword(), user.getLoginName() + user.getSalt());
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        sysUserService.updateById(user);
        return ResultMap.ok("密码修改成功");
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys/user/info")
    public ResultMap info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        List<Long> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
        user.setRoleIdList(roleIdList);
        return ResultMap.ok().put("user", user);

    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    @RequiresPermissions("sys/user/save")
    public ResultMap save(@Valid @RequestBody SysUser user) {
        try {
            UserVo u = sysUserService.selectByLoginName(user.getLoginName(), null);
            if (u != null) {
                return ResultMap.error("登录名已存在");
            }
            String salt = ShiroKit.getRandomSalt(16);
            user.setSalt(salt);
            String pwd = ShiroKit.md5(user.getPassword(), user.getLoginName() + salt);
            user.setPassword(pwd);
            user.setCreateTime(new Date());
            user.setCreateUserId(ShiroKit.getUser().getId());
            sysUserService.saveByVo(user);
            return ResultMap.ok("添加成功");
        } catch (Exception e) {
            e.getStackTrace();
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @RequiresPermissions("sys/user/update")
    public ResultMap update(@Valid @RequestBody SysUser user) {
        try {
            if (StringUtils.isNotBlank(user.getPassword())) {
                String salt = ShiroKit.getRandomSalt(16);
                user.setSalt(salt);
                String pwd = ShiroKit.md5(user.getPassword(), user.getLoginName() + salt);
                user.setPassword(pwd);
            } else {
                user.setPassword(null);
            }
            user.setUpdateTime(new Date());
            user.setCreateUserId(ShiroKit.getUser().getId());
            sysUserService.updateByVo(user);
            return ResultMap.ok("修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultMap.error("运行异常，请联系管理员");
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys/user/delete")
    public ResultMap delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, CommonConstants.SUPER_ADMIN)) {
            return ResultMap.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, ShiroKit.getUser().getId())) {
            return ResultMap.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return ResultMap.ok();
    }

    /**
     * 用户选择树
     * @return
     */
    @ApiOperation(value = "用户选择树", notes = "用户选择树")
    @GetMapping("/getUserTree")
    public Object getUserTree() {
        try {
            List<SelectNode> nodeList = new ArrayList<>();
            List<SysUser> list = sysUserService.list();
            list.forEach(baseUser -> {
                SelectNode node = new SelectNode();
                node.setLabel(baseUser.getName());
                node.setValue(baseUser.getId().toString());
                nodeList.add(node);
            });
            return ResultMap.ok().put("list", nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.error("运行异常，请联系管理员");
        }
    }
}
