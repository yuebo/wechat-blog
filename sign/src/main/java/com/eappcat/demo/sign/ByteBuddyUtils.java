package com.eappcat.demo.sign;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.not;

public class ByteBuddyUtils {
    public static <T> T signObject(T o){
        //生成一个中间类添加signature属性
        Class<?> clazzSignature=new ByteBuddy().subclass(o.getClass()).defineField("signature",String.class, Visibility.PRIVATE)
                .implement(SignatureAware.class).intercept(FieldAccessor.ofBeanProperty())
                .make().load(ByteBuddyUtils.class.getClassLoader()).getLoaded();
        //生成一个代理类对所有方法进行拦截
        Class<?> clazz=new ByteBuddy().subclass(clazzSignature)
                .implement(SignatureAware.class)
                .method(not(isDeclaredBy(Object.class))).intercept(MethodDelegation.to(SignatureInterceptor.class))
                .make().load(clazzSignature.getClassLoader()).getLoaded();
        String signature=SignatureUtils.signObject(o);
        SignatureAware buddy=(SignatureAware)BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(o,buddy,"signature");
        buddy.setSignature(signature);
        return (T)buddy;

    }
//    public static <T> T signObject2(T o){
//        Class<?> clazz=new ByteBuddy().subclass(o.getClass()).defineField("signature",String.class, Visibility.PRIVATE)
//                .implement(SignatureAware.class)
//                .intercept(FieldAccessor.ofBeanProperty())
//                .make().load(ByteBuddyUtils.class.getClassLoader()).getLoaded();
//        String signature=SignatureUtils.signObject(o);
//        SignatureAware cglib=(SignatureAware)Enhancer.create(clazz, new SignatureProxyHandler());
//        BeanUtils.copyProperties(o,cglib,"signature");
//        cglib.setSignature(signature);
//        return (T)cglib;
//
//    }
}
