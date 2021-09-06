package com.zc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zc.dao.UserDao;
import com.zc.util.HttpUtil;
import com.zc.util.MapUtil;

@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDao userDao;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/collections")
    @ResponseBody
    public Object getCol(){
        Map<String, Object> param = new HashMap<>();
        param.put("env", "zc-hongbao-3grj4o8d48f4a1f2");
        param.put("limit", 50);
        String s = HttpUtil.
                sendPost("https://api.weixin.qq.com/tcb/databasecollectionget?access_token=" + HttpUtil.token, param);
        return s;
    }


    @RequestMapping("/datas")
    @ResponseBody
    public Object getCol(@RequestParam String table,@RequestParam String para,@RequestParam String skip){
        Map<String,Object> param =  new MapUtil()
                .com("env", HttpUtil.getKey("env"))
                .com("query", "db.collection(\""+ table +"\").where("+para+").limit(50).skip("+skip+").get()")
                .map;
        String s = HttpUtil.sendPost(HttpUtil.QUERY, param);
        return s;
    }

    //更新
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestParam String table,@RequestParam String para){
        logger.info("update : " + para);
        JSONObject p = JSONObject.parseObject(para);
        String id = p.getString("_id");
        p.remove("_id");
        Map<String,Object> param =  new MapUtil()
                .com("env", HttpUtil.getKey("env"))
                .com("query", "db.collection(\""+ table +"\").where({\"_id\":\""+ id +"\"}).update({data:"+ p.toJSONString() +"})")
                .map;
        String s = HttpUtil.sendPost(HttpUtil.UPDATE, param);
        logger.info(s);
        return s;
    }


    @RequestMapping("/1")
    public String order(){
        return "upload";
    }

    //更新
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam MultipartFile file){
        BufferedInputStream is = null;
        List<Map<String,Object>> results = new ArrayList<>();
        try {
            is = new BufferedInputStream(
                    file.getInputStream());
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            //读第一个sheet
            for (int i = 1;i<rows;i++){
                Row row = sheet.getRow(i);
                String noTime = row.getCell(2).getStringCellValue() ;
                String no = row.getCell(10).getStringCellValue() ;
                String userId = row.getCell(10).getStringCellValue();
                double amount = row.getCell(12).getNumericCellValue();
                String fl = new BigDecimal(amount).multiply(new BigDecimal("3")).setScale(0, RoundingMode.DOWN).toPlainString();
                Map<String,Object> result = new MapUtil().com("no", no).com("userId", userId).com("amount", amount+"")
                        .map;
                results.add(result);
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
                    continue;
                }

                String addOrder = "db.collection('m_order').add({" +
                        "no:'"+ no +"', " +
                        "amount:"+amount+"," +
                        "fl:"+ fl +"," +
                        "userId:'"+ userId +"', " +
                        "noTime:'"+ noTime +"', " +
                        "})";
                Map<String,Object> inparam =  new MapUtil()
                        .com("env", HttpUtil.getKey("env"))
                        .com("query", addOrder)
                        .map;
                result.put("code", "0");
                result.put("msg","新增");
                result.put("fl", fl);
                String inOrder = HttpUtil.sendPost(HttpUtil.ADD, inparam);
                JSONObject inOrderJson = JSONObject.parseObject(inOrder);
                if(inOrderJson.get("code").equals("0")){
                    String userUpdate = "db.collection('m_user').where({userId:'"+userId+"'}).update({data:{yue:_.inc("+fl+"), zuori:_.inc("+fl+")}})";
                    String updateUserResult = HttpUtil.sendPost(HttpUtil.UPDATE, userUpdate);
                    JSONObject updateUserResultJson = JSONObject.parseObject(updateUserResult);
                    if(updateUserResultJson.get("code").equals("0")){
                        result.put("code", "1");
                        result.put("msg","更新user");
                    }
                }
            }
        } catch (IOException e) {
            logger.error("", e);
        }finally {
            is = null;
        }
        return results;
    }


    //生成qr
    @RequestMapping("/qr")
    @ResponseBody
    @Deprecated
    public Object qr(@RequestParam MultipartFile file){
        try {
            JSONArray a = new JSONArray();
            InputStream in = file.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            LineNumberReader reader1 = new LineNumberReader(reader);
            String s ;
            while((s =reader1.readLine())!=null){
                JSONObject o = new JSONObject();
                o.put("userId", s);
                o.put("elmqr", "mm_12_12_12");
                a.add(o);
            }
            in.close();
            return a;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    //初始化
    @Deprecated
    @RequestMapping("/init")
    @ResponseBody
    public Object init(){
        try {
            Map<String,Object> param =  new MapUtil()
                    .com("env", HttpUtil.getKey("env"))
                    .com("query", "db.collection('m_user').where({}).get()")
                    .map;
            String s = HttpUtil.sendPost(HttpUtil.UPDATE, param);
            JSONObject res = JSONObject.parseObject(s);
            JSONArray users = (JSONArray) res.get("data");
            users.stream().forEach(e->{
                JSONObject user = (JSONObject)e;
                String openid = user.getString("_openid");
                logger.info(openid);
                param.put("query", "db.collection('m_qrs').doc('').update({data:{qrs:_.shift()}})");
                String uparam = HttpUtil.sendPost(HttpUtil.QUERY, param);
                param.put("query", "db.collection('m_user').where({_openid:'"+openid+"'}).update({data:"+ uparam +"})");
                String ures = HttpUtil.sendPost(HttpUtil.QUERY, param);
                logger.info(ures);
            });
            return s;
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }

    public static void main(String[] args) {
        Map<String,Object> param =  new MapUtil()
                .com("env", HttpUtil.getKey("env"))
                .com("query", "cloud.callFunction('userlian').data({}).get()")
                .map;
        String s = HttpUtil.sendPost(HttpUtil.UPDATE, param);
    }


}
