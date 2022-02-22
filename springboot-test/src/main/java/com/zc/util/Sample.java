package com.zc.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.crypto.Data;

import org.apache.http.client.utils.DateUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "25593451";
    public static final String API_KEY = "g3qn7XAr4Hz336cBohVGrIfZ";
    public static final String SECRET_KEY = "UkZgNywi3QIBWSUWusGOpk1amr7ICj8q";

    /**
     * 情感分析
     * @param content
     * @return
     */
    public static String ex(String content) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(content, options);
        return res.toString(2);
    }

    /**
     * 对话分析
     * @param content
     * @return
     */
    public static String dh(String content) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("scene", "customer_service");

        // 情感倾向分析
        JSONObject res = client.emotion(content, options);
        return res.toString(2);
    }



    /**
     * 语音合成
     */
    public static String yu(String content
            , String spd,
                             String pit,
                            String vol,
                           String per) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech("5577387", "iulUZSUXC1edGCY3fXBGbG9a", "UC6GP0YbtU4Wlh312wdwfqb43FrVifMw");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", spd);
        options.put("pit", pit);
        options.put("vol", vol);
        options.put("per", per);
        // 调用接口
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "E:/output"+ DateUtils.formatDate(new Date(),"yyyymmddHHmmss" ) +".mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "1:成功";
        }
        if (res1 != null) {
            return res1.toString(2);
        }
        return "0";
    }

}
