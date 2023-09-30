package com.example.redisAndMongo.repository;

import com.example.redisAndMongo.entity.Employee;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Cacheable(value = "findByFirstName", unless="#result == null") Optional<Employee> findByFirstName(String firstName);
    Optional<Employee> findByLastName(String lastName);

    //@Cacheable(value = "employee") Employee save(Employee employee);
    //@CacheEvict(value = "findByFirstName", key = "#employee.firstName")
    @CachePut(value = "findByFirstName", key = "#result.firstName", condition = "#result != null")
    Employee save(Employee employee);
}
