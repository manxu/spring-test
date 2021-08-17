package com.zc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zc.dao.UserDao;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/users")
    @ResponseBody
    public Object getUsers(){
        return userDao.getUser();
    }

}
