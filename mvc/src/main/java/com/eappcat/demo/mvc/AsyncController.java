package com.eappcat.demo.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class AsyncController {

    ExecutorService executorService=Executors.newSingleThreadExecutor();

    @GetMapping("/callable")
    public Callable<HttpEntity<String>> callable(){
        return () -> {
            Thread.sleep(5000);
            return new HttpEntity<String>("test");
        };
    }

    @GetMapping("/callable2")
    public Callable<String> callable2(){
        return () -> {
            Thread.sleep(5000);
            return "test2";
        };
    }
    @GetMapping("/callable3")
    public Callable<Map> callable3(){
        return () -> {
            Thread.sleep(5000);
            return new HashMap();
        };
    }
    @GetMapping("/deferredResult")
    public DeferredResult<HttpEntity<String>> deferredResult(){
        DeferredResult<HttpEntity<String>> deferredResult=new DeferredResult<>();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                deferredResult.setResult(new HttpEntity<>("deferredResult"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        return deferredResult;
    }
    @GetMapping("/deferredResult2")
    public DeferredResult<String> deferredResult2(){
        DeferredResult deferredResult=new DeferredResult();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                deferredResult.setResult("deferredResult2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        return deferredResult;
    }
    @GetMapping("/listenableFuture")
    public ListenableFuture<String> listenableFuture(){
        ListenableFutureTask<String> stringListenableFuture=new ListenableFutureTask<String>(()->{
            return "aaa";
        });
        executorService.submit(stringListenableFuture);
        return stringListenableFuture;
    }
    @GetMapping("/responseBodyEmitter")
    public ResponseBodyEmitter responseBodyEmitter(){
        ResponseBodyEmitter responseBodyEmitter=new ResponseBodyEmitter();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                responseBodyEmitter.send("aaaa", MediaType.TEXT_PLAIN);
                Thread.sleep(1000);
                responseBodyEmitter.send("{\"name\":\"aaaa\"}",MediaType.APPLICATION_JSON);
                Thread.sleep(1000);
                responseBodyEmitter.send("<xml>aaaa</xml>",MediaType.APPLICATION_XML);
                responseBodyEmitter.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
        return responseBodyEmitter;

    }
    @GetMapping(value = "/streamingResponseBody",produces = MediaType.TEXT_HTML_VALUE)
    public StreamingResponseBody streamingResponseBody(){
        StreamingResponseBody streamingResponseBody = outputStream -> {
            outputStream.write("<html>this is a html</html>".getBytes());
        };
        return streamingResponseBody;
    }


}
