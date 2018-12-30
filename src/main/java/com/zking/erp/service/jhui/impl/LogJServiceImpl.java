package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.LogMapperJ;
import com.zking.erp.model.jhui.Log;
import com.zking.erp.service.jhui.ILogJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LogJServiceImpl implements ILogJService {

    @Autowired
    private LogMapperJ logMapperJ;

    @Override
    public List<Log> queryLogByLikePager(PageBean pageBean, Log log) {
        return logMapperJ.queryLogByLikePager(log);
    }

    @Override
    public int create(Log record) {
        record.setId(UUID.randomUUID().toString());
        return logMapperJ.insert(record);
    }
}
