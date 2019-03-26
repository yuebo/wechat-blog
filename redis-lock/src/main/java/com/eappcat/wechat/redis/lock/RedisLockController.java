package com.eappcat.wechat.redis.lock;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisLockController {
    @GetMapping("/lock")
    @RedisLock
    public String tryLock(){
        return "get lock";
    }

}
