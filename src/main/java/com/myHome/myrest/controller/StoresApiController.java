package com.myHome.myrest.controller;

import com.myHome.myrest.model.Stores;
import com.myHome.myrest.repository.ProductOptionsRepository;
import com.myHome.myrest.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoresApiController {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ProductOptionsRepository productOptionsRepository;

    @GetMapping("/stores/{id}")
    public List<Stores> getStores(@PathVariable Long id) {
        List<Stores> st = storesRepository.findByStoreId(id);
        return st;
    }
}
