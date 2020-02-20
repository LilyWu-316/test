package com.annaru.queue.controller;


import com.annaru.queue.base.controller.BaseController;
import com.annaru.queue.config.redis.IRedisService;
import com.annaru.queue.model.SysUserToken;
import com.annaru.queue.model.vo.UserVo;
import com.annaru.queue.result.ResultMap;
import com.annaru.queue.service.ISysResourceService;
import com.annaru.queue.service.ISysUserService;
import com.annaru.queue.service.ISysUserTokenService;
import com.annaru.queue.shiro.ShiroKit;
import com.annaru.queue.shiro.form.LoginForm;
import com.annaru.queue.utils.JwtUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录退出Controller
 * @Author TuMinglong
 * @Date 2018/6/11 17:07
 */
@Api(tags = "登录退出")
@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserTokenService userTokenService;

    @Autowired
    private ISysResourceService resourceService;

    @Autowired
    private Producer producer;

    @Autowired
    private IRedisService redisService;

    //30分钟过期
    @Value("${annaru.expire}")
    private int EXPIRE;

    @GetMapping("captcha.jpg")
    public void kaptcha(HttpServletResponse response, String uuid) throws IOException {
        logger.info("前台请求的UUID:" + uuid);
        if (StringUtils.isBlank(uuid)) {
            throw new RuntimeException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        redisService.set(uuid, code);

        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");

        BufferedImage image = producer.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @ApiOperation(value = "药柜管理员登录", notes = "药柜管理员登录")
    @PostMapping("/queue/auth")
    public Object login(@RequestParam String userName, @RequestParam String passaWord) {
        logger.info("药柜管理员登录");
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passaWord)) {
            return ResultMap.error("用户名或密码为空！");
        }

        UserVo userVo =userService.selectByLoginName(userName, 3);

        if (null == userVo) {
            return ResultMap.error("登陆失败：药柜管理员账户不存在！");
        }
        if (!userVo.getPassword().equals(ShiroKit.md5(passaWord, userVo.getLoginName() + userVo.getSalt()))) {
            return ResultMap.error("登陆失败：药柜管理员账户或者密码不正确！");
        }

        // 生成token，并保存到数据库
        return createToken(userVo.getId());
    }

    @PostMapping("/sys/login")
    public Object login(@RequestBody LoginForm form) {
        logger.info("POST请求登录");
        if (StringUtils.isBlank(form.getUsername())) {
            return ResultMap.error("用户名不能为空");
        }
        if (StringUtils.isBlank(form.getPassword())) {
            return ResultMap.error("密码不能为空");
        }
        if (StringUtils.isBlank(form.getCaptcha())) {
            return ResultMap.error("验证码不能为空");
        }
        if (StringUtils.isBlank(form.getUuid())) {
            return ResultMap.error("uuid不能为空");
        }
        String validateCode = (String) redisService.get(form.getUuid());
        logger.info("session中的图形码字符串:" + validateCode);

        //比对
        if (StringUtils.isBlank(form.getCaptcha()) || StringUtils.isBlank(validateCode) || !validateCode.equalsIgnoreCase(form.getCaptcha())) {
            return ResultMap.error("验证码错误");
        }

        UserVo userVo =userService.selectByLoginName(form.getUsername(), null);

        if (null == userVo) {
            return ResultMap.error("账号不存在");
        }
        if (!userVo.getPassword().equals(ShiroKit.md5(form.getPassword(), userVo.getLoginName() + userVo.getSalt()))) {
            return ResultMap.error("密码不正确");
        }

        // 清除验证码
        redisService.del(form.getUuid());

        //生成token，并保存到数据库
        return createToken(userVo.getId());
    }

    @ApiOperation(value = "退出", notes = "退出登录")
    @PostMapping("/sys/logout")
    public ResultMap logout() {
        //生成一个token
        JwtUtils jwtUtils = new JwtUtils();
        Map<String, Object> params = new HashMap<>();
        params.put("token", ShiroKit.getUser().getId());
        String token = jwtUtils.generateToken(params, "annaru-queue", EXPIRE);
        //修改token
        SysUserToken tokenEntity = new SysUserToken();
        tokenEntity.setUserId(ShiroKit.getUser().getId());
        tokenEntity.setToken(token);
        userTokenService.updateById(tokenEntity);
        return ResultMap.ok();
    }

    public ResultMap createToken(Long userId) {
        //生成一个token
        JwtUtils jwtUtils = new JwtUtils();
        Map<String, Object> params = new HashMap<>();
        params.put("token", userId);
        String token = jwtUtils.generateToken(params, "annaru-queue", EXPIRE);
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserToken tokenEntity = userTokenService.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            userTokenService.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            userTokenService.updateById(tokenEntity);
        }
        ResultMap r = ResultMap.ok().put("token", token).put("expire", EXPIRE);
        return r;
    }
}
