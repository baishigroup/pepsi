package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.FunctionsMapperJ;
import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.service.jhui.IFunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionsServiceImpl implements IFunctionsService {

    @Autowired
    private FunctionsMapperJ functionsMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Functions> queryMenuByPNumber(FunctionsVo functionsVo) {
        return functionsMapper.queryMenuByPNumber(functionsVo);
    }
}
