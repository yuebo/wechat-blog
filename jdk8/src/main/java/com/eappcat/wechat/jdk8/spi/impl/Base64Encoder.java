package com.eappcat.wechat.jdk8.spi.impl;

import com.eappcat.wechat.jdk8.spi.Encoder;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

public class Base64Encoder implements Encoder {
    @Override
    public String encode(String value) {
        return Base64.encodeBase64String(value.getBytes(Charset.forName("UTF-8")));
    }
}
