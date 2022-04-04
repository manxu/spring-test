package com.wm.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class PageRes {

    private Integer code;
    private String msg;
    private Result data;

    @Data
    public static class Result {
        private Integer total;
        private List<JSONObject> items;
    }



}
