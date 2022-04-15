package com.wm.controller;

import com.alibaba.fastjson.JSON;
import com.wm.dto.PageReq;
import com.wm.util.ComUtil;
import com.wm.util.HttpUtil;
import com.wm.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("user")
public class User1Controller {
    private static Logger logger = LoggerFactory.getLogger(User1Controller.class);

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestBody PageReq<Map<String,Object>> req) {
        String s = HttpUtil.sendPost(HttpUtil.QUERY, HttpUtil.getQueryStr("m_user", req, null));
        return ComUtil.convertRes(s);
    }


}
