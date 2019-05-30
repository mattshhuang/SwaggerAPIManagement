package com.ustc.apimslogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录并删除cookie
 * @author matthew huang
 * @description
 * @date 2019/5/27 1:21 PM
 */
@Controller
@RequestMapping("/clear")
public class LogoutController {

    private String target = "http://www.huaweiapims.com:8888/index";

    @GetMapping
    public String toLogout(HttpServletRequest request, HttpServletResponse response) {

        Cookie [] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("TOKEN".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:" + target;
    }
}
