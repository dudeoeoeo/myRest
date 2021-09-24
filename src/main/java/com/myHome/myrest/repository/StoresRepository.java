package com.myHome.myrest.repository;

import com.myHome.myrest.model.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoresRepository extends JpaRepository<Stores, Long>, CustomizedStoresRepository {

    /*
    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameQuery(String username);

    @Query(value = "select * from User u where u.username like %?1%", nativeQuery = true)
    List<User> findByUsernameNativeQuery(String username);
     */

    @Query(value = "select s.name, s.description, s.delivery_type, o.name, o.price, " +
            "o.stock from stores s left join product_options o on s.id = 1 ", nativeQuery = true)
    public List<Stores> findByStoreId(Long id);
}
