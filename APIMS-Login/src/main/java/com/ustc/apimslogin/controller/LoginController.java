package com.ustc.apimslogin.controller;

import com.ustc.apimslogin.bean.User;
import com.ustc.apimslogin.utils.LoginCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author matthew huang
 * @description
 * @date 2019/5/26 9:40 PM
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    // 本地化DB
    private static Set<User> dbUsers;
    static {
        dbUsers = new HashSet<>();
        dbUsers.add(new User(0, "hu", "123"));
        dbUsers.add(new User(1, "hua", "123"));
        dbUsers.add(new User(2, "huan", "123"));
        dbUsers.add(new User(3, "huang", "123"));
    }

    @PostMapping
    public String toLogin(HttpSession session, User user, HttpServletResponse response) {
        // 删除session中上一次登录出错的提示
        session.removeAttribute("msg");

        String target = StringUtils.toString(session.getAttribute("target"));
        // 模拟从数据库中通过登录的用户名和密码去查找数据库中的用户
        Optional<User> first = dbUsers.stream().filter(dbUsers -> dbUsers.getUsername().equals(user.getUsername()) &&
                                                       dbUsers.getPassword().equals(user.getPassword()))
                                               .findFirst();

        // 判断用户是否登录
        if (first.isPresent()) {
            // 保存用户登录信息
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("TOKEN", token);
            // 能在子系统中访问，需要域名相同，但是域名设为www.huaweiapims.com时无法登陆！！！
            cookie.setDomain("huaweiapims.com");
            cookie.setMaxAge(60); // 负值为浏览器进程Cookie(内存中保存)，关闭浏览器就失效
            response.addCookie(cookie);
            LoginCache.loginUsers.put(token, first.get());
        } else {
            // 登录失败
            session.setAttribute("msg", "用户名或密码错误");
            return "login";
        }
        System.out.println(target);
        return "redirect:" + target;
    }

    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token) {
        if (!StringUtils.isEmpty(token)) {
            User user = LoginCache.loginUsers.get(token);
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>((User) null, HttpStatus.BAD_REQUEST);
        }
    }
}
