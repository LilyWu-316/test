package com.annaru.queue.service.impl;

import com.annaru.queue.mapper.SysFileMapper;
import com.annaru.queue.model.SysFile;
import com.annaru.queue.result.PageParams;
import com.annaru.queue.service.ISysFileService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Override
    public List<SysFile> selectFileListByTableIdAndRecordId(Map<String, Object> params) {
        return this.baseMapper.selectFileListByTableIdAndRecordId(params);
    }

    @Override
    public PageParams selectSysFilePage(Map<String, Object> params) {
        PageParams pageParams = new PageParams(params);
        IPage<SysFile> iPage = this.baseMapper.selectSysFilePage(pageParams, pageParams.getRequestMap());
        return new PageParams(iPage);
    }
}
