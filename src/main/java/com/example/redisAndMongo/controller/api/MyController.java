package com.example.redisAndMongo.controller.api;

//import com.example.redisAndMongo.entity.Customer;
//import com.example.redisAndMongo.repository.CustomerRepository;
import com.example.redisAndMongo.entity.Employee;
import com.example.redisAndMongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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
    StringRedisTemplate redisTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    @Cacheable(value = "employee")
    public List<Employee> getAllEmployees(){
        return (List<Employee>) employeeRepository.findAll();
    }

    @GetMapping("/firstName/{firstName}")
    public Optional<Employee> getByFirstName(@PathVariable String firstName){
        return employeeRepository.findByFirstName(firstName);
    }

    @GetMapping("/lastName/{lastName}")
    public Optional<Employee> getByLastName(@PathVariable String lastName){
        return employeeRepository.findByLastName(lastName);
    }

    @GetMapping("/{firstName}/{lastName}")
    public Employee saveEmployee(@PathVariable String firstName, @PathVariable String lastName){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail("a@b.com");
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        Optional<Employee> tutorialData = employeeRepository.findById(id);

        if (tutorialData.isPresent()) {
            Employee _employee = tutorialData.get();
            _employee.setEmail(employee.getEmail());
            _employee.setFirstName(employee.getFirstName());
            _employee.setLastName(employee.getLastName());
            return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
