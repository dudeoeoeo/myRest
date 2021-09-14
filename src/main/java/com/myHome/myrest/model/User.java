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



    // Board model이 가지고 있는 private User user 와 map
    // orphanRemoval = true = 부모가 없는 데이터는 전부 지움
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

}

