package com.example.redisAndMongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("customers")
@Data
public class Customer implements Serializable {
    @Id
    private String id;
    private String username;
    private String name;
    private String email;
}
