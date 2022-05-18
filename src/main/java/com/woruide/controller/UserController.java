package com.woruide.controller;

import com.woruide.entity.User;
import com.woruide.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.html";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";

    }
    @RequestMapping("/login")
    public String login(String username, String password) {
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            System.out.println("登录成功！！！");
            return "redirect:/index.html";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户错误！！！");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
        }
        return "redirect:/login.html";
    }
}
