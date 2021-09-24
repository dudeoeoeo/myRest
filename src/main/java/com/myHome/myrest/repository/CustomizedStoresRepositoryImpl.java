package com.myHome.myrest.repository;


import com.myHome.myrest.model.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedStoresRepositoryImpl implements CustomizedStoresRepository {


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Query(value = "select s.name, s.description, s.delivery_type, o.name, o.price, " +
//            "o.stock from stores s left join product_options o on s.id = ?1 ", nativeQuery = true)

    public List<Stores> findByStoreId(Long id) {
        List<Stores> stores = jdbcTemplate.query(
                "select s.name, s.description, s.delivery_type, o.name, o.price, o.stock from stores s left join product_options o on s.id = 1 ",
                new BeanPropertyRowMapper(Stores.class));

        return stores;
    }

        /*
    public List<User> findByCustomUsername(String username) {
        QUser qUser = QUser.user;
        JPAQuery<?> query = new JPAQuery<Void>(em);

        List<User> users = query.select(qUser)
                .from(qUser)
                .where(qUser.username.contains(username))
                .fetch();
        return users;
        return null;
    }

    public List<Stores> findByUsernameJdbc(Long id) {

        List<Stores> stores = jdbcTemplate.query(
                "select s.name, s.description, s.delivery_type, o.name, o.price, \" +\n" +
                    "  \"o.stock from stores s left join product_options o on s.id = ?1 ",
                new BeanPropertyRowMapper(Stores.class));

        return stores;
    }
         */
}
