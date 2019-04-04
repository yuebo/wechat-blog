package com.eappcat.demo.sign;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;

public class CglibUtils {
    public static <T> T signObject(T o){
        String signature=SignatureUtils.signObject(o);
        SignatureAware enhanced=(SignatureAware)Enhancer.create(o.getClass(), new Class[]{SignatureAware.class}, new SignatureProxyHandler());
        BeanUtils.copyProperties(o,enhanced,"signature");
        enhanced.setSignature(signature);
        return (T)enhanced;
    }
}
