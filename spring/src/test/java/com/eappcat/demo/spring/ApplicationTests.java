package com.eappcat.demo.spring;

import com.eappcat.demo.spring.scope.PrototypeBean;
import com.eappcat.demo.spring.scope.SingletonBean;
import com.eappcat.demo.spring.scope.ThirdBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        User user=new User();
        System.out.println(user.toString());
    }

    @Test
    public void singletonTest(){
        //第一次获取
        SingletonBean singletonBean=applicationContext.getBean(SingletonBean.class);
        //第二次获取
        SingletonBean singletonBean2=applicationContext.getBean(SingletonBean.class);
        Assert.assertEquals(singletonBean,singletonBean2);

    }
    @Test
    public void prototypeTest(){
        //第一次获取
        PrototypeBean prototypeBean=applicationContext.getBean(PrototypeBean.class);
        //第二次获取
        PrototypeBean prototypeBean2=applicationContext.getBean(PrototypeBean.class);
        Assert.assertNotEquals(prototypeBean,prototypeBean2);

    }
    @Test
    public void thirdBean(){
        //第一次获取
        ThirdBean thirdBean=applicationContext.getBean(ThirdBean.class);
        //第二次获取
        ThirdBean thirdBean2=applicationContext.getBean(ThirdBean.class);
        Assert.assertEquals(thirdBean.getSingletonBean(),thirdBean2.getSingletonBean());
        Assert.assertEquals(thirdBean.getPrototypeBean(),thirdBean2.getPrototypeBean());
    }
    @Test
    public void thirdBean2(){
        //第一次获取
        ThirdBean thirdBean=applicationContext.getBean(ThirdBean.class);
        //第二次获取
        ThirdBean thirdBean2=applicationContext.getBean(ThirdBean.class);
        Assert.assertEquals(thirdBean.getSingletonBean(),thirdBean2.getSingletonBean());
        Assert.assertNotEquals(thirdBean.getPrototypeBean(),thirdBean2.getPrototypeBean());
    }


}
