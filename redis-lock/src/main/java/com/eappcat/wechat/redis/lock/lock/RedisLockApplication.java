package com.eappcat.wechat.redis.lock.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisLockApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean RedisLockCallback redisLockCallback(){
		return new RedisLockCallback(){};
	}
}
