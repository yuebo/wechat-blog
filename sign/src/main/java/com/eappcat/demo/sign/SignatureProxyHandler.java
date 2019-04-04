package com.eappcat.demo.sign;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SignatureProxyHandler implements MethodInterceptor {
    private String signature;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(!StringUtils.isEmpty(signature)&&method.getName().startsWith("set")){
            throw new SignatureException("对象签名后不能被修改");
        }
        if(method.getName().equals("setSignature")){
            this.signature = (String)objects[0];
            return null;
        }
        if(method.getName().equals("getSignature")){
            return this.signature;
        }
        return methodProxy.invokeSuper(o,objects);
    }
}
