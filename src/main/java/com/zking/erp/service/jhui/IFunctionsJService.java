package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import com.zking.erp.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFunctionsJService {
    /**
     * 根据上级编号查询页面菜单
     * @return
     */
     List<Functions> queryMenuByPNumber(FunctionsVo functionsVo);

    List<Functions> queryFunctionsByRole(Functions functions);

    List<Functions> queryFunctionByIds(Functions functions);

    List<Functions>  queryFunctionByLikePager(PageBean pageBean,Functions functions);

    Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

    int insert(Functions functions);

    Functions selectById(String id);

    int updateById(Functions record);

    int deleteById(String id);

    int delete(Functions functions);



}