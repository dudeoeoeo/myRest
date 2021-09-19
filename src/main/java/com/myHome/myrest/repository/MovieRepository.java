package com.myHome.myrest.repository;

import com.myHome.myrest.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
