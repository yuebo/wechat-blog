package com.eappcat.demo.jxbrowser;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserType;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class JxbrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxbrowserApplication.class, args);
    }

    @Bean JxbrowserSupport jxbrowserSupport(){
        return new JxbrowserSupport();
    }
    @Bean Browser browser(@Autowired JxbrowserSupport jxbrowserSupport){
        System.setProperty("java.awt.headless", "false");
        Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
        AtomicInteger atomicInteger=new AtomicInteger(0);
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                int size=atomicInteger.getAndIncrement();
                if(size==event.getBrowser().getFramesIds().size()){
                    atomicInteger.set(0);
                    browser.executeJavaScript("setTimeout(new function(){window.print()},1000)");
                }
            }
        });
        return browser;

    }

}
