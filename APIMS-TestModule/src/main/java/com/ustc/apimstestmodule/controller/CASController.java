package com.ustc.apimstestmodule.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author matthew huang
 * @description
 * @date 2019/6/3 6:16 PM
 */
@Controller
public class CASController {

    @Value("${casClientLogoutUrl}")
    //http://cas.server.com:9100/cas/logout?service=http://cas.clienttest.com:9001/logout/success
    private String clientLogoutUrl;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "CAS clien");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();//销毁session
        //使用cas退出成功后,跳转到http://cas.clienttest.com:9001/logout/success
        return "redirect:" + clientLogoutUrl;
    }

    @RequestMapping("/logoutsuccess")
    public String logoutsuccess(HttpSession session) {
        return "redirect:index";
    }
}
