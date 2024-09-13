package com.example.moviestreaming.controller;

import com.example.moviestreaming.service.MovieCatalogService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController implements ErrorController {

    public static final String VIDEO_DIRECTORY_FROM_MAC = "/Volumes/Tee/stream/";
    public static final String VIDEO_DIRECTORY_FROM_WINDOWS = "F:\\Stream\\";

    private final MovieCatalogService movieCatalogService;

    public MovieStreamController(MovieCatalogService movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY_FROM_MAC + videoPath);
        System.out.println("Attempting to access file: " + file.getAbsolutePath());
        if (!file.exists()) {
            Logger.getLogger(MovieStreamController.class.getName()).log(Level.SEVERE, "File not found: " + videoPath);
            return ResponseEntity.notFound().build();
        }
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(inputStreamResource);
    }

    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable Long videoInfoId) throws FileNotFoundException {
        String videoPath = movieCatalogService.getMoviePath(videoInfoId);
        System.out.println("Resolve Video path: " + videoPath);
        return streamVideo(videoPath);
    }

    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        // Custom error message or page
        return "An unexpected error occurred. Please try again.";
    }
}
