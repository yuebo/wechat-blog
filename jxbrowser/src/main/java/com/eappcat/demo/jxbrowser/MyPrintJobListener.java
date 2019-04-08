package com.eappcat.demo.jxbrowser;

import com.alibaba.fastjson.JSONObject;
import com.teamdev.jxbrowser.chromium.events.PrintJobEvent;
import com.teamdev.jxbrowser.chromium.events.PrintJobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.File;

@Slf4j
public class MyPrintJobListener implements PrintJobListener {
    private DeferredResult<JSONObject> deferredResult;
    private File tempFile;

    public MyPrintJobListener(DeferredResult<JSONObject> deferredResult, File tempFile){
        this.deferredResult=deferredResult;
        this.tempFile=tempFile;
    }
    @Override
    public void onPrintingDone(PrintJobEvent printJobEvent) {
        JSONObject jsonObject=new JSONObject();
        if (!deferredResult.isSetOrExpired()){
            if(printJobEvent.isSuccess()){
                jsonObject.put("status",200);
                jsonObject.put("output",tempFile.getName());
                deferredResult.setResult(jsonObject);
            }else {
                jsonObject.put("status",500);
                jsonObject.put("message","打印失败");
                deferredResult.setResult(jsonObject);
            }
        }
        log.info("print end: {}",printJobEvent.isSuccess());
    }
}
