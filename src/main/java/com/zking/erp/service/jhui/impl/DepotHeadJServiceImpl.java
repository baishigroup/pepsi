package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.DepotHeadMapperJ;
import com.zking.erp.mapper.jhui.DepotItemMapperJ;
import com.zking.erp.model.jhui.DepotHead;
import com.zking.erp.model.jhui.DepotItem;
import com.zking.erp.service.jhui.IDepotHeadJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DepotHeadJServiceImpl implements IDepotHeadJService {

    @Autowired
    private DepotHeadMapperJ depotHeadMapperJ;
    @Autowired
    private DepotItemMapperJ depotItemMapperJ;

    @Override
    public int deleteById(String id) {
        return depotHeadMapperJ.deleteById(id);
    }

    @Override
    public int insert(DepotHead record) {
        return depotHeadMapperJ.insert(record);
    }

    @Override
    public DepotHead selectById(String id) {
        return depotHeadMapperJ.selectById(id);
    }

    @Override
    public int updateById(DepotHead record) {
        return depotHeadMapperJ.updateById(record);
    }

    @Override
    public int delete(DepotHead depotHead) {
        String[] split = depotHead.getDepotHeadIDs().split(",");
        depotHead.setIds(split);
        return depotHeadMapperJ.delete(depotHead);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID) {
        List<DepotHead> dataList = depotHeadMapperJ.checkIsNameExist(filedName, filedVale, idFiled, objectID);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }

    @Override
    public int updatebatchStatus(DepotHead depotHead) {
        String[] split = depotHead.getDepotHeadIDs().split(",");
        depotHead.setIds(split);
        return depotHeadMapperJ.updatebatchStatus(depotHead);
    }

    @Override
    public List<DepotHead> queryNumberByTypeByTime(DepotHead depotHead) {
        return depotHeadMapperJ.queryNumberByTypeByTime(depotHead);
    }

    @Override
    public List<DepotItem> queryHeaderIdByMaterial(String materialParam, String depotIds) {
        String[] split = depotIds.split(",");
        return depotItemMapperJ.queryHeaderIdByMaterial(materialParam,split);
    }

    @Override
    public List<DepotHead> queryDepotHeadPager(PageBean pageBean, DepotHead depotHead) {
        if(null!=depotHead.getDhIds()&&""!=depotHead.getDhIds()) {
            String[] split = depotHead.getDhIds().split(",");
            depotHead.setIds(split);
        }
        return depotHeadMapperJ.queryDepotHeadPager(depotHead);
    }

    @Override
    public List<DepotHead> queryMaterialsListByHeaderId(DepotHead depotHead) {
        return depotHeadMapperJ.queryMaterialsListByHeaderId(depotHead);
    }

    @Override
    public List<DepotHead> queryDepotHeadByMonth(DepotHead depotHead) {
        return depotHeadMapperJ.queryDepotHeadByMonth(depotHead);
    }


}
