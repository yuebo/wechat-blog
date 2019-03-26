package com.eappcat.wechat.redis.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Redis分布式锁注解，用于注释方法，表示该方法需要持有锁的人才能调用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedisLock {
    /**
     * redis的key, 留空的话为类名加方法名
     * @return
     */
    String key() default "";

    /**
     * 锁持有时间
     * @return
     */
    long timeout () default 300;

    /**
     * 是否在操作后立即释放锁。
     * @return
     */
    boolean releaseOnSuccess() default false;

    /**
     * 如果未获得锁，则执行该方法回调
     * @return
     */
    Class<? extends RedisLockCallback> callback() default RedisLockCallback.class;
}
