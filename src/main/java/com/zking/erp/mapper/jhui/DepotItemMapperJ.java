package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.DepotItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotItemMapperJ {

    //库存数量查询
    List<DepotItem> queryDepotItemByMaterialId(DepotItem depotItem);
    List<DepotItem> queryByType(String type, String ProjectId, String MId, String MonthTime, Integer isPrev);

    List<DepotItem> queryDepotJoinTable(DepotItem depotItem);

    int insert(DepotItem record);

    DepotItem selectById(String id);

    int updateById(DepotItem record);

    int deleteById(String id);

    /**
     * 根据材料信息获取
     * @param materialParam
     * @param depotIds
     * @return
     */
    List<DepotItem> queryHeaderIdByMaterial(String materialParam, String[] depotIds);


}