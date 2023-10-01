package com.example.redisAndMongo.controller.api;

//import com.example.redisAndMongo.entity.Customer;
//import com.example.redisAndMongo.repository.CustomerRepository;
import com.example.redisAndMongo.entity.Customer;
import com.example.redisAndMongo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class MyController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/username/{username}")
    public Optional<Customer> getByFirstName(@PathVariable String username){
        return customerRepository.findByUsername(username);
    }

    @GetMapping("/getAllKeys")
    public List<String> getAllKeys(){
        redisTemplate.keys("findByFirstName::Lokesh");
        String key = "findByFirstName*";
        Set keys = redisTemplate.keys(key);
        if (CollectionUtils.isEmpty(keys)) return null;
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
