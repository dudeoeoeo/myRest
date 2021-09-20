package com.myHome.myrest.service;

import com.myHome.myrest.model.Movie;
import com.myHome.myrest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> search(final String query) {
        return movieRepository.findByQuery(query);
    }
}
