package com.ustc.apimslogin.controller;

import com.ustc.apimslogin.bean.User;
import com.ustc.apimslogin.utils.LoginCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * 页面跳转的逻辑
 * @author matthew huang
 * @description
 * @date 2019/5/26 5:37 PM
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    /**
     * 跳转到login页面
     * @return
     */
    @GetMapping("/login")
    public String tologin(@RequestParam(required = false, defaultValue = "") String target,
                          HttpSession session,
                          @CookieValue(required = false, value = "TOKEN")Cookie cookie) {
        // 直接访问登录页面时，添加target
        if (StringUtils.isEmpty(target)) {
            target = "http://www.huaweiapims.com:8888/index";
        }

        // 如果已经登录的用户再次访问登录系统时，直接重定向
        if (cookie != null) {
            User user = LoginCache.loginUsers.get(cookie.getValue());
            if (user != null) {
                return "redirect:" + target;
            }
        }

        // TODO:要做target做合法校验
        // 重定向地址
        session.setAttribute("target", target);
        return "login";
    }

    /**
     * 退出登录并删除LoginCache记录，这里并没有删除cookie
     * @return
     */
    @GetMapping("/logout")
    public String toLogout(@CookieValue(required = false, value = "TOKEN")Cookie cookie) {
        LoginCache.loginUsers.remove(cookie.getValue());
        cookie.setMaxAge(0);
        return "login";
    }
}
