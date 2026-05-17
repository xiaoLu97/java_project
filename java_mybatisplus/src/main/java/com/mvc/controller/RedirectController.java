package com.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.subject.Subject;

@Controller
@RequestMapping("/auth")
public class RedirectController {
    @GetMapping("/{url}")
    public String url(@PathVariable("url") String url) {
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            // ↑ Shiro 会调用 request.getSession()
            // ↑ Tomcat 检测到没有 Session，自动创建一个
            // ↑ 生成唯一的 JSESSIONID
            // ↑ Shiro 在 Session 中存储认证信息
            return "main";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "未授权没有访问权限";
    }
}
