package com.eappcat.demo.sign;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class SignatureInterceptor {
    @RuntimeType
    public static Object invoke(@This Object proxy, @Origin Method method, @SuperCall Callable<Object> call, @AllArguments Object[] agrs)
            throws Exception {
        Field signature=proxy.getClass().getSuperclass().getDeclaredField("signature");
        signature.setAccessible(true);
        if(signature.get(proxy)!=null){
            if(method.getName().startsWith("set")){
                throw new SignatureException("对象签名后不能被修改");
            }
        }
        return call.call();
    }
}
