package com.example.moviestreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MovieStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieStreamingApplication.class, args);
	}

}