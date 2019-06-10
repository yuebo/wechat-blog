package com.eappcat.wechat.jdk8.spi.impl;

import com.eappcat.wechat.jdk8.spi.Encoder;
import org.apache.commons.codec.digest.DigestUtils;

public class ShaEncoder implements Encoder {
    @Override
    public String encode(String value) {
        return DigestUtils.sha1Hex(value);
    }
}
