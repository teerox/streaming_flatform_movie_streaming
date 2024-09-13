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
        String url =  CATALOG_SERVICE +"/movies/video/" + movieId;
        System.out.println("Calling: " + url);
         return restTemplate
                 .getForEntity(url, String.class,movieId)
                 .getBody();
    }
}
