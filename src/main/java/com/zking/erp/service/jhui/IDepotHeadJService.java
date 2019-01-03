package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.DepotHead;
import com.zking.erp.model.jhui.DepotItem;
import com.zking.erp.util.PageBean;

import java.util.List;

public interface IDepotHeadJService {

    int deleteById(String id);

    int insert(DepotHead record);

    DepotHead selectById(String id);

    int updateById(DepotHead record);

    int delete(DepotHead depotHead);

    /**
     * 检查输入名称是否存在
     * @return
     */
    Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID);

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

    /**
     * 根据材料信息获取
     * @param materialParam
     * @param depotIds
     * @return
     */
    List<DepotItem> queryHeaderIdByMaterial(String materialParam, String depotIds);

    List<DepotHead> queryDepotHeadPager(PageBean pageBean,DepotHead depotHead);

    List<DepotHead> queryMaterialsListByHeaderId(DepotHead depotHead);

    /**
     * 查找单据_根据月份(报表)
     *
     * @return
     */
    List<DepotHead> queryDepotHeadByMonth(DepotHead depotHead);


}
