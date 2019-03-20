package org.wlgzs.manipulation.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.manipulation.base.BaseController;
import org.wlgzs.manipulation.service.LoginService;
import org.wlgzs.manipulation.util.Result;

import javax.servlet.http.HttpSession;

/**
 * @author: 胡亚星
 * @createTime 2019-03-19 10:08
 * @description:
 **/
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    //跳转到登录页面
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }

    //登录
    @RequestMapping("/login")
    public ModelAndView login(Model model,HttpSession session, @RequestParam("userPhone") String userPhone, @RequestParam("userPassword") String userPassword) {
        Result result = loginService.login(session,userPhone,userPassword);
        if(result.getCode() == 0){//成功
            model.addAttribute("msg","登录成功！");
            return new ModelAndView("index");
        }
        model.addAttribute("msg","账号或密码错误！");
        return new ModelAndView("redirect:/login/toLogin");
    }
}
