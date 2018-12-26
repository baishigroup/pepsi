package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.UserBusinessMapperJ;
import com.zking.erp.model.jhui.UserBusiness;
import com.zking.erp.service.jhui.IUserBusinessJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserBusinessJServiceImpl implements IUserBusinessJService {

    @Autowired
   private UserBusinessMapperJ userBusinessMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserBusiness> queryUserBusinessByTypeByKeyId(UserBusiness userBusiness) {
        return userBusinessMapper.queryUserBusinessByTypeByKeyId(userBusiness);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkIsUserBusinessExist(String TypeName, String TypeVale, String KeyIdName, String KeyIdValue, String UBName, String UBValue) {
        List<UserBusiness> dataList= userBusinessMapper.checkIsUserBusinessExist(TypeName,TypeVale,KeyIdName,KeyIdValue,UBName,UBValue);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }

    @Override
    public UserBusiness queryUserBusinessById(String id) {
        return userBusinessMapper.queryUserBusinessById(id);
    }

    @Override
    public int insert(UserBusiness record) {
        return userBusinessMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(UserBusiness record) {
        return userBusinessMapper.updateByPrimaryKey(record);
    }
}
