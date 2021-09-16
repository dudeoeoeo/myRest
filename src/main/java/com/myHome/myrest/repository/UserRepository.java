package com.myHome.myrest.repository;

import com.myHome.myrest.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름에 맞는 id를 가져옴
    User findByUsername(String username);

    // User 클래스의 boards 변수명
    @EntityGraph(attributePaths = { "boards" })
    List<User> findAll();
}
