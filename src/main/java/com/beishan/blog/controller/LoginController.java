package com.beishan.blog.controller;

import com.alibaba.fastjson.JSON;
import com.beishan.blog.bean.User;
import com.beishan.blog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String flag = "error";
        User us = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        if (us != null) {
            flag = "ok";
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("flag", flag);
        res.put("user", us);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }
}
