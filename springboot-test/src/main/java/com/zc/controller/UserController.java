package com.zc.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String,Object>> res = new ArrayList<>();
        try {
            is = new BufferedInputStream(
                    file.getInputStream());
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            //读第一个sheet
            for (int i = 1;i<rows;i++){
                Row row = sheet.getRow(i);
                String no = row.getCell(10).getStringCellValue();
                String amount = row.getCell(12).getStringCellValue();

                //查询
                String query = "db.collection(\"m_order\").aggregate()." +
                        "lookup({" +
                        "from:'m_user'," +
                        "localField:'_openid'," +
                        "foreignField:'_openid'," +
                        "as:'user'" +
                        "}).where({no:'"+ no +"'}).get()";
                Map<String,Object> param =  new MapUtil()
                        .com("env", HttpUtil.getKey("env"))
                        .com("query", query)
                        .map;
                String cq = HttpUtil.sendPost(HttpUtil.QUERY, param);
                JSONObject cqjson = JSONObject.parseObject(cq);
                for(int j=0;j< cqjson.getJSONArray("data").size();j++){
                    JSONObject c = cqjson.getJSONArray("data").getJSONObject(j);
                    if(c.get("fl")!=null){
                        res.add(new MapUtil().com("no", no).com("amount", c.getString("amount")).com("fl", c.getString("fl"))
                                .com("flflag","已返利").map);
                    }else{

                        /*Map<String,Object> be = new MapUtil().com("no", no).com("amount",  "").
                                com("fl",  "")).map;*/
                        //be.put()



                    }
                }





            }
        } catch (IOException e) {
            logger.error("", e);
        }finally {
            is = null;
        }
        return res;
    }

}
