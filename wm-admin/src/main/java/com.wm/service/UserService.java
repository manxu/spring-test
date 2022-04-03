package com.wm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wm.util.HttpUtil;
import com.wm.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public Map<String, Object> handlerOrder(String userId, String no, String amount, String noTime,String fl) throws Exception {
        if(fl == null || "".equals(fl)){
            fl = new BigDecimal(amount).multiply(new BigDecimal("3")).setScale(0, RoundingMode.DOWN).toPlainString();
        }

        Map<String, Object> result = new MapUtil().com("no", no).com("userId", userId).com("amount", amount + "")
                .map;

        //查询
        String queryOrder = "db.collection('m_order').where({userId:'" + userId + "',no:'" + no + "'}).get()";
        Map<String, Object> param = new MapUtil()
                .com("env", HttpUtil.getKey("env"))
                .com("query", queryOrder)
                .map;
        String order = HttpUtil.sendPost(HttpUtil.QUERY, param);
        JSONObject orderJson = JSONObject.parseObject(order);
        final int exist = orderJson.getJSONArray("data").size();
        if (exist > 0) {
            result.put("code", "-2");
            result.put("msg", "已存在");
        } else {

            //查询用户信息
            Map<String,Object> queryUser =  new MapUtil()
                    .com("env", HttpUtil.getKey("env"))
                    .com("query", "db.collection('m_user').where({userId:'"+userId+"'}).get()")
                    .map;
            String user = HttpUtil.sendPost(HttpUtil.QUERY, queryUser);
            JSONObject userJson = JSONObject.parseObject(user);
            JSONObject userData = JSONObject.parseObject(userJson.getJSONArray("data").getString(0));
            String addOrder = "db.collection('m_order').add({data:{" +
                    "no:'" + no + "', " +
                    "amount:" + ((fl == null || "".equals(fl))?amount:("'"+amount+"'")) + "," +
                    "fl:" + fl + "," +
                    "userId:'" + userId + "', " +
                    "noTime:'" + noTime + "'," +
                    "_openid:'"+ userData.getString("_openid") +"'" +
                    "}})";
            Map<String, Object> inparam = new MapUtil()
                    .com("env", HttpUtil.getKey("env"))
                    .com("query", addOrder)
                    .map;
            result.put("code", "0");
            result.put("msg", "新增");
            result.put("fl", fl);
            result.put("yue", userData.get("yue"));
            String inOrder = HttpUtil.sendPost(HttpUtil.ADD, inparam);
            JSONObject inOrderJson = JSONObject.parseObject(inOrder);
            if (inOrderJson.getString("errcode").equals("0")) {
                String userUpdate = "db.collection('m_user').where({userId:'" + userId + "'}).update({data:{subscribe_flg:0,yue:_.inc(" + fl + "), zuori:_.inc(" + fl + ")}})";
                String updateUserResult = HttpUtil.sendPost(HttpUtil.UPDATE, userUpdate);
                JSONObject updateUserResultJson = JSONObject.parseObject(updateUserResult);
                if (updateUserResultJson.getString("errcode").equals("0")) {
                    result.put("code", "1");
                    result.put("msg", "更新user");

                    //发布订阅消息
                    Integer flag = userData.getInteger("subscribe_flg");
                    if(1== flag) {
                        Map<String,Object> subM = new HashMap<>();
                        subM.put("touser", userData.getString("_openid"));
                        subM.put("page", "pages/index/index");
                        subM.put("lang", "zh_CN");

                        Map<String,Object> subMC = new HashMap<>();
                        Map<String,Object> x9 = new HashMap<>();
                        x9.put("value",amount);
                        subMC.put("amount9", x9);

                        Map<String,Object> x3 = new HashMap<>();
                        x3.put("value",new BigDecimal(fl).divide(new BigDecimal(100),2, RoundingMode.DOWN).toPlainString());
                        subMC.put("amount3", x3);

                        Map<String,Object> x7 = new HashMap<>();
                        x7.put("value","返利"); //只能用中文
                        subMC.put("phrase7", x7);

                        Map<String,Object> x5 = new HashMap<>();
                        x5.put("value",noTime);
                        subMC.put("date5", x5);
                        subM.put("data", subMC);
                        subM.put("template_id", "ADRRfkWm8wOxbFh_3xDTSxC7RKz9yxP5AN391cEOOSc");
                        String s = HttpUtil.sendPost(HttpUtil.SUB, subM);
                        if(!"0".equals(JSON.parseObject(s).getInteger("errcode"))){
                            logger.error("消息发送失败", JSON.toJSONString(subMC));
                        }else {
                            logger.info("消息发送成功", JSON.toJSONString(subMC));
                        }
                    }


                }
            }
        }
        return result;
    }

}
