package com.annaru.queue.service;


import com.annaru.queue.model.SysUserToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author TuMinglong
 * @since 2018-06-11
 */
public interface ISysUserTokenService extends IService<SysUserToken> {

    SysUserToken selectByToken(String token);
}
