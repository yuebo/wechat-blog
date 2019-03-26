package com.eappcat.wechat.redis.lock.lock;

public interface RedisLockCallback {
    default Object failback(Object ... args){
        return "cannot get the lock";
    }
}
