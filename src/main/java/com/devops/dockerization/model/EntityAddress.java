package com.devops.dockerization.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name ="entity_address")

public class EntityAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",nullable = false)
    private Long id;
    @Column(name ="user_id", nullable = false)
    private Long userId;
    @Column(name ="locality", nullable = false)
    private String locality;
    @Column(name ="street", nullable = false)
    private String street;
    @Column(name ="city", nullable = false)
    private String city;
    @Column(name ="country", nullable = false)
    private String country;

}
