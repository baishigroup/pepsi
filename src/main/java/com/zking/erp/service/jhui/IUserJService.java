package com.zking.erp.service.jhui;

import com.zking.erp.model.jhui.User;

public interface IUserJService {

    /**
     * 判断用户名是否符合登录条件
     *
     * @param username 用户名 String password
     * @return int 1、用户名不存在  2、密码不正确  3、黑名单用户  4、符合条件  5、访问后台异常
     */
    int validateUser(String username, String password) ;

    /**
     * 获取用户信息
     *
     * @param username
     * @return 用户信息
     *
     */
    public User getUser(String username)  ;

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    int updateById(User user);

}
