package com.annaru.queue.service.impl;

import com.annaru.queue.mapper.SysUserTokenMapper;
import com.annaru.queue.model.SysUserToken;
import com.annaru.queue.service.ISysUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author TuMinglong
 * @since 2018-06-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements ISysUserTokenService {

    @Override
    public SysUserToken selectByToken(String token) {
        return this.baseMapper.selectByToken(token);
    }
}
