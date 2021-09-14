package com.myHome.myrest.repository;

import com.myHome.myrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름에 맞는 id를 가져옴
    User findByUsername(String username);
}
