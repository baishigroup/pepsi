package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.FunctionsMapperJ;
import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.service.jhui.IFunctionsJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionsJServiceImpl implements IFunctionsJService {

    @Autowired
    private FunctionsMapperJ functionsMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Functions> queryMenuByPNumber(FunctionsVo functionsVo) {
        return functionsMapper.queryMenuByPNumber(functionsVo);
    }

    @Override
    public List<Functions> queryFunctionsByRole(Functions functions) {
        return functionsMapper.queryFunctionsByRole(functions);
    }

    @Override
    public List<Functions> queryFunctionByIds(Functions functions) {
        String[] split = functions.getFunctionsIDs().split(",");
        functions.setIds(split);
        return functionsMapper.queryFunctionByIds(functions);
    }
}
