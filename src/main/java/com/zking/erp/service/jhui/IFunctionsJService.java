package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
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


}