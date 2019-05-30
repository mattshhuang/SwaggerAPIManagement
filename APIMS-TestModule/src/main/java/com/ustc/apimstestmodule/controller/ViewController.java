package com.ustc.apimstestmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @author matthew huang
 * @description
 * @date 2019/5/26 9:13 PM
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    // 通过HTTP请求其他的网络资源，注入RestTemplate
    private RestTemplate restTemplate = new RestTemplate();
    private final String LOGIN_INFO_ADDRESS = "http://login.huaweiapims.com:9999/login/info?token=";

    @GetMapping("/index")
    public String toIndex(@CookieValue(required = false, value = "TOKEN") Cookie cookie, HttpSession session) {
        if (cookie != null) {
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)) {
                // 请求"/info"接口
                Map result = restTemplate.getForObject(LOGIN_INFO_ADDRESS + token, Map.class);
                session.setAttribute("loginUser", result);
            }
        }
        return "index";
    }
}
