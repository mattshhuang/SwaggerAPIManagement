package com.ustc.apimsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 单点登录
 * @author matthew huang
 * @description
 * @date 2019/5/26 8:49 PM
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    // 通过HTTP请求其他的网络资源，注入RestTemplate
    private RestTemplate restTemplate = new RestTemplate();
    private final String LOGIN_INFO_ADDRESS = "http://login.huaweiapims.com:9999/login/info?token=";

    @RequestMapping("/index")
    public String toMainPage(@CookieValue(required = false, value = "TOKEN") Cookie cookie, HttpSession session) {
        if (cookie != null) {
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)) {
                Map result = restTemplate.getForObject(LOGIN_INFO_ADDRESS + token, Map.class);
                session.setAttribute("loginUser", result);
            }
        }
        return "index";
    }

//    @RequestMapping("clear")
//    public String toLogout(HttpServletRequest request, HttpServletResponse response) {
//        Cookie [] cookies = request.getCookies();
//        for (Cookie cookie: cookies) {
//            if ("TOKEN".equals(cookie.getName())) {
//                cookie.setValue(null);
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//        return "index";
//    }
}
