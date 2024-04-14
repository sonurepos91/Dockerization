package com.devops.dockerization.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_no", nullable = false, unique = true)
    private String phoneNo;
    @Column(name = "age", nullable = false)
    private Integer age;

    /*
     *BiDirectional RelationShips
     * */
    @OneToMany(mappedBy = "userId", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<EntityAddress> addresses = new ArrayList<>();

    /*
     *UniDirectional RelationShips
     *Name of the column should be exact what present in the SQL Database
     * */
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<BankAccount> bankAccounts = new ArrayList<>();

}
