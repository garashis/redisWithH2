package com.example.redisAndMongo.repository;

import com.example.redisAndMongo.entity.Customer;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //@Cacheable(value = "findByFirstName", unless="#result == null") Optional<Customer> findByFirstName(String
    // firstName);
    Optional<Customer> findByUsername(String username);

    //@Cacheable(value = "employee") Employee save(Employee employee);
    //@CacheEvict(value = "findByFirstName", key = "#employee.firstName")
    //@CachePut(value = "findByFirstName", key = "#result.firstName", condition = "#result != null")
    //Customer save(Customer customer);
}
