package com.myHome.myrest.service;

import com.myHome.myrest.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoresService {

    @Autowired
    StoresRepository storesRepository;

}
