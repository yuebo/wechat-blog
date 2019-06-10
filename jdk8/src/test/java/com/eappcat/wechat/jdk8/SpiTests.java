package com.eappcat.wechat.jdk8;

import com.eappcat.wechat.jdk8.spi.Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ServiceLoader;

@RunWith(JUnit4.class)
public class SpiTests {
    @Test
    public void test(){

        ServiceLoader<Encoder> serviceLoader=ServiceLoader.load(Encoder.class);
        serviceLoader.forEach(encoder -> {
            String test=encoder.encode("123456");
            System.out.println(test);
        });
    }
}
