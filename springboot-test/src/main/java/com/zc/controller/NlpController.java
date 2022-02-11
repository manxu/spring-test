package com.zc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.service.UserService;
import com.zc.util.Sample;

@Controller
public class NlpController {
    private static Logger logger = LoggerFactory.getLogger(NlpController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/nlp")
    public String index() {
        return "nlp";
    }


    @RequestMapping("/fx")
    @ResponseBody
    public Object fx(@RequestParam String content, @RequestParam String type) {
        if(type.equals("1")){
            return Sample.ex(content);
        }
        return Sample.dh(content);
    }


    @RequestMapping("/yu")
    @ResponseBody
    public Object yu(@RequestParam String content,@RequestParam String spd,
                     @RequestParam String pit,
                     @RequestParam String vol,
                     @RequestParam String per) {
        return Sample.yu(content, spd, pit, vol, per);
    }
}
