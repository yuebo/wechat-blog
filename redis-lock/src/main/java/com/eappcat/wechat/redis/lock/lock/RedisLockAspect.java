package com.eappcat.wechat.redis.lock.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 切点，待遇哦RedisLock的注解方法
     */
    @Pointcut("@annotation(com.eappcat.wechat.redis.lock.lock.RedisLock)")
    void cutPoint(){};

    @Around("cutPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        //获取方法上的注解
        RedisLock redisLock=targetMethod.getAnnotation(RedisLock.class);
        String key=redisLock.key();
        if(StringUtils.isEmpty(key)){
            key=point.getTarget().getClass().getName()+"."+targetMethod.getName();
        }
        //通过setnx设置值，如果值不存在，则获得该锁
        boolean test = redisTemplate.opsForValue().setIfAbsent(key, point.getThis().toString(), redisLock.timeout() ,TimeUnit.SECONDS);
        if(test){
            try {
                Object result = point.proceed();
                return result;
            }finally {
                if (redisLock.releaseOnSuccess()){
                    redisTemplate.delete(key);
                }
            }

        }else {
            //查找错误处理器
            RedisLockCallback redisLockCallback=(RedisLockCallback)applicationContext.getBean(redisLock.callback());
            return redisLockCallback.failback(point.getArgs());
        }

    }
}
