package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.InoutItem;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IInoutItemJService {

    /**
     * 查找收支项目信息-下拉框
     * @param inoutItem
     * @return
     */
    List<InoutItem> queryInoutItemBySelect(InoutItem inoutItem);

}