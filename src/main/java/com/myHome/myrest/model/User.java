package com.myHome.myrest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;


    @ManyToMany
    @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"), // join할 테이블
    inverseJoinColumns = @JoinColumn(name = "role_id")) // join될 테이블
    private List<Role> roles = new ArrayList<>();

}

