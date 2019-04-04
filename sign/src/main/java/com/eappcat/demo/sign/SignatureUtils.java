package com.eappcat.demo.sign;

import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class SignatureUtils {
    public static String signObject(Object target,String ...salt){
        List<Field> fieldList = new ArrayList<>();
        //1.读取签名字段

        fieldList.addAll(ReflectionUtils.getAllFields(target.getClass()));

        //2.按照排序
        Collections.sort(fieldList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        //3.获取值然后签名
        List<String> signaturePart=new ArrayList<>();
        for(Field field:fieldList){
            if(PropertyUtils.isReadable(target,field.getName())&& !Modifier.isStatic(field.getModifiers())){
                addSignatureField(signaturePart,field,target);
            }
        }
        StringBuilder buffer=new StringBuilder();
        for (String part:signaturePart){
            buffer= DigestUtils.appendMd5DigestAsHex(part.getBytes(),buffer);
        }
        //4.对签名加盐
        if(salt!=null){
            buffer= DigestUtils.appendMd5DigestAsHex(StringUtils.join(salt).getBytes(),buffer);
        }
        //5.对签名结果再次签名获取最后结果
        String finalMd5=DigestUtils.md5DigestAsHex(buffer.toString().getBytes());
        log.info("signature: {}",finalMd5);
        return finalMd5;

    }
    private static void addSignatureField(List<String> signaturePart, Field field,Object target) {

        try {
            Object value= PropertyUtils.getProperty(target,field.getName());
            if(value!=null){
                log.info("signature field: {}",field.getName());
                if(value instanceof Number){
                    signaturePart.add(new BigDecimal(value.toString()).toPlainString());
                }else if(value instanceof Date){
                    signaturePart.add(new BigDecimal(((Date) value).getTime()).toPlainString());
                }else if(value instanceof byte[]){
                    signaturePart.add(Base64Utils.encodeToString((byte[])value));
                }else {
                    signaturePart.add(value.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error to signature for "+target.getClass().getName()+"@"+field.getName());
        }
    }
}
