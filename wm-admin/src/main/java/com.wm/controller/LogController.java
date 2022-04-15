package com.wm.controller;

import com.wm.dto.PageReq;
import com.wm.service.UserService;
import com.wm.util.ComUtil;
import com.wm.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestBody PageReq<Map<String,Object>> req) {
        String s = HttpUtil.sendPost(HttpUtil.QUERY, HttpUtil.getQueryStr("login_log", req, new String[]{"time", "desc"}));
        return ComUtil.convertRes(s);
    }


    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(@RequestBody Map<String,Object> request) {
        try {
            String noTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            String no = request.get("no").toString();
            String userId = request.get("userId").toString();
            String amount = request.get("amount").toString();
            String fl = request.get("fl").toString();
            String source = request.get("source").toString();
            return userService.handlerOrder(userId, no, amount, noTime, fl, source);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

}
