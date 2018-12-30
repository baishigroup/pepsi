package com.zking.erp.base;


import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.ILogJService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * struts2工具类
 *
 * @author jishenghua qq752718920
 * struts2 base action 一些常用方法获取
 */
@SuppressWarnings("serial")
public abstract class BaseController   {

    @Autowired
    public ILogJService logService;


    /**
     * 操作日志使用 是否成功表示
     */
    public String tipMsg = "成功";

    /**
     * 操作日志使用 是否成功表示 0 ==成功 1==失败
     */
    public int tipType = 0;

    /**
     * 登录用户信息
     *
     * @return 登录用户对象
     */
    public String getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getId();
    }

    public User getUser2(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }





}
