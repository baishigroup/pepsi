package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.InoutItemMapperJ;
import com.zking.erp.model.jhui.InoutItem;
import com.zking.erp.service.jhui.IInoutItemJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InoutItemJServiceImpl implements IInoutItemJService {

    @Autowired
    InoutItemMapperJ inoutItemMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<InoutItem> queryInoutItemBySelect(InoutItem inoutItem) {
        return inoutItemMapperJ.queryInoutItemBySelect(inoutItem);
    }
}
