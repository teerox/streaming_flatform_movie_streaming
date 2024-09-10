package com.example.moviestreaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogService {

    public static final String CATALOG_SERVICE = "http://movie-catalogs";

    private final RestTemplate restTemplate;

    MovieCatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMoviePath(Long movieId) {
         return restTemplate.getForEntity(
                 CATALOG_SERVICE +"/movie-info/list/" + movieId, String.class,
                 movieId)
                 .getBody();
    }
}
