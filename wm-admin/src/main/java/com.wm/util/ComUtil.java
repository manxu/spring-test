package com.wm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wm.dto.PageRes;

import java.util.ArrayList;
import java.util.List;

public class ComUtil {


    public static String toJSONString(Object a) {
        if (a == null) {
            return "{}";
        }
        return JSON.toJSONString(a);
    }

    //转换为返回
    public static Object convertRes(String s) {
        PageRes res = new PageRes();
        JSONObject c = JSON.parseObject(s);
        Integer code = c.getInteger("errcode");
        res.setCode(code==0?20000:code);
        res.setMessage(c.getString("errmsg"));

        if(code == 0) {

            PageRes.Result data = new PageRes.Result();
            data.setTotal(c.getJSONObject("pager").getInteger("Total"));
            JSONArray data1 = c.getJSONArray("data");
            if(data1 != null && data1.size() >0) {
                List<JSONObject> its = new ArrayList<>();
                for (Object o : data1) {
                    its.add(JSON.parseObject(o.toString()));
                }
                data.setItems(its);
            }
            res.setData(data);
        }

        return res;

    }

}
