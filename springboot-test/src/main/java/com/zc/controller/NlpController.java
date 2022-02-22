package com.zc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.zc.easyexcel.ChatData;
import com.zc.service.UserService;
import com.zc.util.Sample;

@Controller
public class NlpController {
    private static Logger logger = LoggerFactory.getLogger(NlpController.class);
    @Autowired
    private UserService userService;

    @Value("${aa.text}")
    private int text;

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

    @RequestMapping("/upload1")
    @ResponseBody
    public void fx(@RequestParam("file") MultipartFile file) {
        ExcelWriter excelWriter = null;
        try {
            File templateFile = file.getResource().getFile();
            File destFile = new File("test.xlsx");
            List<ChatData> list  = new ArrayList<>();
            final ExcelWriterSheetBuilder nw = EasyExcel.write(destFile, ChatData.class).sheet();
            EasyExcel.read(templateFile, ChatData.class, new AnalysisEventListener() {
                @Override
                public void invoke(Object data, AnalysisContext context) {
                    ChatData d =  (ChatData) data;
                    final String dh = Sample.dh(d.getText());
                    JSONObject s = JSONObject.parseObject(dh);
                    d.setOptimistic(s.getString("optimistic"));
                    d.setNeutral(s.getString("neutral"));
                    d.setOno(s.getString("ono"));
                    list.add(d);
                    if(list.size() > 100){
                        save(list, nw);
                        list.clear();
                    }
                }

                private void save(List<ChatData> list, ExcelWriterSheetBuilder nw) {
                    nw.doWrite(list);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    save(list, nw);
                }
            }).headRowNumber(1).sheet().doRead();


        }catch (Exception e){

        }finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
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
