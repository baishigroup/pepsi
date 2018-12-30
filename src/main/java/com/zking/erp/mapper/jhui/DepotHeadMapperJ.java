package com.zking.erp.mapper.jhui;

import com.zking.erp.model.jhui.DepotHead;
import com.zking.erp.model.jhui.Depot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepotHeadMapperJ {
    int deleteById(String id);

    int insert(DepotHead record);

    DepotHead selectById(String id);

    int updateById(DepotHead record);

    int delete(DepotHead depotHead);

    /**
     * 检查输入名称是否存在
     * @return
     */
    List<DepotHead> checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

    /**
     * 批量审核/反审核
     * @param depotHead
     * @return
     */
    int updatebatchStatus(DepotHead depotHead);

    /**
     * 根据时间，出入库类型查询（最大单据号）
     */
    List<DepotHead> queryNumberByTypeByTime(DepotHead depotHead);

    List<DepotHead> queryDepotHeadPager(DepotHead depotHead);

    List<DepotHead> queryMaterialsListByHeaderId(DepotHead depotHead);

}