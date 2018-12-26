package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.InoutItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InoutItemMapperJ {

    /**
     * 查找收支项目信息-下拉框
     * @param inoutItem
     * @return
     */
    List<InoutItem> queryInoutItemBySelect(InoutItem inoutItem);

}