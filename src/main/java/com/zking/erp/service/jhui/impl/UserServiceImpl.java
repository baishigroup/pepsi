package com.zking.erp.service.jhui.impl;

import com.zking.erp.mapper.jhui.UserMapperJ;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapperJ userMapperJ;

    @Transactional(readOnly = true)
    @Override
    public int validateUser(String username, String password) {
        try {
            User u = new User();
            try {
                User user=new User();
                user.setLoginame(username);
                  u=userMapperJ.queryByLogin(user);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(">>>>>>>>访问验证用户姓名是否存在后台信息异常");
                return 5;//访问数据库异常
            }

            if (null == u || null==u.getLoginame())
                return 1;//用户不存在

            try {
                User user=new User();
                user.setLoginame(username);
                user.setPassword(password);
                u = userMapperJ.queryByLogin(user);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(">>>>>>>>>>访问验证用户密码后台信息异常");
                return 5;//访问数据库异常
            }

            if (null == u || null==u.getLoginame())
                return 2;//用户密码错误
            return 4;//可以登录
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(String username) {
        User user=new User();
        user.setLoginame(username);
        User user1 = userMapperJ.queryByLogin(user);
//        if (null != user1 && null!=user1.getLoginame()) {
            return user1;
//        else
//            return
//        }
    }
}
