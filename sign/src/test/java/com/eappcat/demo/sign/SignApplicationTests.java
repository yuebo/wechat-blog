package com.eappcat.demo.sign;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
@Slf4j
public class SignApplicationTests {

    @Test(expected = SignatureException.class)
    public void contextLoads() throws InvocationTargetException, IllegalAccessException {
        User user=new User();
        user.setAge(1);
        user.setName("xyb");
        user.setGender(Gender.Male);
        User signed=CglibUtils.signObject(user);
        log.info("{}", ((SignatureAware)signed).getSignature());
        log.info("{}", JSONObject.toJSONString(signed));
        signed.setName("bbb");
    }

    @Test(expected = SignatureException.class)
    public void buddy(){
        User user=new User();
        user.setAge(1);
        user.setName("xyb");
        user.setGender(Gender.Male);
        User signed=ByteBuddyUtils.signObject(user);
        log.info("{}", JSONObject.toJSONString(signed));
        signed.setName("bbb");
    }
}
