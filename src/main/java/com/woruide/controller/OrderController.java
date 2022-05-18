package com.woruide.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("save")
    public String save() {
        //基于角色
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //代码方式
        if (subject.hasRole("admin")) {
            System.out.println("保存订单!");
        }else{
            System.out.println("无权访问!");
        }
        System.out.println("进入save方法============");
        return "redircet:/index.jsp";
    }
}
