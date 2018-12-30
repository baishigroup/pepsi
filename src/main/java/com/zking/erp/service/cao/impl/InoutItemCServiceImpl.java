package com.zking.erp.service.cao.impl;

import com.zking.erp.mapper.cao.InoutItemMapperC;
import com.zking.erp.model.cao.InoutItem;
import com.zking.erp.service.cao.IInoutItemCService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InoutItemCServiceImpl implements IInoutItemCService {

    @Autowired
    private InoutItemMapperC inoutItemMapperC;

    @Override
    public int insertInoutItem(InoutItem inoutItem) {
        return inoutItemMapperC.insertInoutItem(inoutItem);
    }

    @Override
    public int updateInoutItemById(InoutItem inoutItem) {
        return inoutItemMapperC.updateInoutItemById(inoutItem);
    }

    @Override
    public int deleteInoutItemById(String id) {
        return inoutItemMapperC.deleteInoutItemById(id);
    }

    @Override
    public int deleteInoutItemByIds(InoutItem inoutItem) {
        return inoutItemMapperC.deleteInoutItemByIds(inoutItem);
    }

    @Override
    public List<InoutItem> queryInoutItemPager(InoutItem inoutItem, PageBean pageBean) {
        return inoutItemMapperC.queryInoutItemPager(inoutItem);
    }

    @Override
    public Boolean checkIsNameExist(String filedName, String filedValue, String idFiled, String objectID) {
        List<InoutItem> dataList = inoutItemMapperC.checkIsNameExist(filedName, filedValue, idFiled, objectID);
        if(null != dataList&& dataList.size()>0)
            return true;
        return false;
    }

    @Override
    public List<InoutItem> queryInoutItemBySelect(InoutItem inoutItem) {
        return inoutItemMapperC.queryInoutItemBySelect(inoutItem);
    }
}
