package com.eappcat.demo.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class ControllerRegister implements ApplicationRunner {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Method method= HelloMvc.class.getDeclaredMethod("hello");
        Field field= RequestMappingHandlerMapping.class.getDeclaredField("config");
        field.setAccessible(true);
        //解析注解
        RequestMappingInfo.BuilderConfiguration configuration=(RequestMappingInfo.BuilderConfiguration)field.get(requestMappingHandlerMapping);

        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths(requestMapping.path())
                .methods(requestMapping.method())
                .params(requestMapping.params())
                .headers(requestMapping.headers())
                .consumes(requestMapping.consumes())
                .produces(requestMapping.produces())
                .mappingName(requestMapping.name());
        builder.options(configuration);
        //初始化示例
        HelloMvc mvc=new HelloMvc();
        //对bean进行注入，使得可以使用@Autowired
        applicationContext.getAutowireCapableBeanFactory().autowireBean(mvc);
        requestMappingHandlerMapping.registerMapping(builder.build(),mvc,method);
    }
}
