package com.zking.erp.controller.jhui;

import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IUserService;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private HttpSession getSession;

    /**
     * 需要判断用户状态，用户名密码错误不能登录 ，黑名单用户不能登录，如果已经登录过，不再进行处理，直接进入管理页面
     *
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, User user) {
        try {
            getSession = request.getSession();
            String message = "";
            System.out.println("============用户登录 login 方法调用开始==============");
            String username = user.getLoginame().trim();
            String password = user.getPassword().trim();
            //因密码用MD5加密，需要对密码进行转化
            try {
                password = Tools.md5Encryp(password);
                System.out.println(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                System.out.println(">>>>>>>>>>>>>>转化MD5字符串错误 ：");
            }

            //判断用户是否已经登录过，登录过不再处理
            if (null != getSession.getAttribute("user")) {
                User sessionUser = (User) getSession.getAttribute("user");
                if (null != sessionUser && username.equalsIgnoreCase(sessionUser.getLoginame())
                        && sessionUser.getPassword().equals(password)) {
                    message = "user already login";
                    System.out.println("====用户 " + username + "已经登录过, login 方法调用结束====");
                    /*return "login";*/
                }
            }

            //获取用户状态
            int userStatus = -1;
            try {
                userStatus = userService.validateUser(username, password);
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>>用户  " + username + " 登录 login 方法 访问服务层异常====");
                message = "access service exception";
                e.printStackTrace();
            }
            switch (userStatus) {
                case 1:
                    message = "user is not exist";
                    System.out.println("user is not exist");
                    break;
                case 2:
                    message = "user password error";
                    System.out.println("user password error");
                    break;
                case 3:
                    message = "user is black";
                    System.out.println("user is black");
                    break;
                case 5:
                    message = "access service error";
                    System.out.println("access service error");
                    break;
                default:
                    try {
                        //验证通过 ，可以登录，放入session，记录登录日志
                        User uu = userService.getUser(username);
//                    logService.create(new Logdetails(user, "登录系统", model.getClientIp(),
//                            new Timestamp(System.currentTimeMillis()), (short) 0, "管理用户：" + username + " 登录系统", username + " 登录系统"));
                        message = "user can login";
                        getSession.setAttribute("user", uu);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(">>>>>>>>>>>>>>>查询用户名为:" + username + " ，用户信息异常");
                    }
                    break;
            }
		/*if(ExceptionCodeConstants.UserExceptionCode.USER_CONDITION_FIT == userStatus)
		    return "login";*/
            System.out.println("===============用户登录 login 方法调用结束===============");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("message",message);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
