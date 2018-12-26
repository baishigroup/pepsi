package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.RoleMapperJ;
import com.zking.erp.model.jhui.Role;
import com.zking.erp.service.jhui.IRoleJService;
import com.zking.erp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleJServiceImpl implements IRoleJService {

    @Autowired
    private RoleMapperJ roleMapperJ;

    @Transactional(readOnly = true)
    @Override
    public List<Role> queryRolePager(Role role, PageBean pageBean) {
        return roleMapperJ.queryRolePager(role);
    }

    @Transactional
    @Override
    public int insert(Role record) {
        return roleMapperJ.insert(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Role selectById(String id) {
        return roleMapperJ.selectById(id);
    }

    @Transactional
    @Override
    public int updateById(Role record) {
        return roleMapperJ.updateById(record);
    }
    @Transactional
    @Override
    public int deleteById(String id) {
        return roleMapperJ.deleteById(id);
    }
    @Transactional
    @Override
    public int delete(Role role) {
        String[] split = role.getRoleIDs().split(",");
        role.setIds(split);
        return roleMapperJ.delete(role);
    }
    @Transactional(readOnly = true)
    @Override
    public Boolean checkIsNameExist(String filedName, String filedVale, String idFiled, String objectID) {
        List<Role> dataList = roleMapperJ.checkIsNameExist(filedName, filedVale, idFiled, objectID);
        if (null != dataList && dataList.size() > 0)
            return true;
        return false;
    }
}
