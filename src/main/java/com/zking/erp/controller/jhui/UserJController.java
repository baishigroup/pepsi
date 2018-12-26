package com.zking.erp.controller.jhui;

import com.zking.erp.base.BaseController;
import com.zking.erp.model.jhui.User;
import com.zking.erp.service.jhui.IUserJService;
import com.zking.erp.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserJController extends BaseController{

    @Autowired
    private IUserJService userService;

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

    /**
     * 用户退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,User user) {
        getSession = request.getSession();
//        logService.create(new Logdetails(getUser(), "退出系统", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis()), (short) 0,
//                "管理用户：" + getUser().getLoginame() + " 退出系统", getUser().getLoginame() + " 退出系统"));
        getSession.removeAttribute("user");
        return "pages/common/admin";
    }



    /**
     * 修改密码
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public Map<String,Object> updatePwd(HttpServletRequest request,User model) {
        getSession = request.getSession();
        Map<String,Object> map=new HashMap<String, Object>();
        Integer flag = 0;//1.修改成功  2.原始密码错误（失败）  3.修改失败
        try {
            //获取当前登陆用户的信息
            User user =(User)getSession.getAttribute("user");
            //原始密码
            String orgPassword = Tools.md5Encryp(model.getOrgpwd());
            //修改后的密码
            String md5Pwd = Tools.md5Encryp(model.getPassword());
            //必须和原始密码一致才可以更新密码
//            if(user.getLoginame().equals("jsh")){
//                flag = 3;
//                tipMsg = "管理员jsh不能修改密码";
//                tipType = 1;
//            } else
            //判断输入的原始密码是否与真实原始密码相等
            if (orgPassword.equalsIgnoreCase(user.getPassword())) {

                user.setPassword(md5Pwd);
                userService.updateById(user);

                //看是否需要更新seesion中user
//                if(getUser().getId() == model.getUserID())
//                {
//                    getSession().put("user", user);
//                }

                flag = 1;
                tipMsg = "成功";
                tipType = 0;
            } else {
                flag = 2;
                tipMsg = "失败";
                tipType = 1;
            }

        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>修改用户ID为 ： " + model.getId() + "密码信息失败");
            flag = 3;
            tipMsg = "失败";
            tipType = 1;
            e.printStackTrace();
        } finally {
            try {
                map.put("flag",flag);
                return map;
            } catch (Exception e) {
                System.out.println(">>>>>>>>>>>>修改用户密码回写客户端结果异常");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
//        logService.create(new Logdetails(getUser(), "更新用户", model.getClientIp(),
//                new Timestamp(System.currentTimeMillis())
//                , tipType, "更新用户ID为  " + model.getUserID() + "密码信息 " + tipMsg + "！", "更新用户" + tipMsg));
    }





}
