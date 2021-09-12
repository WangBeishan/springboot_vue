package com.beishan.blog.controller;

import com.alibaba.fastjson.JSON;
import com.beishan.blog.bean.MainMenu;
import com.beishan.blog.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @GetMapping("/menus")
    public String getAllMenus() {
        System.out.println("SUCCESS!");
        HashMap<String, Object> data = new HashMap<>();
        List<MainMenu> menus = menuDao.getMenus();
        if (menus != null) {
            data.put("menus", menus);
            data.put("flag", 200);
        } else {
            data.put("flag", 400);
        }
        System.out.println(data);
        return JSON.toJSONString(data);
    }
}
