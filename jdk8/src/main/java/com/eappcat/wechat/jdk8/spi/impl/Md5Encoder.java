package com.eappcat.wechat.jdk8.spi.impl;

import com.eappcat.wechat.jdk8.spi.Encoder;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5Encoder implements Encoder {
    @Override
    public String encode(String value) {
        return DigestUtils.md5Hex(value);
    }
}
