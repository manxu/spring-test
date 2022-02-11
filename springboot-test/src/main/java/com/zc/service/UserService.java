package com.zc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zc.util.HttpUtil;
import com.zc.util.MapUtil;

@Transactional
@Service
public class UserService {

    public Map<String,Object> handlerOrder(String userId,String no,String amount,String noTime){
        String fl = new BigDecimal(amount).multiply(new BigDecimal("3")).setScale(0, RoundingMode.DOWN).toPlainString();
        Map<String,Object> result = new MapUtil().com("no", no).com("userId", userId).com("amount", amount+"")
                .map;

        //查询
        String queryOrder = "db.collection('m_order').where({userId:'"+userId+"',no:'"+ no +"'}).get()";
        Map<String,Object> param =  new MapUtil()
                .com("env", HttpUtil.getKey("env"))
                .com("query", queryOrder)
                .map;
        String order = HttpUtil.sendPost(HttpUtil.QUERY, param);
        JSONObject orderJson = JSONObject.parseObject(order);
        final int exist = orderJson.getJSONArray("data").size();
        if(exist>0){
            result.put("code", "-2");
            result.put("msg","已存在");
        }else{
            //查询用户信息
            Map<String,Object> queryUser =  new MapUtil()
                    .com("env", HttpUtil.getKey("env"))
                    .com("query", "db.collection('m_user').where({userId:'"+userId+"'}).get()")
                    .map;
            String user = HttpUtil.sendPost(HttpUtil.QUERY, queryUser);
            JSONObject userJson = JSONObject.parseObject(user);

            String addOrder = "db.collection('m_order').add({data:{" +
                    "no:'"+ no +"', " +
                    "amount:"+amount+"," +
                    "fl:"+ fl +"," +
                    "userId:'"+ userId +"'," +
                    "noTime:'"+ noTime +"'," +
                    "noTime:'"+ noTime +"'," +
                    "_openid:'"+ JSONObject.parseObject(userJson.getJSONArray("data").getString(0)).getString("_openid") +"'" +
                    "}})";
            Map<String,Object> inparam =  new MapUtil()
                    .com("env", HttpUtil.getKey("env"))
                    .com("query", addOrder)
                    .map;
            result.put("code", "0");
            result.put("msg","新增");
            result.put("fl", fl);
            result.put("yue", JSONObject.parseObject(userJson.getJSONArray("data").getString(0)).get("yue"));
            String inOrder = HttpUtil.sendPost(HttpUtil.ADD, inparam);
            JSONObject inOrderJson = JSONObject.parseObject(inOrder);
            if(inOrderJson.getString("errcode").equals("0")){
                String userUpdate = "db.collection('m_user').where({userId:'"+userId+"'}).update({data:{yue:_.inc("+fl+"), zuori:_.inc("+fl+")}})";
                String updateUserResult = HttpUtil.sendPost(HttpUtil.UPDATE, userUpdate);
                JSONObject updateUserResultJson = JSONObject.parseObject(updateUserResult);
                if(updateUserResultJson.getString("errcode").equals("0")){
                    result.put("code", "1");
                    result.put("msg","更新user");
                }
            }
        }
        return result;
    }

}
