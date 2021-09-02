package com.zc.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static Properties pts = getProperties();
    public static String token;


    public static String QUERY = "https://api.weixin.qq.com/tcb/databasequery?access_token=" + HttpUtil.token;
    public static String UPDATE = "https://api.weixin.qq.com/tcb/databaseupdate?access_token=" + HttpUtil.token;

    public static String getKey(String key){
        return pts.getProperty(key);
    }

    public static Properties getProperties() {
        /*try {
            InputStream in = new FileInputStream("E:/local.properties");
            Properties p = new Properties();
            p.load(in);
            String appid = p.getProperty("appid");
            String secret = p.getProperty("secret");
            String s = HttpUtil.
                    sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
            token = JSONObject.parseObject(s).getString("access_token");
            return p;
        } catch (Exception e) {
            logger.error("", e);
        }*/
        return null;

    }




    public static String sendPost(String url, Map<String, Object> param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            logger.info("executing request " + url);
            post.setHeader("Content-Type", "application/json;charset=utf-8");
            String requestParams = JSONObject.toJSONString(param);
            StringEntity postingString = new StringEntity(requestParams,
                    "utf-8");
            post.setEntity(postingString);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    final String result = EntityUtils.toString(entity);
                    logger.info("Response content: " + result);
                    return result;
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return null;
    }


    public static String sendGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            logger.info("executing request " + url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    final String result = EntityUtils.toString(entity);
                    logger.info("Response content: " + result);
                    return result;
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return null;
    }


    public static void main(String[] args) {

    }

}
