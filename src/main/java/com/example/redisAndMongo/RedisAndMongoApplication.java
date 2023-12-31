package com.example.redisAndMongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisAndMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAndMongoApplication.class, args);
	}

}
