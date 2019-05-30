package com.ustc.apimslogin.utils;

import com.ustc.apimslogin.bean.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author matthew huang
 * @description
 * @date 2019/5/26 9:54 PM
 */
public class LoginCache {

    public static Map<String, User> loginUsers = new HashMap<>();
}
