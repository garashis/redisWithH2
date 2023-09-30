package com.example.redisAndMongo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "TBL_EMPLOYEES")
@Data
public class Employee implements Serializable {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String email;
    private String lastName;
}
