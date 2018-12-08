package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/portal/user/")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     *登录
     */
    @RequestMapping(value = "login.do")
    public ServerResponse login(String username, String password, HttpSession session){

        ServerResponse serverResponse =  userService.login(username,password);
        if(serverResponse.isSuccess()){
            //将用户信息保存到session中，购物车需要
            session.setAttribute(Const.CURRENTUSER,serverResponse.getData());
        }
        return serverResponse;
    }

    /**
     *注册
     */
    @RequestMapping(value = "register.do")
    public ServerResponse register(UserInfo userInfo){
        return userService.register(userInfo);
    }


    /**
     * 检查用户名是否有效
     */
    @RequestMapping(value = "check_valid.do")
    public ServerResponse check_valid(String str,String type){
        return userService.check_valid(str, type);
    }

    /**
     * 获取登录用户信息
     */
    @RequestMapping(value = "get_user_info.do")
    public ServerResponse get_user_info(HttpSession session){
        Object o =  session.getAttribute(Const.CURRENTUSER);
        if(o!=null && o instanceof UserInfo){
            UserInfo userInfo = (UserInfo)o;
            UserInfo responseUserInfo = new UserInfo();
            responseUserInfo.setId(userInfo.getId());
            responseUserInfo.setUsername(userInfo.getUsername());
            responseUserInfo.setEmail(userInfo.getEmail());
            responseUserInfo.setCreateTime(userInfo.getCreateTime());
            responseUserInfo.setUpdateTime(userInfo.getUpdateTime());
            return ServerResponse.createServerResponseBySuccess(null,responseUserInfo);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 获取当前登录用户的详细信息
     */
    @RequestMapping(value = "get_information.do")
    public ServerResponse get_information(HttpSession session){
        Object o =  session.getAttribute(Const.CURRENTUSER);
        if(o!=null && o instanceof UserInfo){
            UserInfo userInfo = (UserInfo)o;

            return ServerResponse.createServerResponseBySuccess(null,userInfo);
        }
        return ServerResponse.createServerResponseByError(ResponseCode.USER_NOT_LOGIN.getStatus(),ResponseCode.USER_NOT_LOGIN.getMsg());
    }

    /**
     * 根据用户名获取密保问题
     */
    @RequestMapping(value = "forget_get_question.do")
    public ServerResponse forget_get_question(String username){
        return userService.forget_get_question(username);
    }

    /**
     * 提交问题答案
     */
    @RequestMapping(value = "forget_check_answer.do")
    public ServerResponse forget_check_answer(String username, String question, String answer){
        return userService.forget_check_answer(username,question,answer);
    }

    /**
     * 忘记密码的重置密码
     */
    @RequestMapping(value = "forget_reset_password.do")
    public ServerResponse forget_reset_password(String username,String passwordNew,String forgetToken){
        return userService.forget_reset_password(username,passwordNew,forgetToken);
    }
}
