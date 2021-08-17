package com.zc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zc.entity.User;

@Repository
public interface UserDao {

    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    /**
     * BeanPropertyRowMapper 字段属性映射，但要名称一致或者标志驼峰模式
     */
    /*public List<User> getUser(){
        return jdbcTemplate.query("select user_name,age from t_users",
                new BeanPropertyRowMapper<User>(User.class));
    }*/


    public List<User> getUser();
}
