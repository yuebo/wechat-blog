package com.eappcat.wechat.redis.lock;

public interface RedisLockCallback {
    default Object failback(Object ... args){
        return "cannot get the lock";
    }
}
