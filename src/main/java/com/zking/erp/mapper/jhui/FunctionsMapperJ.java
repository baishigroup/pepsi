package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.Functions;
import com.zking.erp.model.jhui.vo.FunctionsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FunctionsMapperJ {
    /**
     * 根据上级编号查询页面菜单
     * @return
     */
     List<Functions> queryMenuByPNumber(FunctionsVo functionsVo );
}