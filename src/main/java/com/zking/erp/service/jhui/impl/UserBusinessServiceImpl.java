package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.UserBusinessMapperJ;
import com.zking.erp.model.jhui.UserBusiness;
import com.zking.erp.service.jhui.IUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserBusinessServiceImpl implements IUserBusinessService {

    @Autowired
   private UserBusinessMapperJ userBusinessMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserBusiness> queryUserBusinessByTypeByKeyId(UserBusiness userBusiness) {
        return userBusinessMapper.queryUserBusinessByTypeByKeyId(userBusiness);
    }
}
