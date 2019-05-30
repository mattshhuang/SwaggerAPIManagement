package com.ustc.apimsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author matthew huang
 * @description
 * @date 2019/5/25 5:08 PM
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "view/welcome";
    }

    @RequestMapping("/curl")
    public String curl() { return "view/curl"; }

    @RequestMapping("/postman")
    public String postman(){
        return "view/postman";
    }

}
