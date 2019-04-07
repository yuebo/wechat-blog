package com.eappcat.demo.jxbrowser;

import com.alibaba.fastjson.JSONObject;
import com.teamdev.jxbrowser.chromium.Browser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@Slf4j
public class PrintController {
    @Autowired
    private Browser browser;


    /**
     * 用于打印网页
     * @param url
     * @return
     * @throws Exception
     */
    @GetMapping("/print")
    public DeferredResult<JSONObject> print(@RequestParam("url") String url) throws Exception {
        DeferredResult<JSONObject> deferredResult = new DeferredResult<>();
        if(!browser.isLoading()){
            browser.setPrintHandler(new MyPintHandler(deferredResult));
            browser.loadURL(url);
        }else {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("status",500);
            jsonObject.put("messgae","尚有未完成文件，请稍后再试");
            deferredResult.setResult(jsonObject);
        }
        return deferredResult;
    }
    /**
     * 用于下载打印完成的文件
     * @param file
     * @return
     * @throws Exception
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response,@RequestParam("file") String file) throws Exception {
        File tempfile=new File(FileUtils.getTempDirectory(),file);
        if(tempfile.exists()){
            InputStream inputStream=new FileInputStream(tempfile);
            try {
                response.setHeader("Content-Type","application/pdf");
                response.setHeader("Content-Disposition", "attachment;fileName="+file);
                IOUtils.copy(inputStream,response.getOutputStream());
            }finally {
               IOUtils.closeQuietly(inputStream);
            }
        }else {
            response.sendError(404,"File cannot be found");
        }

    }
}
