package com.myHome.myrest.repository;

import com.myHome.myrest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

class CustomizedUserRepositoryImpl implements CustomizedUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findByCustomUsername(String username) {
        /*
        QUser qUser = QUser.user;
        JPAQuery<?> query = new JPAQuery<Void>(em);
        List<User> users = query.select(qUser)
                .from(qUser)
                .where(qUser.username.contains(username))
                .fetch();
        return users;
         */
        return null;
    }

    public List<User> findByUsernameJdbc(String username) {

        List<User> users = jdbcTemplate.query(
                "SELECT * FROM user WHERE username like ?",
                new Object[]{"%" + username + "%"},
                new BeanPropertyRowMapper(User.class));

        return users;
    }

}