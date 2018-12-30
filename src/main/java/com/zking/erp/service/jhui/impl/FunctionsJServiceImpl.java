package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.FunctionsMapperJ;
import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.service.jhui.IFunctionsJService;
import com.zking.erp.util.PageBean;
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

    @Override
    public List<Functions> queryFunctionByLikePager(PageBean pageBean,Functions functions) {
        return functionsMapper.queryFunctionByLikePager(functions);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID) {
        List dataList = functionsMapper.checkIsNameExist(filedName,filedVale,idFiled,objectID);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }

    @Override
    public int insert(Functions functions) {
        return functionsMapper.insert(functions);
    }

    @Override
    public Functions selectById(String id) {
        return functionsMapper.selectById(id);
    }

    @Override
    public int updateById(Functions record) {
        return functionsMapper.updateById(record);
    }

    @Override
    public int deleteById(String id) {
        return functionsMapper.deleteById(id);
    }

    @Override
    public int delete(Functions functions) {
        String[] split = functions.getFunctionsIDs().split(",");
        functions.setIds(split);
        return functionsMapper.delete(functions);
    }
}
