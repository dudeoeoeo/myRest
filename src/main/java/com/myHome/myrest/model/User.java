package com.myHome.myrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"), // join할 테이블
    inverseJoinColumns = @JoinColumn(name = "role_id")) // join될 테이블
    private List<Role> roles = new ArrayList<>();


    // Board model이 가지고 있는 private User user 와 map
    // orphanRemoval = true = 부모가 없는 데이터는 전부 지움
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // LAZY 전략은 user를 호출할 때 board의 데이터가 필요 없다면 board 조회 쿼리를 날리지 않음
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Board> boards = new ArrayList<>();

}

