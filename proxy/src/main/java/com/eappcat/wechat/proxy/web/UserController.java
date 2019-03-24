package com.eappcat.wechat.proxy.web;

import com.eappcat.wechat.proxy.entity.User;
import com.eappcat.wechat.proxy.service.UserService;
import com.eappcat.wechat.proxy.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/create")
    public int createUser(){
        try {
            log.info("service class {}",userService.getClass());
            log.info("service proxy class is instanceof UserServiceImpl:{}",userService instanceof UserServiceImpl);
            userService.createUser();

        }catch (Exception e){

        }
        List<User> result= userService.findAllUser();
        return result.size();
    }
    /**
     * jdk的动态代理
     * @return
     */
    @GetMapping("/jdk")
    public int jdkProxy(){
        log.info("call for jdk proxy start");
        ClassLoader classLoader=this.getClass().getClassLoader();
        Class[] interfaces=new Class[]{UserService.class};
        UserService userService=(UserService) Proxy.newProxyInstance(classLoader,interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("proxy called: {}",method);
                return Collections.singletonList(new User("xxx","yyy",111));
            }
        });
        log.info("service proxy class is instanceof UserServiceImpl:{}",userService instanceof UserServiceImpl);
        List<User> users=userService.findAllUser();
        log.info("call for jdk proxy end");
        return users.size();

    }
    /**
     * 静态代理模式
     * @return
     */
    @GetMapping("/static")
    public int staticProxy(){
        UserService proxy=new StaticProxy(this.userService);
        return proxy.findAllUser().size();
    }

    /**
     * cglib的动态代理
     * @return
     */
    @GetMapping("/cglib")
    public int cglibProxy(){
        log.info("call for cglib proxy start");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.info("proxy called: {}",method);
                return Collections.singletonList(new User("xxx","yyy",111));
            }
        });
        UserService userService=(UserService)enhancer.create();
        List<User> users=userService.findAllUser();
        log.info("service proxy class is instanceof UserServiceImpl:{}",userService instanceof UserServiceImpl);
        log.info("call for cglib proxy end");
        return users.size();
    }

    /**
     * 基于接口的静态代理实现
     */
    private static class StaticProxy implements UserService{
        private UserService proxyUserService;
        public StaticProxy(UserService proxyUserService){
            this.proxyUserService=proxyUserService;
        }

        @Override
        public void createUser() {
            log.info("static proxy call: createUser");
            proxyUserService.createUser();
        }

        @Override
        public List<User> findAllUser() {
            log.info("static proxy call: findAllUser");
            return proxyUserService.findAllUser();
        }
    }
}
