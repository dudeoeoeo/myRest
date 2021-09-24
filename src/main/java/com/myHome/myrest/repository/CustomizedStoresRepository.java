package com.myHome.myrest.repository;

import com.myHome.myrest.model.Stores;

import java.util.List;

public interface CustomizedStoresRepository {

    List<Stores> findByStoreId(Long id);
}
