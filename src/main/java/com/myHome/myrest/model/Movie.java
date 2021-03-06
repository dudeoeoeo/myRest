package com.myHome.myrest.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Movie implements Serializable {

    private String title;
    private String link;
    private float userRating;
}
