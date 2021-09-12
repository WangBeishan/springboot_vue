package com.beishan.blog.controller;

import com.alibaba.fastjson.JSON;
import com.beishan.blog.bean.QueryInfo;
import com.beishan.blog.bean.User;
import com.beishan.blog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/allUser")
    public String getUserList(QueryInfo queryInfo) {
        int number = userDao.getUserCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<User> users = userDao.getAllUser("%" + queryInfo.getQuery() + "%",
                            pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("number", number);
        res.put("data", users);

        return JSON.toJSONString(res);
    }

    @PutMapping("/userState")
    public String updateUserState(@RequestParam("id") Integer id,
                                  @RequestParam("state") Boolean state) {
        int i = userDao.updateState(id, state);
        return i > 0 ? "success" : "error";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        user.setRole("general user");
        user.setState(false);
        int i = userDao.addUser(user);
        return i > 0 ? "success" : "error";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(int id) {
        int i = userDao.deleteUser(id);
        return i > 0 ? "success" : "error";
    }

    @GetMapping("/getUpdateUser")
    public String getUpdateUser(int id) {
        User user = userDao.getUpdateUser(id);
        return JSON.toJSONString(user);
    }

    @PutMapping("/editUser")
    public String editUser(@RequestBody User user) {
        int i = userDao.editUser(user);
        return i > 0 ? "success" : "error";
    }
}
